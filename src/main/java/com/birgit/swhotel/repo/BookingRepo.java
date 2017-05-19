
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@RequestScoped
public class BookingRepo extends SingleIdEntityRepository implements Serializable
{
    @Inject
     RoomRepo roomRepo;
    
    @Inject
    private Logger logger;
    
    public BookingRepo()
    {
        super(Booking.class);
    }

    @Transactional
    public List<Booking> getBookingsForRooms(List<Room> rooms)
    {
        List<Booking> bookings = new ArrayList<>();
        for(int i=0; i<rooms.size(); i++)
        {
            long roomId = rooms.get(i).getId();
            // find the bookings for each room
            TypedQuery<Booking> query = this.getEntityManager().createQuery(
            "SELECT b FROM Booking b WHERE  b.room.id = :parameter1", Booking.class);
            query.setParameter("parameter1", roomId);
            List<Booking> roomBookings = query.getResultList();
            if(roomBookings != null && roomBookings.size() > 0)
            {
                for (Booking roomBooking : roomBookings) {
                    bookings.add(roomBooking);
                }
            }
        }
        logger.info("bookings found for room: "+bookings.size());
        return bookings;   
    }
    
    @Transactional
    public List<Room> getAvailableRooms(List<Room> rooms, java.util.Date arrivalDate, int nights)
    {
        List<Room> availableRooms = new ArrayList<>();
        for(int i=0; i<rooms.size(); i++)
        {
            long roomId = rooms.get(i).getId();
            TypedQuery<Booking> query = this.getEntityManager().createQuery(
            "SELECT b FROM Booking b WHERE  b.room.id = :parameter1", Booking.class);
            query.setParameter("parameter1", roomId);
            List<Booking> roomBookings = query.getResultList();
            logger.info("bookings for room: "+roomBookings.size());
            
            boolean available = true;
            if(roomBookings != null && roomBookings.size() > 0)
            {
                for(int j=0; j<roomBookings.size(); j++)
                {
                     available = roomBookings.get(j).compareRoomAvailability(arrivalDate, nights);
                     if(available == false)
                     {
                         logger.info("availability set false");
                         break;
                     }
                }
            }
            if(available == true)
            {
                    Room room = (Room) roomRepo.getById(roomId);
                    availableRooms.add(room);
            }
        }
        logger.info("repo: #available rooms: "+availableRooms.size());
        return availableRooms;
    }
    
}
