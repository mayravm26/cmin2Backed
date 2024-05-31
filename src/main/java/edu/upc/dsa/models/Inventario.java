package edu.upc.dsa.models;

public class Inventario {
    int idUser;
    int idProduct;
    int quantity;
    public Inventario(){

    }
    public Inventario(int idUser, int idProduct, int quantity) {
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

