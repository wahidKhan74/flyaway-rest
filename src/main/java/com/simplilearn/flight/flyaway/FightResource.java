package com.simplilearn.flight.flyaway;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.simplilearn.flight.flyaway.entity.Flight;
import com.simplilearn.flight.flyaway.entity.dao.FlightDAO;

@Path("/flights")
public class FightResource {
	
	@GET
    @Produces("application/json")
    public List<Flight> getFlight() {
        FlightDAO dao = new FlightDAO();
        List Flights = dao.getFlights();
        return Flights;
    }
 
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    
    public Response addAirport(Flight flight){
    	flight.setId(flight.getId());
    	flight.setDeparture(flight.getDeparture());
    	flight.setArrival(flight.getArrival());
    	flight.setDepartureDate(flight.getDepartureDate());
    	flight.setArrivalDate(flight.getArrivalDate());
   
    	FlightDAO dao = new FlightDAO();
        dao.addFlight(flight);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{2}")
    @Consumes("application/json")
    public Response updateFlight(@PathParam("id") int id, Flight flight){
    	FlightDAO dao = new FlightDAO();
        int count = dao.updateFlight(id, flight);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{3}")
    @Consumes("application/json")
    public Response deleteFlight(@PathParam("id") int id){
    	FlightDAO dao = new FlightDAO();
        int count = dao.deleteFlight(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}

