package edu.upc.dsa;

import edu.upc.dsa.exceptions.MapaNoExiste;
import edu.upc.dsa.exceptions.MapaNomYaExisteix;
import edu.upc.dsa.models.Mapa;

import java.util.List;

public interface MapaManager {
    public List<Mapa> getmapalist();
    public Mapa getmapa(int id) throws MapaNoExiste;
    public Mapa addmapa(int idMapa, int foodElements, int fuelElements, int fuelRequirement, int enemies, String name) throws MapaNomYaExisteix;
    public Mapa deletemapa(int id) throws MapaNoExiste;
    public int size();
}
