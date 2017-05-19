

package com.birgit.swhotel.entity;

import com.birgit.swhotel.utils.DateUtils;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Booking extends SingleIdEntity
{
    @ManyToOne
    private User user;
    @ManyToOne
    private Room room;
    
    private int nights;
    private Date arrival;
    private double totalPrice;
    
    public Booking()
    {
        super();
    }
    
    public Booking(Room room)
    {
        this.room = room;
    }
    
    public boolean compareRoomAvailability(Date arrivalDate, int nnights)
    {
        List<Date> otherDate = DateUtils.getTimeList(arrivalDate, nnights);
        List<Date> thisDate = DateUtils.getTimeList(this.arrival, this.nights);
        for(int i=0; i<otherDate.size(); i++)
        {
            if(thisDate.contains(otherDate.get(i))== true)
            {
                System.out.println("NOT available");
                return false;
            }
        }
        System.out.println("available");
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) 
    {
        this.nights = nights;
        this.totalPrice = this.room.getPrice()*nights;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
