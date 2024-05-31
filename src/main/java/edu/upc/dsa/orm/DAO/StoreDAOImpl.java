package edu.upc.dsa.orm.DAO;

import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StoreDAOImpl implements StoreDAO {
    private static StoreDAO instance;
    private List<Product> listproducts;

    final static Logger logger =Logger.getLogger(StoreDAOImpl.class);

    private StoreDAOImpl() {
        this.listproducts = new ArrayList<Product>();

    }
    public static StoreDAO getInstance() {
        if(instance == null) instance = new StoreDAOImpl();
        return instance;
    }

    @Override
    public Product getProduct(int idProduct) {
        Session session = null;
        try{
            session = FactorySession.openSession();
            Product product = (Product) session.get(Product.class, "idProduct", idProduct);
            logger.info("getPoduct("+idProduct+"): "+product);
            return product;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        logger.info("Product not found");
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = null;
        List<Product> listproducts = null;
        try{
            session = FactorySession.openSession();
            listproducts = session.findAll(Product.class);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return listproducts;
    }

    @Override
    public Inventario buyProduct(Inventario inventario) {
        Inventario i = new Inventario(inventario.getIdUser(), inventario.getIdProduct(), inventario.getQuantity());
        Session session = null;
        //Mirem si el usuari por comprar o no els productes
        User user = UserDAOImpl.getInstance().getUserbyID(i.getIdUser());
        Product product = getProduct(i.getIdProduct());
        int quantity = inventario.getQuantity();
        double price = getPrice(i.getIdProduct());
        double coins = user.getCoins();
        double coinsTotal = coins - (price*quantity);

        if(coinsTotal >= 0){
            try {
                session = FactorySession.openSession();
                session.save(i);
                logger.info("Producte afegit al inventari");
                //Fem la funcio per restar les coins al ususari
                UserDAOImpl.getInstance().setCoins(user.getIdUser(), coinsTotal);
                return i;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                session.close();
            }
        }
        return null;
    }

    @Override
    public double getPrice(int idProduct) {
        Product product = getProduct(idProduct);
        double price = product.getPrice();
        return price;
    }
}
