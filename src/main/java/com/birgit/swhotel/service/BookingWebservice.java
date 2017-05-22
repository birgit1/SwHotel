/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.HotelRepo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@SessionScoped
public class BookingWebservice implements Serializable 
{
    @Inject
    private HotelRepo hotelRepo;
    
    @Inject
    private BookingService bookingService;
    
    @Transactional
    public List<Room> getHotelRooms(String str, Date date, int nights)
    {
        List<Hotel> hotels = hotelRepo.findHotelByString(str);
        if(hotels == null)
            return null;
        this.arrivalDate = date;
        this.nights = nights;
        List<Room> rooms = new ArrayList<>();
        for(int i=0; i<hotels.size(); i++)
        {
            List<Room> hotelRooms = bookingService.getAvailableRooms(hotels.get(i), date, nights);
            if(hotelRooms != null)
            {
                for(Room room: hotelRooms)
                    rooms.add(room);
            }
        }   
        return rooms;
    }
    
    @Transactional
    public long bookRoom(Room room, User user)
    {
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setUser(user);
        booking.setArrival(arrivalDate);
        booking.setNights(nights);
        Booking b = bookingService.makeBooking(booking);
        arrivalDate=null;
        nights=0;
        return b.getId();
    }
    
    private Date arrivalDate;
    private int nights;
    
}
