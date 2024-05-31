package edu.upc.dsa.services;

import edu.upc.dsa.exceptions.PasswordIncorrecteException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Credencials;
import edu.upc.dsa.models.Register;
import edu.upc.dsa.models.User;
import edu.upc.dsa.orm.DAO.IUserDAO;
import edu.upc.dsa.orm.DAO.UserDAOImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/userBBDD", description = "Endpoint to User Service")
@Path("/userBBDD")
public class UserDAOService {
    private IUserDAO um;

    public UserDAOService() {
        this.um = UserDAOImpl.getInstance();
        /*
        if (um.getUsers().size()==0){
            um.registerUser(new User(1,"Laura","Fernandez","lauraa8","12345"));
        }
        */
    }

    @POST
    @Path("/reg2")
    public Response register2(Register register) {
        User u = new User(0, register.getName(), register.getSurname(), register.getUsername(), register.getPassword());

        if(u.getUsername()==null || u.getPassword()==null) {
            return Response.status(501).entity(u).build();
        }
        User us = this.um.registerUser(u);
        if(us == null)
            return Response.status(404).build();
        else
            return Response.status(201).entity(us).build();

    }

   /* @POST
    @ApiOperation(value = "User Registration", notes = "Registrem a un usuari")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User register Successfull"),
            @ApiResponse(code = 404, message = "This username is already being used"),
            @ApiResponse(code = 501, message = "Error")

    })
    @Path("/reg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Register register) throws UserNameYaExiste {
        User u = new User(register.getIdUser(),register.getName(), register.getSurname(), register.getUsername(), register.getPassword());

        if(u.getUsername()==null || u.getPassword()==null) return Response.status(501).entity(u).build();
        User us = this.um.registerUser(u);
        if(us == null)
            return Response.status(404).build();
        else
            return Response.status(201).entity(u).build();

    }

    */

    @POST
    @ApiOperation(value = "User Log in", notes = "Fem login d'un usuari")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User logg in Successfull"),
            @ApiResponse(code = 404, message = "The password is incorrect"),

    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credencials credencials)  throws PasswordIncorrecteException, UserNotRegisteredException {
        User user1 = this.um.loginUser(credencials.username, credencials.password);
        if(user1 != null){
            return Response.status(201).entity(user1).build();
        }
        return Response.status(404).entity(user1).build();
    }
    @GET
    @ApiOperation(value = "get User", notes = "get a user from the username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {
        User user = this.um.getUser(username);
        if(user==null) return Response.status(404).build();
        else return Response.status(200).entity(user).build();
    }

    @GET
    @ApiOperation(value = "get Users", notes = "get a users from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> lu = this.um.getUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(lu) {};
        return Response.status(201).entity(entity).build()  ;
    }
    @PUT
    @ApiOperation(value = "update pasword", notes= "update a password from a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfull"),
            @ApiResponse(code = 404, message = "user not found")
    })
    @Path("/changePassword")
    public Response changePassword(@QueryParam("username") String username, @QueryParam("currentPassword") String currentPassword, @QueryParam("newPassword") String newPassword) throws UserNotRegisteredException, PasswordIncorrecteException {
        String password = newPassword;
        User user = this.um.changePassword(username, currentPassword, newPassword);
        if(user != null)
            return Response.status(201).entity(user).build();
        else
            return Response.status(404).build();


    }

}
