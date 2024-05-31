package edu.upc.dsa;

import edu.upc.dsa.exceptions.ProductNoExiste;
import edu.upc.dsa.exceptions.ProductYaExiste;
import edu.upc.dsa.exceptions.UserNoExiste;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface StoreManager {
    public List<Product> getProducts();
    //public List<User> getUsers();
    public Product getProduct(String id);
    public void deleteP(String id);
    public void addProduct(String id,String name, String description, double price, String imatge) throws ProductYaExiste;
    public void deleteProduct(Product product) throws ProductNoExiste;
    public boolean comprar(User user, Product product) throws ProductNoExiste, UserNoExiste;
    public List<Product> buyProduct(String username, String idProduct) throws ProductNoExiste, UserNoExiste;
}
