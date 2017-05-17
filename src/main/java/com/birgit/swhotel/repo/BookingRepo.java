
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
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
    private RoomRepo roomRepo;
    
    @Inject
    private Logger logger;
    
    // Schreibzugriff
    @Transactional
    public Booking addBooking(Booking booking)
    {
        entityManager.persist(booking);
        return booking;
    }
    
    @Transactional
    public Booking deleteBooking(Booking booking)
    {
        booking = entityManager.merge(booking);
        entityManager.remove(booking);
        return booking;
    }
    
    // Lesezugriff
    public List<Booking> getAllBookings()
    {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking AS b", Booking.class);
        List<Booking> result = query.getResultList();
        return result;
    }
    
    @Transactional
    public List<Room> getAvailableRooms(List<Room> rooms, Date arrivalDate, int nights)
    {
        List<Room> availableRooms = new ArrayList<>();
     
        for(int i=0; i<rooms.size(); i++)
        {
            long roomId = rooms.get(i).getId();
            // find the bookings for each room
            TypedQuery<Booking> query = entityManager.createQuery(
            "SELECT b FROM Booking b WHERE  b.room.id = :parameter1", Booking.class);
            query.setParameter("parameter1", roomId);
            List<Booking> roomBookings = query.getResultList();
            logger.info("bookings found for room: "+roomBookings.size());
            
        
            // check if one of the bookings is at the same time of the reservation date
            boolean available = true;
            if(roomBookings == null || roomBookings.size() > 0)
            {
                logger.info("some roombookings");
                // there are bookings for that room
                for(int j=0; j<roomBookings.size(); j++)
                {
                     available = roomBookings.get(j).compareRoomAvailability(arrivalDate, nights);
                     if(available == false)
                         break;
                }
            }
            if(available == true)
                {
                    Room room = roomRepo.getRoomById(roomId);
                    
                    logger.info("add room as available: "+room.getId());
                    availableRooms.add(room);
                }
        }
        logger.info("rooms avaiable");
        return availableRooms;
    }
    
}
