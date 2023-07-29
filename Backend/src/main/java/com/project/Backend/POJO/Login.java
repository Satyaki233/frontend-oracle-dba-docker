package com.project.Backend.POJO;

public class Login {
    String id;
    String email;
    String password;


    public Login(String email, String password,String id) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Login() {
        
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }



    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPasssword(String passsword) {
        this.password = passsword;
    }



    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", passsword='" + getPassword() + "'" +
            "}";
    }

}
