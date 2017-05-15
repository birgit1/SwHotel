/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.birgit.swhotel.entity;

import com.birgit.swhotel.utils.DateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author kimi
 */
@Entity
public class RoomBooking extends EntityClass 
{
    @ManyToOne
    private Room room;
    private List<Date> date ;
    @OneToOne
    private Booking booking;
    
    public RoomBooking()
    {
        super();
    }
    
    public RoomBooking(Room room, Date arrival, int nights, Booking booking)
    {
        super();
        this.room = room;
        this.booking = booking;
        
        date = DateUtils.getTimeList(arrival, nights);
    }
    
    public boolean compareRoomAvailability(Date arrival, int nights)
    {
        List<Date> compareDate = DateUtils.getTimeList(arrival, nights);
        for(int i=0; i<compareDate.size(); i++)
        {
            if(date.contains(compareDate.get(i))== true)
                return false;
        }
        return true;
    }
    
    

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Date> getDate() {
        return date;
    }

    public void setDate(List<Date> date) {
        this.date = date;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    
}
