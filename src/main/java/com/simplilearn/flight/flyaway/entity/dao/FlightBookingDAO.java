package com.simplilearn.flight.flyaway.entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simplilearn.flight.flyaway.entity.FlightBooking;
import com.simplilearn.flight.flyaway.entity.util.SessionUtil;


public class FlightBookingDAO {

	public void addFlightBooking(FlightBooking bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addFlightBooking(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addFlightBooking(Session session, FlightBooking bean){
    	FlightBooking flightbooking = new FlightBooking();
        flightbooking.setId(bean.getId());
        flightbooking.setPassenger(bean.getPassenger()); 
        
        session.save(flightbooking);
    }
    
    public List<FlightBooking> getFlightBookings(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from FlightBooking");
        List<FlightBooking> flightbookings =  query.list();
        session.close();
        return flightbookings;
    }
 
    public int deleteFlightBooking(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from FlightBooking where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateFlightBooking(int id, FlightBooking flightbooking){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Flight set id = :id, passenger=:passenger where id = :id";
            Query query = session.createQuery(hql);
            
            query.setInteger("id",id);
            query.setString("id",flightbooking.getId());
//            query.setString("passenger",flightbooking.getPassenger());
           
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}


