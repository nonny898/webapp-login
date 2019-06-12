package io.muzoo.ooc.weblogin.MySql;

import java.sql.*;

public class MySql {

        private final String jdbcDriverStr;
        private final String jdbcURL;

        public MySql() {
                this.jdbcDriverStr = "com.mysql.jdbc.Driver";
                this.jdbcURL = "jdbc:mysql://localhost/";
        }

        public Connection connection () throws SQLException, ClassNotFoundException {
                Class.forName(jdbcDriverStr);
                String dbName = "homework4";
                String dbUsername = "root";
                String dbPassword = "Nonny2540";
                return DriverManager.getConnection(jdbcURL + dbName, dbUsername, dbPassword);
        }

        public void printSQLException(SQLException ex) {
                for (Throwable e: ex) {
                        if (e instanceof SQLException) {
                                e.printStackTrace(System.err);
                                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                                System.err.println("Message: " + e.getMessage());
                                Throwable t = ex.getCause();
                                while (t != null) {
                                        System.out.println("Cause: " + t);
                                        t = t.getCause();
                                }
                        }
                }
        }
}
