
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class BookingService 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    // Schreibzugriff
    @Transactional
    public Booking addBooking(Booking booking)
    {
        entityManager.persist(booking);
        return booking;
    }
    
    // Lesezugriff
    public List<Booking> getAllBookings()
    {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking AS b", Booking.class);
        List<Booking> result = query.getResultList();
        return result;
    }
    
    
}
