package edu.upc.dsa;

import edu.upc.dsa.exceptions.PartidaNoExiste;
import edu.upc.dsa.exceptions.PartidaYaExiste;
import edu.upc.dsa.models.Partida;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PartidaManagerImpl implements PartidaManager {
    private static PartidaManager instance;
    public List<Partida> listPartidas;

    final static Logger logger = Logger.getLogger(MapaManagerImpl.class);
    public PartidaManagerImpl(){
        this.listPartidas = new ArrayList<>();
    }
    public static PartidaManager getInstance(){
        if (instance==null) instance = new PartidaManagerImpl();
        return instance;
    }

    @Override
    public List<Partida> getpartidaList() {
        return listPartidas;
    }

    @Override
    public Partida addPartida(int idPartida, int idMapa, String username) throws PartidaYaExiste {

        if(listPartidas.contains(idPartida)){
            logger.warn("La partida ja existeix");
            throw new PartidaYaExiste();
        }
        else {
            Partida p = new Partida(idPartida, idMapa, username);
            listPartidas.add(p);
            logger.info("added partida: " + idPartida);
            return p;
        }
    }

    @Override
    public Partida getpartida(int idPartida) throws PartidaNoExiste {
        boolean found = listPartidas.contains(idPartida);
        if(found) {
            logger.info("found " + idPartida);
            return listPartidas.get(idPartida);
        }
        else{
            logger.warn("not found " + idPartida);
            throw new PartidaNoExiste();
        }
    }

    @Override
    public Partida startPartida(Partida partida) {
        return null;
    }

    @Override
    public Partida endPartida(Partida partida) {
        return null;
    }

    @Override
    public int size() {
        int ret = this.listPartidas.size();
        logger.info("size " + ret);
        return ret;
    }
}
