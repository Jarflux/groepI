package com.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 4-3-13
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable {
    private Long fId;
    private String fName;
    private String fEmail;
    private String fPassword;
    private Long fDateOfBirth;

    public User(Long fId, String fName, String fEmail, String fPassword, Long fDateOfBirth) {
        this.fId = fId;
        this.fName = fName;
        this.fEmail = fEmail;
        this.fPassword = fPassword;
        this.fDateOfBirth = fDateOfBirth;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    public Long getfDateOfBirth() {
        return fDateOfBirth;
    }

    public void setfDateOfBirth(Long fDateOfBirth) {
        this.fDateOfBirth = fDateOfBirth;
    }
}
