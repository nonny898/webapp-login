/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ooc.weblogin.servlet;

import io.muzoo.ooc.weblogin.BCrypt;
import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.Routable;
import io.muzoo.ooc.weblogin.service.SecurityService;
import org.apache.coyote.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListServlet extends HttpServlet implements Routable {

        private SecurityService securityService;
        private MySql mySql;
        private LoginBean loginBean;

        @Override
        public String getMapping() {
                return "/index.jsp";
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
                List<String> dataList = new ArrayList<>();
                boolean authorized = securityService.isAuthorized(request);
                if (authorized) {
                        ResultSet resultSet;
                        try {
                                Statement statement = mySql.connection().createStatement();
                                statement.executeQuery("select * from login");
                                resultSet = statement.getResultSet();
                                while (resultSet.next()) {
                                        dataList.add(resultSet.getString("username"));
                                        dataList.add(BCrypt(resultSet.getString("password")));
                                }
                                resultSet.close();
                                statement.close();
                        } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                        }
                        // do MVC in here
//                        String username = (String) request.getSession().getAttribute("username");
                        request.setAttribute("data", dataList);
                        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/userlist.jsp");
                        rd.include(request, response);
                } else {
                        response.sendRedirect("/login");
                }
        }
        
        private String BCrypt(String s) {
                return BCrypt.hashpw(s, BCrypt.gensalt(12));
        }
}
