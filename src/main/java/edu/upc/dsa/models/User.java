package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User {

        int idUser;
        String name;
        String surname;
        String username;
        String password;
        //List<Product> inventario;
        double coins;
        int fuel;
        int food;
        int puntos;
        private String avatar;
        private Long id;




        public User(){ //this.idUser = RandomUtils.getIdUser();
             }
        public User(int idUser, String name, String surname, String username, String password){
            this.idUser = idUser;
            this.name = name;
            this.surname = surname;
            this.username = username;
            this.password = password;
            //this.inventario = new ArrayList<Product>();
            this.coins = 100;
            this.fuel = 0;
            this.food = 0;
        }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    /*public List<Product> getInventario() {
            return inventario;
    }

    public void setInventario(List<Product> inventario) {
        this.inventario = inventario;
    }

     */

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }


    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
