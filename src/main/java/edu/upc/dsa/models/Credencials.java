package edu.upc.dsa.models;

public class Credencials {
    public String username;
    public String password;

    public Credencials(){}
    public Credencials(String username, String password){
        this.username = username;
        this.password = password;
    }

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
}
