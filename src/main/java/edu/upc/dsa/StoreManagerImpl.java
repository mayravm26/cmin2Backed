package edu.upc.dsa;

import edu.upc.dsa.exceptions.ProductNoExiste;
import edu.upc.dsa.exceptions.ProductYaExiste;
import edu.upc.dsa.exceptions.UserNoExiste;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StoreManagerImpl implements StoreManager{
    private static StoreManager instance;
    //public HashMap<String, Product> inventario;
    UserManagerImpl userManager = new UserManagerImpl();
    List<User> users;
    {
        UserManagerImpl.getInstance().getUsers();
    }

    public List<Product> listproducts;

    final static Logger logger = Logger.getLogger(StoreManagerImpl.class);
    private StoreManagerImpl(){
        this.listproducts = new ArrayList<>();
        this.users = new ArrayList<>();
        //this.inventario = new HashMap<>();
    }
    public static StoreManager getInstance(){
        if (instance==null) instance = new StoreManagerImpl();
        return instance;
    }

    public int size(){
        int ret = this.listproducts.size();
        logger.info("size " + ret);
        return ret;

    }
    @Override
    public List<Product> getProducts() {
        return listproducts;
    }

    @Override
    public Product getProduct(String id) {
        logger.info("getTrack("+id+")");

        for (Product p: this.listproducts) {
            if (p.getIdProduct().equals(id)) {
                logger.info("getProduct("+id+"): "+p);

                return p;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    @Override
    public void deleteP(String id) {
        Product p = this.getProduct(id);
        if (p==null) {
            logger.warn("not found " + p);
        }
        else logger.info(p +" deleted ");

        this.listproducts.remove(p);
    }


   /* @Override
    public void delete(String id) {
        Product p = this.getProduct(id);
        if (p==null) {
            logger.warn("not found " + p);
        }
        else logger.info(p +" deleted ");

        this.listproducts.remove(p);
    }*/



    @Override
    public void addProduct(String id,String name, String description, double price, String imatge) throws ProductYaExiste {
        Product p = new Product(id, name, description, price, imatge);
        String idP = p.getIdProduct();
        logger.info("Comprovem que aquest producte no esta a la nostre llista");
        if(listproducts.contains(idP)){
            logger.error("Aquest producte ja esta a la llista");
            throw new ProductYaExiste();
        }
        logger.info("producte afegit");
        listproducts.add(p);
    }

    @Override
    public void deleteProduct(Product product) throws ProductNoExiste {
        logger.info("Comprovem que el producte existeix");
        String idP = product.getIdProduct();
        if(listproducts.contains(idP)){
            logger.info("Borrem Producte");
            listproducts.remove(product);
        }
        logger.error("Aquest producte no existeix");
        throw new ProductNoExiste();
    }
    @Override
    public boolean comprar(User user, Product product) throws ProductNoExiste, UserNoExiste {
        boolean comprado = false;
        double coins = user.getCoins();
        double cost = product.getPrice();
        double coinsTotals = coins - cost;
        boolean p = listproducts.contains(product);
        if(listproducts.contains(product) & users.contains(user)){
            logger.info("Item comprat");
            comprado = true;
            //user.getInventario().add(product);
            user.setCoins(coinsTotals);
            return comprado;
        }
        else if(p == false){
            logger.info("INo existeix producte");
            throw new ProductNoExiste();
        }
        else{
            logger.info("Comprovem que l'usuari existeix");
            throw new UserNoExiste();
        }
    }

    @Override
    public List<Product> buyProduct(String username, String idProduct) throws ProductNoExiste, UserNoExiste {
        User user = userManager.getInstance().getUser(username);
        Product product = getProduct(idProduct);
        if( user.getUsername()!= null && listproducts.contains(product)){
            logger.info("Item Comprat");
            double coins = user.getCoins();
            double cost = product.getPrice();
            double coinsTotals = coins - cost;
            //user.getInventario().add(product);
            user.setCoins(coinsTotals);
            //List<Product> inventario = user.getInventario();
            return null;
            //return inventario;

        } else if (!listproducts.contains(getProduct(idProduct))) {
            logger.info("El producte no existeix");
            throw new ProductNoExiste();
        }
        logger.info("l'usuari no existeix");
        throw new UserNoExiste();

    }

}
