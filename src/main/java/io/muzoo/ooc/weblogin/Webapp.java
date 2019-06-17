package io.muzoo.ooc.weblogin;

import java.io.File;
import javax.servlet.ServletException;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Webapp {
    
    public static void main(String[] args) {
        
        File docBase = new File("src/main/webapp/");
        docBase.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        
        SecurityService securityService = new SecurityService();
        ServletRouter servletRouter = new ServletRouter();
        MySql mySql = new MySql();
        LoginBean loginBean = new LoginBean();
        servletRouter.setSecurityService(securityService);
        servletRouter.setMySql(mySql);
        servletRouter.setLoginBean(loginBean);
        
        Context ctx;
        try {
            ctx = tomcat.addWebapp("", docBase.getAbsolutePath());
            servletRouter.init(ctx);
            
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException ex) {
            ex.printStackTrace();
        }
        
    }
}
