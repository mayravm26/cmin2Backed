package edu.upc.dsa.models;

public class Partida {
    private int idPartida;
    int idMapa = getIdMapa();
    private String username;

    public Partida(){

    }

    public Partida(int idPartida, int idMapa, String username) {
        this.idPartida = idPartida;
        this.idMapa = idMapa;
        this.username = username;
    }
    public int getIdPartida() {
        return idPartida;
    }
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }
    public int getIdMapa() {
        return idMapa;
    }
    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
