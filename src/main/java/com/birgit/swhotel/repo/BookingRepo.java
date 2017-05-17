
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomBooking;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class BookingRepo 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    @Inject
    private Logger logger;
    
    // Schreibzugriff
    @Transactional
    public Booking addBooking(Booking booking)
    {
        entityManager.persist(booking);
        
        RoomBooking roomBookings = new RoomBooking(booking.getRoom(), 
        booking.getArrival(), booking.getNights(), booking);
        entityManager.persist(roomBookings);
        
        return booking;
    }
    
    @Transactional
    public Booking deleteBooking(Booking booking)
    {
        booking = entityManager.merge(booking);
        entityManager.remove(booking);
        
        /*long bookingId = booking.getId();
        String queryString = "SELECT rb FROM RoomBooking rb WHERE rb.booking.id = :parameter1";
        TypedQuery query = entityManager.createQuery(queryString, RoomBooking.class);
        query.setParameter("parameter1", bookingId);
        List<RoomBooking> roomBookings = query.getResultList();
        
        if(roomBookings.size() > 0)
        {    
            RoomBooking roomBooking = roomBookings.get(0);
            roomBooking = entityManager.merge(roomBooking);
            entityManager.remove(roomBooking);
        }*/
       
        return booking;
    }
    
    // Lesezugriff
    public List<Booking> getAllBookings()
    {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking AS b", Booking.class);
        List<Booking> result = query.getResultList();
        return result;
    }
    
    
    public List<Room> getBookingsForRooms(List<Room> rooms, Date arrivalDate, int nights)
    {
        List<Room> availableRooms = new ArrayList<>();
        
        for(int i=0; i<rooms.size(); i++)
        {
            long roomId = rooms.get(i).getId();
             List<RoomBooking> roomBookings;
            try{
            TypedQuery query = entityManager.
                    createQuery("Select r From RoomBooking r Where r.room.id =: parameter1", RoomBooking.class);
            query.setParameter("parameter1", roomId);
            roomBookings = query.getResultList();
            }
            catch(Exception e)
            {
                logger.info("booking repo##########: query fail");
                e.printStackTrace();
                return null;
            }
        
            if(roomBookings.size() > 0)
            {
                boolean available = true;
                for(int j=0; j<roomBookings.size(); j++)
                {
                     available = roomBookings.get(j).compareRoomAvailability(arrivalDate, nights);
                     if(available == false)
                         break;
                }
                if(available == true)
                {
                    availableRooms.add(roomBookings.get(i).getRoom());
                }
            }
        }
        return availableRooms;
    }
    
}
