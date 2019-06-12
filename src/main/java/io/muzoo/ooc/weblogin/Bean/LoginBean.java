package io.muzoo.ooc.weblogin.Bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
        private String username;
        private String password;
        private  boolean validated = false;

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
        
        public boolean isValidated() {
                return validated;
        }
        
        public void setValidated(boolean validated) {
                this.validated = validated;
        }
}