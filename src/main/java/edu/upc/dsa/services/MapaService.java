package edu.upc.dsa.services;

import edu.upc.dsa.MapaManager;
import edu.upc.dsa.MapaManagerImpl;
import edu.upc.dsa.exceptions.MapaNomYaExisteix;
import edu.upc.dsa.models.Mapa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/mapa", description = "Endpoint to Mapa Service")
@Path("/mapa")
public class MapaService {
    private MapaManager mm;
    public MapaService() throws MapaNomYaExisteix {
        this.mm = MapaManagerImpl.getInstance();
        if(mm.getmapalist().size()==0){
            mm.addmapa(1,10,10,500,2,"Júpiter");
            mm.addmapa(2, 20,5,550,1,"Saturn");
            mm.addmapa(3, 30,15,600,3,"Mercuri");
        }
    }
    @GET
    @ApiOperation(value = "get Mapas", notes = "Show a list of mapas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mapa.class, responseContainer="List"),
    })
    @Path("/getMapas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMapas() {
        List<Mapa> mapas = this.mm.getmapalist();
        GenericEntity<List<Mapa>> entity = new GenericEntity<List<Mapa>>(mapas) {};
        return Response.status(201).entity(entity).build();
    }
    @POST
    @ApiOperation(value = "Add a new Map", notes = "Afegim un nou mapa")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Map added Successfully"),
            @ApiResponse(code = 500, message = "The fields have been wrongly filled in"),


    })
    @Path("/addMapa")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMap(Mapa mapa) throws MapaNomYaExisteix {
        if (mapa.getIdMapa() == 0 || mapa.getName() == null || mapa.getEnemies() == 0 || mapa.getFoodElements() == 0 || mapa.getFuelElements() == 0 || mapa.getFuelRequirement() == 0)  return Response.status(500).entity(mapa).build();
        this.mm.addmapa(mapa.getIdMapa(), mapa.getFoodElements(), mapa.getFuelElements(), mapa.getFuelRequirement(), mapa.getEnemies(), mapa.getName());
        return Response.status(201).entity(mapa).build();
    }
}
