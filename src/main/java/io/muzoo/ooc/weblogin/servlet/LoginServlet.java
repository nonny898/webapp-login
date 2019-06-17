package io.muzoo.ooc.weblogin.servlet;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.service.SecurityService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.muzoo.ooc.weblogin.Routable;
import org.apache.commons.lang.StringUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet implements Routable {


        private SecurityService securityService;
        private MySql mySql;
        private LoginBean loginBean;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                rd.include(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                loginBean.setUsername(username);
                loginBean.setPassword(password);

                if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
                        try {
                                if (securityService.validate(loginBean,mySql, request)) {
                                        response.sendRedirect("/");
                                } else {
                                        String error = "Wrong username or password.";
                                        request.setAttribute("error", error);
                                        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                                        rd.include(request, response);
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                } else {
                        String error = "Username or password is missing.";
                        request.setAttribute("error", error);
                        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                        rd.include(request, response);
                }

                // check username and password against database
                // if valid then set username attribute to session via securityService
                // else put error message to render error on the login form

        }

        @Override
        public String getMapping() {
                return "/login";
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
}
