package io.muzoo.ooc.weblogin.servlet;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.Routable;
import io.muzoo.ooc.weblogin.service.SecurityService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet implements Routable {

        private SecurityService securityService;
        private MySql mySql;
        private LoginBean loginBean;

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
                if(session.getAttribute("username") != null){
                        session.removeAttribute("username");
                        response.sendRedirect("/login");
                }
        }

        @Override
        public String getMapping() {
                return "/logout";
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
