package edu.upc.dsa;

import edu.upc.dsa.exceptions.PasswordIncorrecteException;
import edu.upc.dsa.exceptions.UserNameYaExiste;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManagerImpl implements UserManager{
    private static UserManager instance;
    private HashMap<String, User> MapUsers;
    public List<User> listusers;
    //private HashMap<String, Product> inventario;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);
    UserManagerImpl(){
        this.listusers = new ArrayList<>();
        this.MapUsers = new HashMap<>();
    }
    public static UserManager getInstance(){
        if (instance==null) instance = new UserManagerImpl();
        return instance;
    }
    public int size(){
        int ret = this.MapUsers.size();
        logger.info("size " + ret);
        return ret;
    }


    @Override
    public User registerUser(User user) throws UserNameYaExiste {
        logger.info("Comprovem que no hi ha un nom d'usuari ja existent igual");
        User user1 = MapUsers.get(user.getUsername());
        if(user1 != null){
            logger.error("Aquest username ya s'esta utilitzant");
            throw new UserNameYaExiste();
        }
        logger.info("User registrat");
        listusers.add(user);
        MapUsers.put(user.getUsername(), user);
        return user;

    }

    @Override
    public User loginUser(String username, String password) throws PasswordIncorrecteException, UserNotRegisteredException {

        User user = MapUsers.get(username);
        if(user != null){
            logger.info("L'usuari existeix");
            if(!password.equals(user.getPassword())){
                logger.warn("Password incorrecte");
                throw new PasswordIncorrecteException();
            }
            logger.warn("User logged in");
            return user;
        }
        else{
            logger.warn("User no registrat");
            throw new UserNotRegisteredException();
        }

    }

    @Override
    public List<User> getUsers() {
        return listusers;
    }

    @Override
    public List<Product> getuserInventario(String username) throws UserNotRegisteredException {
        User user = MapUsers.get(username);
        //List<Product> inventario = user.getInventario();
        //return inventario;
        return null;
    }

    @Override
    public User changePassword(String username, String currentPassword, String newPassword) throws UserNotRegisteredException {
        User user = MapUsers.get(username);
        if(user.getPassword().equals(currentPassword)){
            user.setPassword(newPassword);
            return user;
        }
        else{
            logger.warn("User no registrat");
            throw new UserNotRegisteredException();
        }
    }

    @Override
    public User getUser(String username) {
        logger.info("getUser("+username+")");
        for (User u: this.listusers) {
            if (u.getUsername().equals(username)) {
                logger.info("getUser("+username+"): "+ u);
                return u;
            }
        }
        logger.warn("not found " + username);
        return null;
    }

    @Override
    public User changeUsername(String username, String password, String newUsername) throws UserNotRegisteredException, PasswordIncorrecteException, UserNameYaExiste {
        User user1 = MapUsers.get(newUsername);
        if(listusers.contains(user1)){
            logger.info("Aquest username ja s'esta utilitzant");
            throw new UserNameYaExiste();
        }
        User user = getUser(username);
        if (user != null) {
            if(user.getPassword().equals(password)) {
                user.setUsername(newUsername);
                return user;
            }
            throw new PasswordIncorrecteException();
        }
        throw new UserNotRegisteredException();
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
