package com.simplilearn.flight.flyaway.entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

import com.simplilearn.flight.flyaway.entity.Airport;
import com.simplilearn.flight.flyaway.entity.Flight;
import com.simplilearn.flight.flyaway.entity.FlightBooking;
import com.simplilearn.flight.flyaway.entity.Passenger;
import com.simplilearn.flight.flyaway.entity.util.SessionUtil;

public class FlightDAO {
	public void addFlight(Flight bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addFlight(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addFlight(Session session, Flight bean){
    	Flight flight = new Flight();
        flight.setId(bean.getId());
        flight.setDeparture(bean.getDeparture());
        flight.setArrival(bean.getArrival()); 
        flight.setDepartureDate(bean.getDepartureDate()); 
        flight.setArrivalDate(bean.getArrivalDate()); 
        session.save(flight);
    }
    
    public List<Flight> getFlights(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Flight");
        List<Flight> flights =  query.list();
        session.close();
        return flights;
    }
 
    public int deleteFlight(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Flight where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateFlight(int id, Flight flight){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Flight set id = :id, departure=:departure, arrival:=arrival depaturedate=: departuredate, arrivaldate=:arrivaldate where id = :id";
            Query query = session.createQuery(hql);
            
            query.setInteger("id",id);
            query.setString("id",flight.getId());
            query.setString("departure",flight.getDeparture());
            query.setString("arrival",flight.getArrival());
          query.setString("departuredate", flight.getDepartureDate());
            
            query.setString("arrivaldate",flight.getArrivalDate());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}


