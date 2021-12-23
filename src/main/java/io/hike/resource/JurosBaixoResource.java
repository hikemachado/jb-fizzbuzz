package io.hike.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hike.usecases.TreasureUseCase;

@Path("/jb")
public class JurosBaixoResource {

    Logger logger = LoggerFactory.getLogger(JurosBaixoResource.class);

    @Inject
    TreasureUseCase treasureUseCase; 

    @GET
    @Path("/findtreasure")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findTreasure(){
        logger.info("Start treaseure...");
        String response = treasureUseCase.discoverTreasure();
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/resettreasure")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reset(){
        return Response.ok(treasureUseCase.resetTreasure()).build();
    }
    
}
