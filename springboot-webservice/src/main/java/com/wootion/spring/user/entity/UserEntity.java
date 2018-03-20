package com.wootion.spring.user.entity;

import java.io.Serializable;

/**
 * @author Xzhang
 * @date 2018/3/17 11:48
 */
public class UserEntity implements Serializable{

    /**
     */
    private static final long serialVersionUID = 8408913746829183859L;
    private int id;
    private String accunt;
    private String username;
    private String email;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccunt() {
        return accunt;
    }

    public void setAccunt(String accunt) {
        this.accunt = accunt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
