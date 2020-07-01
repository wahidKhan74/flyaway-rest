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

import com.simplilearn.flight.flyaway.entity.FlightBooking;
import com.simplilearn.flight.flyaway.entity.dao.FlightBookingDAO;


@Path("/flightBookings")
	public class FlightBookingResource {
		@GET
	    @Produces("application/json")
	    public List<FlightBooking> getFlightBooking() {
	        FlightBookingDAO dao = new FlightBookingDAO();
	        List FlightBookings = dao.getFlightBookings();
	        return FlightBookings;
	    }
	 
	    @POST
	    @Path("/create")
	    @Consumes("application/json")
	    
	    public Response addAirport(FlightBooking flightbooking){
	    	flightbooking.setId(flightbooking.getId());
	    	flightbooking.setPassenger(flightbooking.getPassenger());
	    	FlightBookingDAO dao = new FlightBookingDAO();
	        dao.addFlightBooking(flightbooking);
	        
	        return Response.ok().build();
	    }
	    
	    @PUT
	    @Path("/update/{2}")
	    @Consumes("application/json")
	    public Response updateFlightBooking(@PathParam("id") int id, FlightBooking flightbooking){
	    	FlightBookingDAO dao = new FlightBookingDAO();
	        int count = dao.updateFlightBooking(id, flightbooking);
	        if(count==0){
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        }
	        return Response.ok().build();
	    }
	    
	    @DELETE
	    @Path("/delete/{3}")
	    @Consumes("application/json")
	    public Response deleteFlight(@PathParam("id") int id){
	    	FlightBookingDAO dao = new FlightBookingDAO();
	        int count = dao.deleteFlightBooking(id);
	        if(count==0){
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        }
	        return Response.ok().build();
	    }
	}







