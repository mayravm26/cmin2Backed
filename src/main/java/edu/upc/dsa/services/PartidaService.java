package edu.upc.dsa.services;

import edu.upc.dsa.PartidaManager;
import edu.upc.dsa.PartidaManagerImpl;
import edu.upc.dsa.exceptions.MapaNomYaExisteix;
import edu.upc.dsa.exceptions.PartidaYaExiste;
import edu.upc.dsa.models.Partida;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/partida", description = "Endpoint to Partida Service")
@Path("/partida")
public class PartidaService {
    private PartidaManager pm;
    public PartidaService() throws PartidaYaExiste {
        this.pm = PartidaManagerImpl.getInstance();
        if(pm.getpartidaList().size()==0){
            pm.addPartida(1,1,"lauraa8");
            pm.addPartida(2,2,"anna11");
        }
    }
    @GET
    @ApiOperation(value = "get Partidas", notes = "Show a list of partidas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
    })
    @Path("/getPartidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartidas() {
        List<Partida> p = this.pm.getpartidaList();
        GenericEntity<List<Partida>> entity = new GenericEntity<List<Partida>>(p) {};
        return Response.status(201).entity(entity).build();
    }
    @POST
    @ApiOperation(value = "Add a new Partida", notes = "Afegim una nova partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida added Successfully"),
            @ApiResponse(code = 500, message = "The fields have been wrongly filled in"),


    })
    @Path("/addPartida")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPartida(Partida p) throws MapaNomYaExisteix, PartidaYaExiste {
        if (p.getIdPartida() == 0 || p.getIdMapa() ==0 || p.getUsername() == null)  return Response.status(500).entity(p).build();
        this.pm.addPartida(p.getIdPartida(), p.getIdMapa(), p.getUsername());
        return Response.status(201).entity(p).build();
    }

}
