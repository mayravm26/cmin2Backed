package edu.upc.dsa.services;

import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.orm.DAO.StoreDAO;
import edu.upc.dsa.orm.DAO.StoreDAOImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/storeBBDD", description = "Endpoint to Store Service")
@Path("/storeBBDD")

public class StoreDAOService {
    private StoreDAO sm;
    public StoreDAOService() {
        this.sm = StoreDAOImpl.getInstance();
    }
    @GET
    @ApiOperation(value = "get Product", notes = "get a product from the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/getProduct/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        Product product = sm.getProduct(id);
        if(product==null) return Response.status(404).build();
        else return Response.status(200).entity(product).build();
    }
    @GET
    @ApiOperation(value = "get Product", notes = "get a product from the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/getProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<Product> lp = this.sm.getAllProducts();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(lp) {};
        return Response.status(201).entity(entity).build()  ;
    }
    @POST
    @ApiOperation(value = "BUY", notes = "Buy a product from the Store")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product bought  Successfully"),
            @ApiResponse(code = 509, message = "Error in the buying process"),

    })
    @Path("/buy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyProduct(Inventario inventario) {
        Inventario i = this.sm.buyProduct(inventario);
        if(i != null){
            return Response.status(201).entity(i).build();
        }
        return Response.status(404).entity(i).build();
    }


}
