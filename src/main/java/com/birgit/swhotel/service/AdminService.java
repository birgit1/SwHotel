
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.HotelRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.repo.RoomTypeRepo;
import com.birgit.swhotel.repo.UserRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class AdminService {
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject
    private UserRepo userRepo;
    
    @Inject
    private BookingRepo bookingRepo;
    
    @Inject
    private RoomTypeRepo roomTypeRepo;
    
    @Inject
    private HotelRepo hotelRepo;
    
    @Inject 
    private Logger logger;
    
    
    @Transactional
    public Hotel addHotel(Hotel hotel)
    {
        try{
            if(hotel.getName()!= null && hotel.getPriceFactor()>0 
                    && hotel.getAddress().getCity()!= null && hotel.getAddress().getCountry()!=null)
            {
                if(!hotel.getName().equals("") && !hotel.getAddress().getCity().equals("") && !hotel.getAddress().getCountry().equals(""))
                {
                    Hotel h = (Hotel) hotelRepo.persist(hotel);
                    logger.info("hotel added "+h.getId());
                    return h;
                }
            }
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    @Transactional
    public Hotel deleteHotel(Hotel hotel)
    {
        Hotel h = (Hotel) hotelRepo.delete(hotel);
        logger.info("hotel deleted "+h.getId());
        return h;
    }
    
    @Transactional
    public RoomType addRoomType(RoomType roomType)
    {
        try{
            if(roomType.getRoomName()!= null && roomType.getBeds()>0 
                    && roomType.getStandardPrice()>0)
            {
                if(!roomType.getRoomName().equals(""))
                {
                    RoomType rt = (RoomType) roomTypeRepo.persist(roomType);
                    logger.info("roomtype added");
                    return rt;
                }
            }
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    @Transactional
    public RoomType deleteRoomType(RoomType roomType)
    {
        RoomType rt = (RoomType) roomTypeRepo.delete(roomType);
        logger.info("roomType deleted "+rt.getId());
        return rt;
    }
    
    // room ***********************************
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
        logger.info("room deleted");
        return room;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Transactional
    public Room addRoom(Room room)
    {
        Room r = (Room) roomRepo.persist(room);
        logger.info("room added "+r.getId());
        return r;
    }
    

    public List<Hotel> getHotels()
    {
        return hotelRepo.getAll();
    }
    
    public List<RoomType> getRoomTypes()
    {
        return roomTypeRepo.getAll();
    }
    
    public List<Room> getRooms()
    {
        return roomRepo.getAll();
    }
}
