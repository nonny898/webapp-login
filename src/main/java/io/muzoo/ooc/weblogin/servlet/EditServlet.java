package io.muzoo.ooc.weblogin.servlet;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.Routable;
import io.muzoo.ooc.weblogin.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditServlet extends HttpServlet implements Routable {
    
    private SecurityService securityService;
    private MySql mySql;
    private LoginBean loginBean;
    
    @Override
    public String getMapping() {
        return "/edit";
    }
    
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    
    @Override
    public void setMySql(MySql mySql) {
        this.mySql = mySql;
    }
    
    @Override
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
        rd.include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldUsername = request.getParameter("oldusername");
        String oldPassword = request.getParameter("oldpassword");
        String newUsername = request.getParameter("newusername");
        String newPassword = request.getParameter("newpassword");
        if (!StringUtils.isBlank(newUsername) && !StringUtils.isBlank(oldUsername)) {
            try {
                
                PreparedStatement ps = mySql.connection().prepareStatement("select * from login where username = ? and password = ?");
                ps.setString(1,oldUsername);
                ps.setString(2,oldPassword);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ps = mySql.connection().prepareStatement("update login set username = '"+newUsername+"'  where username = '"+oldUsername+"'");
                    ps.executeUpdate();
                    ps = mySql.connection().prepareStatement("update login set password = '"+newPassword+"'  where username = '"+oldUsername+"'");
                    ps.executeUpdate();
                    response.sendRedirect("/");
                } else  {
                    String error = "Username does not exist.";
                    request.setAttribute("error", error);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
                    rd.include(request, response);
                }
            } catch (SQLException e) {
                mySql.printSQLException(e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String error = "Username or password is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(request, response);
        }
    }
}
