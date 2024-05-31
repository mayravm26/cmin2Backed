package edu.upc.dsa.services;

import edu.upc.dsa.StoreManager;
import edu.upc.dsa.StoreManagerImpl;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/store", description = "Endpoint to Store Service")
@Path("/store")
public class StoreService {
    private StoreManager sm;
    private UserManager um;
    public StoreService() throws ProductYaExiste, UserNameYaExiste, UserNotRegisteredException {
        this.sm = StoreManagerImpl.getInstance();
        this.um = UserManagerImpl.getInstance();
        if(sm.getProducts().size()==0){
            sm.addProduct("1","Martillo","Tool to save the alien",15, "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/2/25/Mjolnir.png");
            sm.addProduct("2","Pico","Tool to save the alien",20, "https://cdn-icons-png.flaticon.com/512/4813/4813227.png");
            um.registerUser(new User(1,"Laura", "Fernandez", "lauraa8", "12345"));
            um.registerUser(new User(2,"Anna", "Fernandez", "annaa11", "56789"));
        }
        /*if (sm.getProducts().size()==0){
            sm.addProduct("1","Martillo","Tool to save the alien",15);
            sm.addProduct("2","Pico","Tool to save the alien",20);
        } else if (um.getUsers().size()==0) {
            um.registerUser(new User("Laura", "Fernandez", "lauraa8", "12345"));
            um.registerUser(new User("Anna", "Fernandez", "annaa11", "56789"));
        }*/
    }
    @GET
    @ApiOperation(value = "get Store Products", notes = "Show a list of Products that are in the store")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/getStoreProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListofProducts() {
        List<Product> products = this.sm.getProducts();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build();
    }
    @POST
    @ApiOperation(value = "create a new Product", notes = "add a new product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) throws ProductYaExiste {

        if (product.getName() == null || product.getDescription() == null  || product.getPrice() == 0 || product.getIdProduct()== null || product.getImatge()==null)
            return Response.status(500).entity(product).build();
        this.sm.addProduct(product.getIdProduct(), product.getName(), product.getDescription(), product.getPrice(), product.getImatge());
        return Response.status(201).entity(product).build();
    }

    @GET
    @ApiOperation(value = "get a Product", notes = "get a product by the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/getProduct/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product p = this.sm.getProduct(id);
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Product", notes = "Delete a product from the store")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/deleteProduct/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Product p = this.sm.getProduct(id);
        if (p == null) return Response.status(404).build();
        else this.sm.deleteP(id);
        return Response.status(201).build();
    }
    @POST
    @ApiOperation(value = "buy a Product", notes = "get a product from the store")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/buyProduct/{username}/{idProduct}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyProduct(@PathParam("username") String username, @PathParam("idProduct") String idProduct) throws  ProductNoExiste, UserNoExiste {
        List<Product> inventario = this.sm.buyProduct(username, idProduct);
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(inventario) {};
        return Response.status(201).entity(entity).build();

    }

   /*@PUT
    @ApiOperation(value = "Buy a product ", notes = "Buy a product from the store")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/buy")
    public Response buyProduct(@QueryParam("username") String username, @QueryParam("idProduct") String idProduct) throws ProductYaExiste, ProductNoExiste, UserNoExiste {
        boolean comprado = this.sm.comprar(user, product);
        if (comprado) {
            return Response.status(201).entity(product).build();
        }
        else return Response.status(404).build();
   }*/




    /*
    * public Response comprarProduct(User user, Product product) throws ProductNoExiste, UserNoExiste {

        boolean comprado = this.sm.comprar(user, product);

        if (comprado){
            return Response.status(201).entity(comprado).build();
        }

        return Response.status(404).build();


    }*/

}
