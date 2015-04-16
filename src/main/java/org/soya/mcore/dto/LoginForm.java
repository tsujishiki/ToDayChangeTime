package org.soya.mcore.dto;

/**
 * Created by FunkySoya on 2015/4/16.
 */
public class LoginForm {
    private String userName;
    private String password;
    private String kaptcha;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
