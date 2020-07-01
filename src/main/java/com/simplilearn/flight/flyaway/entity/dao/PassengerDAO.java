package com.simplilearn.flight.flyaway.entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simplilearn.flight.flyaway.entity.Passenger;
import com.simplilearn.flight.flyaway.entity.util.SessionUtil;

public class PassengerDAO {
	
	public void addPassenger(Passenger bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addPassenger(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addPassenger(Session session, Passenger bean){
        Passenger passenger = new Passenger();
      passenger.setEmail(bean.getEmail());
      passenger.setFirstName(bean.getFirstName());
      passenger.setLastName(bean.getLastName());
      passenger.setPassword(bean.getPassword());
        session.save(passenger);
    }
    
    public List<Passenger> getPassengers(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Passenger");
        List<Passenger> passengers =  query.list();
        session.close();
        return passengers;
    }
    
    public Passenger getPassengerByEmail(Passenger passenger){ 
    	Passenger passengers = null;
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Passenger where email=: email");
        query.setParameter("email", passenger.getEmail());
        try {
        	passengers =  (Passenger)query.getResultList().get(0);
		} catch (Exception e) {
			passengers = null;
		}
        
        session.close();
        return passengers;
    }
    
    public int deletePassenger(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Passenger where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
 
	}
}



