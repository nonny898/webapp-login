/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ooc.weblogin.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;

public class SecurityService {

        private Set<String> userCredentials = new HashSet<>();

        public boolean validate(LoginBean loginBean, MySql mySql, HttpServletRequest request) throws Exception {

                boolean status = false;

                try {
                        PreparedStatement ps = mySql.connection().prepareStatement("select * from login where username = ? and  password = ? ");
                        ps.setString(1, loginBean.getUsername());
                        ps.setString(2, loginBean.getPassword());
                        ResultSet rs = ps.executeQuery();
                        status = rs.next();
                } catch (SQLException e) {
                        mySql.printSQLException(e);
                }
                if (status) {
                        loginBean.setValidated(true);
                        request.getSession().setAttribute("username", loginBean.getUsername());
                        userCredentials.add("admin");
                }
                return status;
        }

        public boolean isAuthorized(HttpServletRequest request) {
                String username = (String) request.getSession().getAttribute("username");
                return (username != null && userCredentials.contains(username));
        }
}
