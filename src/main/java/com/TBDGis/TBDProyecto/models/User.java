package com.TBDGis.TBDProyecto.models;

public class User {

    // Attributes
    private Integer id;
    private String name;
    private String mail;
    private Integer phone;
    private String password;
    private String loginToken;
    private Integer idRol;

    private Integer invisible;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getInvisible() {
        return invisible;
    }

    public void setInvisible(Integer invisible) {
        this.invisible = invisible;
    }

    // Additional methods and content
    public void setUser(User newUser, Rol role){
        this.name = newUser.getName();
        this.mail = newUser.getMail();
        this.phone = newUser.getPhone();
        this.password = newUser.getPassword();
        this.loginToken = newUser.getLoginToken();
        this.idRol = newUser.getIdRol();
        this.invisible = 0;

    }

}
