package edu.upc.dsa.models;

public class Product {
    String idProduct;
    String name;
    String description;
    double price;
    String imatge;

    public Product(){ //this.idProduct = RandomUtils.getIdProduct();
         }
    public Product(String idProduct, String name, String description, double price, String imatge){
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imatge = imatge;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getImatge() {
        return imatge;
    }
    public void setImatge(String imatge) {
        this.imatge = imatge;
    }
}
