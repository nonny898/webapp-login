package io.muzoo.ooc.weblogin;

import io.muzoo.ooc.weblogin.Bean.LoginBean;
import io.muzoo.ooc.weblogin.MySql.MySql;
import io.muzoo.ooc.weblogin.service.SecurityService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);

    void setMySql(MySql mySql);

    void setLoginBean(LoginBean loginBean);
}
