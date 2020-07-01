package com.simplilearn.flight.flyaway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;

import com.simplilearn.flight.flyaway.entity.FlightBooking;
import com.simplilearn.flight.flyaway.entity.Passenger;
import com.simplilearn.flight.flyaway.entity.Resp;
import com.simplilearn.flight.flyaway.entity.dao.FlightBookingDAO;
import com.simplilearn.flight.flyaway.entity.dao.PassengerDAO;


@Path("/passengers")
public class PassengerResource extends ResourceConfig {
	@GET
    @Produces("application/json")
    public List<Passenger> getPassenger() {
		System.out.println("Connection Success");
        PassengerDAO dao = new PassengerDAO();
        List Passengers = dao.getPassengers();
        return Passengers;
    }
 
    @POST
    @Path ("/register")
    @Consumes("application/json")
	@Produces("application/json")
    public Response addPassenger(Passenger Passenger){
    	Resp response = new Resp();            
        PassengerDAO dao = new PassengerDAO();
        dao.addPassenger(Passenger);
        response.setStatus(true);
		response.setMessage("Registration Successfull");
		response.setToken(UUID.randomUUID().toString());
        return Response
                .status(200)
                .entity(response)
      	      	.type(MediaType.APPLICATION_JSON)
                .build();
    }
    
    @POST
    @Path ("/login")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginPassenger(Passenger Passenger){
    	Resp response = new Resp();          
        PassengerDAO dao = new PassengerDAO();
        Passenger loadedPassenger = dao.getPassengerByEmail(Passenger);
        if(loadedPassenger !=null) {
        	if(loadedPassenger.getPassword().equals(Passenger.getPassword())) {
        		response.setStatus(true);
        		response.setMessage("Login Successfull");
        		response.setToken(UUID.randomUUID().toString());
        	}else {
        		response.setStatus(false);
        		response.setMessage("Login Faild Invalid Password");
        	}
        }
        return Response
                .status(200)
                .entity(response)
      	      	.type(MediaType.APPLICATION_JSON)
                .build();
    }
    
  // @PUT
   //@Path("/{update/{2}")
   // @Consumes("application/json")
    //public Response updatePassenger(@PathParam("id") int id, Passenger Passenger){
       // PassengerDAO dao = new PassengerDAO();
       // int count = dao.updatePassenger(id, Passenger);
       // if(count==0){
        //    return Response.status(Response.Status.BAD_REQUEST).build();
       // }
       // return Response.ok().build();
    //}
    
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public Response deletePassenger(@PathParam("id") int id){
        PassengerDAO dao = new PassengerDAO();
        int count = dao.deletePassenger(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
   
}
