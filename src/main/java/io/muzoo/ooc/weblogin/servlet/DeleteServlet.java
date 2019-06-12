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
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet implements Routable {

        private SecurityService securityService;
        private MySql mySql;
        private LoginBean loginBean;

        @Override
        public String getMapping() {
                return "/delete";
        }

        @Override
        public void setSecurityService(SecurityService securityService) {
                this.securityService = securityService;
        }

        @Override
        public void setMySql(MySql mySql) {
                this.mySql =mySql;
        }

        @Override
        public void setLoginBean(LoginBean loginBean) {
                this.loginBean = loginBean;
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
                rd.include(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
                        if (!StringUtils.equals(username,loginBean.getUsername())) {
                                try {
                                        // Database name to access
                                        PreparedStatement ps = mySql.connection().prepareStatement("delete from login where username=?");
                                        ps.setString(1, username);
                                        ps.executeUpdate();
                                        response.sendRedirect("/");
                                } catch (SQLException e) {
                                        // process sql exception
                                        mySql.printSQLException(e);
                                } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                }
                        } else {
                                String error = "Cannot delete self.";
                                request.setAttribute("error", error);
                                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
                                rd.include(request, response);
                        }
                } else {
                        String error = "Username or password is missing.";
                        request.setAttribute("error", error);
                        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
                        rd.include(request, response);
                }
        }
}
