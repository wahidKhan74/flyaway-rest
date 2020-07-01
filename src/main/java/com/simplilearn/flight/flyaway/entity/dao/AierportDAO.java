package com.simplilearn.flight.flyaway.entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simplilearn.flight.flyaway.entity.Airport;
import com.simplilearn.flight.flyaway.entity.Flight;
import com.simplilearn.flight.flyaway.entity.FlightBooking;
import com.simplilearn.flight.flyaway.entity.Passenger;
import com.simplilearn.flight.flyaway.entity.util.SessionUtil;

public class AierportDAO {
	
	public void addAirport(Airport bean){
		Session session = SessionUtil.getSession();
		try {
	        Transaction tx = session.beginTransaction();
	        addAirport(session,bean);        
	        tx.commit();	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
        
        
    }
    
    private void addAirport(Session session, Airport bean){
        Airport airport = new Airport();
        airport.setCountryIsoCode(bean.getCountryIsoCode());
        airport.setIataCode(bean.getIataCode());
        airport.setName(bean.getName());        
        session.save(airport);
    }
        
	/* private void addAirport(Session session, Flight bean){
        Flight flight = new Flight();
       
        flight.setId(bean.getId());
    	flight.setDeparture(bean.getDeparture());
    	flight.setArrival(bean.getArrival());
    	flight.setDepartureDate(bean.getDepartureDate());
    	flight.setArrivalDate(bean.getArrivalDate());
    	session.save(flight); */
        
    /*private void addAirport(Session session, FlightBooking bean){
        FlightBooking flightbooking = new FlightBooking();
        flightbooking.setId(bean.getId());
    	flightbooking.setPassenger(bean.getPassenger());
    	session.save(flightbooking); */
	
	/* private void addAirport(Session session, Passenger bean){
		Passenger passenger = new Passenger();
		passenger.setId(bean.getId());
		passenger.setPassenger(bean.getPassenger());
		session.save(passenger); 
    	}*/
    
    public List<Airport> getAirports(){
        Session session = SessionUtil.getSession();
        List<Airport> airports = null;
        try {
        	 Query query = session.createQuery("from Airport");
             airports =  query.list();
             session.close();
             
		} catch (Exception e) {
			e.printStackTrace();
		}
        return airports;
    }
 
    public int deleteAirport(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Airport where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateAirport(int id, Airport airport){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Airport set name = :name, countryIsoCode=:countryIsoCode, iataCode:=iataCode where id = :id";
            Query query = session.createQuery(hql);
            
            query.setInteger("id",id);
            query.setString("iataCode",airport.getCountryIsoCode());
            query.setString("countryIsoCode",airport.getIataCode());
            query.setString("name",airport.getName());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}
