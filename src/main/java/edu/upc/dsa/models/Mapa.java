package edu.upc.dsa.models;

public class Mapa {
    int idMapa;
    String name;
    int fuelRequirement;
    int foodElements;
    int fuelElements;
    int enemies;

    public Mapa(){}
    public Mapa(int idMapa, int fuelRequirement, int foodElements, int fuelElements, int enemies, String name) {
        this.idMapa = idMapa;
        this.fuelRequirement = fuelRequirement;
        this.foodElements = foodElements;
        this.fuelElements = fuelElements;
        this.enemies = enemies;
        this.name = name;
    }
    public Mapa(int foodElements, int fuelElements, int fuelRequirement, int enemies, String name){
        this.foodElements = foodElements;
        this.fuelElements = fuelElements;
        this.fuelRequirement = fuelRequirement;
        this.enemies = enemies;
        this.name = name;
    }
    public int getIdMapa() {
        return idMapa;
    }

    public int getFuelRequirement() {
        return fuelRequirement;
    }
    public int getFoodElements() {
        return foodElements;
    }
    public int getFuelElements() {
        return fuelElements;
    }
    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }
    public void setFuelRequirement(int fuelRequirement) {
        this.fuelRequirement = fuelRequirement;
    }
    public void setFoodElements(int foodElements) {
        this.foodElements = foodElements;
    }
    public void setFuelElements(int fuelElements) {
        this.fuelElements = fuelElements;
    }
    public void setEnemies(int enemies) {
        this.enemies = enemies;
    }
    public int getEnemies() {
        return enemies;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
