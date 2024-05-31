package edu.upc.dsa;

import edu.upc.dsa.exceptions.PasswordIncorrecteException;
import edu.upc.dsa.exceptions.UserNameYaExiste;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {
    public User registerUser(User user) throws UserNameYaExiste;
    public User loginUser(String username, String password) throws PasswordIncorrecteException, UserNotRegisteredException;
    public List<User> getUsers();
    public List<Product> getuserInventario(String username) throws UserNotRegisteredException;
    //public void comprar(User user, Product product) throws ProductNoExiste, UserNoExiste;
    public User changePassword(String username,String currentPassword, String newPassword) throws UserNotRegisteredException;
    public User getUser (String username);
    public User changeUsername(String username, String password, String newUsername) throws UserNotRegisteredException, PasswordIncorrecteException, UserNameYaExiste;
}
