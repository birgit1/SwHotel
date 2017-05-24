
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.repo.UserRepo;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class RoomService {
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject
    private UserRepo userRepo;
    
    @Inject
    private BookingRepo bookingRepo;
    
    @Transactional
    public Room deleteRoom(Room room)
    {
        try{
            List<Room> roomList = new ArrayList<>();
            roomList.add(room);
        List<Booking> hotelBookings = bookingRepo.getBookingsForRooms(roomList);
        for(Booking b: hotelBookings)
        {
            userRepo.removeBooking(b.getUser(), b);
            bookingRepo.delete(b);
        }
        roomRepo.delete(room);
        return room;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
