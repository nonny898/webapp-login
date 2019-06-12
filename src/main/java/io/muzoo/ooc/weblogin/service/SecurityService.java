/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ooc.weblogin.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import org.apache.commons.lang.StringUtils;

public class SecurityService {

        private Map<String, String> userCredentials = new HashMap<>();

        public boolean validate(LoginBean loginBean, MySql mySql, HttpServletRequest request) throws Exception {

                boolean status = false;

                try {
                        // Database name to access
                        PreparedStatement ps = mySql.connection().prepareStatement("select * from login where username = ? and  password = ? ");
                        ps.setString(1, loginBean.getUsername());
                        ps.setString(2, loginBean.getPassword());
                        ResultSet rs = ps.executeQuery();
                        status = rs.next();
                } catch (SQLException e) {
                        // process sql exception
                        mySql.printSQLException(e);
                }
                if (status) {
                        request.getSession().setAttribute("username", loginBean.getUsername());
                        userCredentials.put(loginBean.getUsername(), loginBean.getPassword());
                }
                return status;
        }

        public boolean isAuthorized(HttpServletRequest request) {
                String username = (String) request.getSession().getAttribute("username");
                return (username != null && userCredentials.containsKey(username));
        }
}
