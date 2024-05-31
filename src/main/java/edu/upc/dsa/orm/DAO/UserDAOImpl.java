package edu.upc.dsa.orm.DAO;

import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.exceptions.PasswordIncorrecteException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    private static IUserDAO instance;
    private HashMap<String, User> MapUsers;
    public List<User> listusers;
    private HashMap<String, Product> inventario;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);
    private UserDAOImpl(){
        this.listusers = new ArrayList<>();
        this.MapUsers = new HashMap<>();
    }
    public static IUserDAO getInstance(){
        if (instance==null) instance = new UserDAOImpl();
        return instance;
    }
    public int size(){
        int ret = this.MapUsers.size();
        logger.info("size " + ret);
        return ret;
    }
    @Override
    public User registerUser(User user)  {

            Session session = null;
            try {
                session = FactorySession.openSession();
                session.save(user);
                this.listusers.add(user);
                logger.info("usuari afegit");
                return user;

            } catch (Exception e){
                e.printStackTrace();
            }
            finally {
                session.close();
            }
            return null;

    }

    @Override
    public User loginUser(String username, String password)  {
      Session session = null;
      User user = null;
      try{
          session = FactorySession.openSession();
          user = (User) session.getbyTwoParameters(User.class, username, "username", password, "password");
          if (user!=null) {
              logger.info(user + " rebut!");
              return user;
          }
      }
      catch (Exception e) {
          logger.warn("not found "+ user);
          e.printStackTrace();
      }
      finally {
          session.close();
      }
        return null;
    }

    @Override
    public List<User> getUsers() {
       Session session = null;
       List<User> listusers = null;
       try{
           session = FactorySession.openSession();
           listusers = session.findAll(User.class);
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           session.close();
       }
       return listusers;
    }

    @Override
    public User getUser(String username) {
        Session session = null;

        try {
            session = FactorySession.openSession();
            User user = (User) session.get(User.class, "username", username);
            logger.info("getUser(" + username + "): " + user);
            return user;

        } catch (Exception e){
            e.printStackTrace();
        }

        logger.info("not found " + username);
        return null;

    }

    @Override
    public User getUserbyID(int idUser) {
        Session session = null;

        try {
            session = FactorySession.openSession();
            User user = (User) session.get(User.class, "idUser", idUser);
            logger.info("getUser(" + idUser + "): " + user);
            return user;

        } catch (Exception e){
            e.printStackTrace();
        }

        logger.info("not found " + idUser);
        return null;
    }

    @Override
    public double getCoins(int idUser) {
        User user = getUserbyID(idUser);
        double coins = user.getCoins();
        return coins;
    }

    @Override
    public void setCoins(int idUser, double coins) {
        User user = getUserbyID(idUser);
        user.setCoins(coins);
        String mycoins = String.valueOf(coins);
        String myidUser = String.valueOf(idUser);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.updatebyTwoParameter(User.class, mycoins, "coins", myidUser, "idUser");
            logger.info("coins set");

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }

    @Override
    public List<Product> getuserInventario(String username) throws UserNotRegisteredException {
        return null;
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) throws PasswordIncorrecteException, UserNotRegisteredException {
        String password = newPassword;
        User user = getUser(username);
        if(user.getPassword().equals(oldPassword)){
            Session session = null;
            try {
                session = FactorySession.openSession();
                session.updatebyTwoParameter(User.class,password,"password", username,"username");
                User user1 = getUser(username);
                return user1;

            } catch (Exception e){
                e.printStackTrace();
            }
            finally {
                session.close();
            }
        }

        return null;
    }

    /*@Override
    public void comprar(User user, Product product) throws ProductNoExiste, UserNoExiste {
        if(user == null ) {
            logger.info("Comprovem que l'usuari existeix");
            throw new UserNoExiste();
        } else if (product == null) {
            logger.info("Comprovem que el producte existeix");
            throw new ProductNoExiste();
        }
        else{
            String idUser = user.getIdUser();
            inventario.put(idUser, product);
        }

    }*/
}
