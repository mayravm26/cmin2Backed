package edu.upc.dsa.services;

import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.exceptions.PasswordIncorrecteException;
import edu.upc.dsa.exceptions.UserNameYaExiste;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Credencials;
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

    @Api(value = "/user", description = "Endpoint to User Service")
    @Path("/user")
    public class UserService {

        private UserManager um;

        public UserService() throws UserNameYaExiste {
            this.um = UserManagerImpl.getInstance();
            if (um.getUsers().size()==0){
                um.registerUser(new User(1,"Laura","Fernandez","lauraa8","12345"));
                um.registerUser(new User(2,"Anna","Fernandez","annaa11","56789"));
                um.registerUser(new User(3,"Lidon","Garcia","lidon11","56789"));
                um.registerUser(new User(4,"Lidia","Esquius","lidia22","12345"));
            }

        }

        @POST
        @ApiOperation(value = "User Registration", notes = "Registrem a un usuari")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "User register Successfull"),
                @ApiResponse(code = 404, message = "This username is already being used"),
                @ApiResponse(code = 500, message = "Error")

        })
        @Path("/register")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response register(User user) throws UserNameYaExiste {
            //if (user.getName() == null || user.getSurname()==null || user.getPassword()== null || user.getUsername() == null)  return Response.status(500).entity(user).build();
            try{
                this.um.registerUser(new User(user.getIdUser(), user.getName(), user.getSurname(), user.getUsername(), user.getPassword()));
                return Response.status(201).entity(user).build();
            }
            catch(UserNameYaExiste e){
                return Response.status(404).entity(user).build();
            }

        }

        @POST
        @ApiOperation(value = "User Log in", notes = "Fem login d'un usuari")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "User logg in Successfull"),
                @ApiResponse(code = 404, message = "The password is incorrect"),

        })
        @Path("/login")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response login(Credencials credencials) throws PasswordIncorrecteException, UserNotRegisteredException {
            User user1 = this.um.loginUser(credencials.getUsername(), credencials.getPassword());
            if(user1 != null){
                return Response.status(201).entity(user1).build();
            }
            return Response.status(404).entity(user1).build();
        }
        @GET
        @ApiOperation(value = "get users", notes = "Show a list of users")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
        })
        @Path("/getusers")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getListofUsers() {

            List<User> lu = this.um.getUsers();
            GenericEntity<List<User>> entity = new GenericEntity<List<User>>(lu) {};
            return Response.status(201).entity(entity).build()  ;

        }
        @GET
        @ApiOperation(value = "get a User", notes = "get a user by the id")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = User.class),
                @ApiResponse(code = 404, message = "Track not found")
        })
        @Path("/getUser/{username}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getUser(@PathParam("username") String username) {
            User p = this.um.getUser(username);
            if (p == null) return Response.status(404).build();
            else  return Response.status(201).entity(p).build();
        }


        @GET
        @ApiOperation(value = "get inventario", notes = "Show the inventario of a user")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
        })
        @Path("/getInventario")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventario(@QueryParam("username") String username) throws UserNotRegisteredException {

            List<Product> inventario = this.um.getuserInventario(username);
            GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(inventario) {};
            return Response.status(201).entity(entity).build()  ;
        }

        @PUT
        @ApiOperation(value = "update pasword", notes= "update a password from a user")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successfull"),
                @ApiResponse(code = 404, message = "user not found")
        })
        @Path("/changePassword")
        public Response changePassword(@QueryParam("username") String username, @QueryParam("currentPassword") String currentPassword, @QueryParam("newPassword") String newPassword) throws UserNotRegisteredException {
            try{
                User user1 = this.um.changePassword(username, currentPassword, newPassword);
                return Response.status(201).entity(user1).build();
            }
            catch(UserNotRegisteredException e){
                return Response.status(404).build();
            }


        }

        @PUT
        @ApiOperation(value = "update username", notes= "update the username from a user")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successfull"),
                @ApiResponse(code = 404, message = "user not found"),
                @ApiResponse(code = 502, message = "paswword incorretce"),
                @ApiResponse(code = 501, message = "Username ya utilitzat, siusplau escriu un altre")
        })
        @Path("/updateUsername")
        public Response updateUsername(@QueryParam("username") String username, @QueryParam("Password") String Password, @QueryParam("newUsername") String newUsername) throws UserNotRegisteredException, PasswordIncorrecteException {

            try{
                User user1 = this.um.changeUsername(username, Password, newUsername);
                return Response.status(201).entity(user1).build();
            }
            catch(UserNotRegisteredException e){
                return Response.status(404).entity(e.getMessage()).build();
            }
            catch(PasswordIncorrecteException e){
                return Response.status(502).entity(e.getMessage()).build();
            } catch (UserNameYaExiste e) {
                return Response.status(501).entity(e.getMessage()).build();
            }


        }

    }
