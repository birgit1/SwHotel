
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@RequestScoped
public class BookingRepo extends SingleIdEntityRepository implements Serializable
{
    @Inject
    private RoomRepo roomRepo;
    
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
        return bookings;   
    }
    
    @Transactional
    public List<Room> getAvailableRooms(List<Room> rooms, java.util.Date arrivalDate, int nights)
    {
        List<Room> availableRooms = new ArrayList<>();
        for(int i=0; i<rooms.size(); i++)
        {
            List<Booking> roomBookings = getRoomBookings(rooms.get(i));
            
            boolean available = true;
            // there are already bookings for that room
            if(roomBookings != null && roomBookings.size() > 0)
            {
                // compare bookings date
                for(int j=0; j<roomBookings.size(); j++)
                {
                     available = roomBookings.get(j).compareRoomAvailability(arrivalDate, nights);
                     if(available == false)
                     {
                         break;
                     }
                }
            }
            if(available == true)
            {
                    Room room = (Room) roomRepo.getById(rooms.get(i).getId());
                    availableRooms.add(room);
            }
        }
        return removeDoubleItems(availableRooms);
    }
    
    public List<Booking> getBookingsByUser(User user)
    {
        TypedQuery<Booking> query = this.getEntityManager().createQuery(
            "SELECT b FROM Booking b WHERE  b.user.id = :parameter1", Booking.class);
            query.setParameter("parameter1", user.getId());
  
            List<Booking> bookings = query.getResultList();
            return bookings;
    }
    
    private List<Booking> getRoomBookings(Room room)
    {
        long roomId = room.getId();
            // get all bookings for one room
            TypedQuery<Booking> query = this.getEntityManager().createQuery(
            "SELECT b FROM Booking b WHERE  b.room.id = :parameter1", Booking.class);
            query.setParameter("parameter1", roomId);
  
            List<Booking> roomBookings = query.getResultList();
            return roomBookings;
    }
    
    private List<Room> removeDoubleItems(List<Room> list)
    {
        if(list == null)
            return null;
        List<Room> list2 = new ArrayList<>();
        HashSet<String> lookup = new HashSet<>();
        for (Room item : list) 
        {
            if (lookup.add(item.getRoomType().getRoomName())) 
            {
                 list2.add(item);
            }
        }
        return list2;
    }
}
