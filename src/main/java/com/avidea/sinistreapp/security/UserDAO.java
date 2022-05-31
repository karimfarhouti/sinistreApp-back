package com.avidea.sinistreapp.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDAO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String login;

    @NotBlank
    @Size(min = 8, max = 120)
    private String password;

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "UserDAO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
