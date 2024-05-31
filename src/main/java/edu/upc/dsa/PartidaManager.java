package edu.upc.dsa;

import edu.upc.dsa.exceptions.PartidaNoExiste;
import edu.upc.dsa.exceptions.PartidaYaExiste;
import edu.upc.dsa.models.Partida;

import java.util.List;

public interface PartidaManager {
    public List<Partida> getpartidaList();
    public Partida addPartida(int idPartida, int idMapa, String username) throws PartidaYaExiste;
    public Partida getpartida(int idPartida) throws PartidaNoExiste;
    public Partida startPartida(Partida partida);
    public Partida endPartida(Partida partida);
    public int size();
}
