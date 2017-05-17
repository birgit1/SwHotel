/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.UserRepo;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class RemoveService 
{
    @Inject
    Logger logger;
    
    @Inject
    private UserRepo userRepo;
    
    @Inject
    private BookingRepo bookingRepo;
   
    
    @Transactional
    public Hotel deleteHotel(Hotel hotel)
    {
        // check if there are rooms in that hotel -> in that case not delete
    }
    
    @Transactional
    public Room deleteRoom(Room room)
    {
        // inform user about delete
    }
    
    @Transactional
    public RoomType deleteRoomType(RoomType roomType)
    {
        // check if there are rooms of that type -> in that case not delete
        
    }
    
    @Transactional
    public Booking deleteBooking(Booking booking)
    {
        // add to both user and booking
        User user = booking.getUser();
        userRepo.removeBooking(user, booking);
        Booking b = bookingRepo.deleteBooking(booking);
         return booking;
    }
    
}
