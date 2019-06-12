/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ooc.weblogin;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.servlet.*;
import io.muzoo.ooc.weblogin.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

class ServletRouter {

        private static final List<Class<? extends Routable>> routables = new ArrayList<>();

        static {
                routables.add(UserListServlet.class);
                routables.add(LoginServlet.class);
                routables.add(LogoutServlet.class);
                routables.add(AddUserServlet.class);
                routables.add(DeleteServlet.class);
        }

        private SecurityService securityService;

        private MySql mySql;

        private LoginBean loginBean;

        public void setLoginBean(LoginBean loginBean) {
                this.loginBean = loginBean;
        }

        public void setMySql(MySql mySql) {
                this.mySql = mySql;
        }

        void setSecurityService(SecurityService securityService) {
                this.securityService = securityService;
        }

        void init(Context ctx) {
                for (Class<? extends Routable> routableClass : routables) {
                        try {
                                Routable routable = routableClass.newInstance();
                                routable.setSecurityService(securityService);
                                routable.setMySql(mySql);
                                routable.setLoginBean(loginBean);
                                String name = routable.getClass().getSimpleName();
                                Tomcat.addServlet(ctx, name, (HttpServlet) routable);
                                ctx.addServletMapping(routable.getMapping(), name);
                        } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                        }
                }
        }

}
