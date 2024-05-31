package edu.upc.dsa;

import edu.upc.dsa.exceptions.MapaNoExiste;
import edu.upc.dsa.exceptions.MapaNomYaExisteix;
import edu.upc.dsa.models.Mapa;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MapaManagerImpl implements MapaManager{
    private static MapaManager instance;
    public List<Mapa> listmapas;

    final static Logger logger = Logger.getLogger(MapaManagerImpl.class);
    public MapaManagerImpl(){
        this.listmapas = new ArrayList<>();
    }
    public static MapaManager getInstance(){
        if (instance==null) instance = new MapaManagerImpl();
        return instance;
    }
    public int size(){
        int ret = this.listmapas.size();
        logger.info("size " + ret);
        return ret;
    }
    @Override
    public List<Mapa> getmapalist() {
        return listmapas;
    }

    @Override
    public Mapa getmapa(int idMapa) throws MapaNoExiste {
        boolean found = listmapas.contains(idMapa);
        if(found) {
            logger.info("found " + idMapa);
            return listmapas.get(idMapa);
        }
        else{
            logger.warn("not found " + idMapa);
            throw new MapaNoExiste();
        }
    }

    @Override
    public Mapa addmapa(int idMapa, int foodElements, int fuelElements, int fuelRequirement, int enemies, String name) throws MapaNomYaExisteix {
        if(listmapas.contains(name)){
            logger.warn("El nom del mapa ja existeix");
            throw new MapaNomYaExisteix();
        }
        else {
            Mapa mapa = new Mapa(idMapa, foodElements, fuelElements, fuelRequirement, enemies, name);
            listmapas.add(mapa);
            logger.info("added mapa: " + mapa.getIdMapa());
            return mapa;
        }
    }

    @Override
    public Mapa deletemapa(int id) throws MapaNoExiste {
        Mapa mapa = listmapas.remove(id);
        if(mapa!=null){
            logger.info("Mapa eliminado: " + mapa.getIdMapa());
            return mapa;
        }
        else{
            logger.warn("Mapa no existeix");
            throw new MapaNoExiste();
        }
    }
}
