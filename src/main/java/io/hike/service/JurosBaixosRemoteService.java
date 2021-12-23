package io.hike.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.hike.exception.ResponseException;

@RegisterRestClient
@RegisterClientHeaders(RequestXApiKeyHeaderFactory.class)
@RegisterProvider(value = ResponseException.class,
                  priority = 50)
public interface JurosBaixosRemoteService {
    
    @GET
    @Path("/fizzbuzz")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Long[] getFizzBuzz();

    @POST
    @Path("/fizzbuzz/{shaHash}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void postFizzBuzz(@PathParam("shaHash") String shaHash, String[] fizzBuzz);

    @GET
    @Path("/fizzbuzz/{shaHash}/canihastreasure")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void getTreasure(@PathParam("shaHash") String shaHash);

    @DELETE
    @Path("/fizzbuzz/{shaHash}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteFizzBuzz(@PathParam("shaHash") String shaHash);

    @GET
    @Path("/fizzbuzz/reset")
    public void reset();
}
