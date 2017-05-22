package com.birgit.swhotel.entity;

import com.birgit.swhotel.utils.DateUtils;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Booking extends SingleIdEntity {

    @ManyToOne//(fetch=FetchType.EAGER)
    private User user;
    @ManyToOne//(fetch=FetchType.EAGER)
    private Room room;

    private int nights;
    private Date arrival;
    private String arrivalStr;
    private double totalPrice;

    public Booking() {
        super();
    }

    public Booking(Room room) {
        this.room = room;
    }

    public boolean compareRoomAvailability(Date arrivalDate, int nnights) {
        List<Date> otherDate = DateUtils.getTimeList(arrivalDate, nnights);
        List<Date> thisDate = DateUtils.getTimeList(this.arrival, this.nights);
        for (int i = 0; i < otherDate.size(); i++) {
            if (thisDate.contains(otherDate.get(i)) == true) {
                System.out.println("other date: " + otherDate.get(i));
                for (int b = 0; b < thisDate.size(); b++) {
                    System.out.println("#" + b + ": " + thisDate.get(b));
                }
                System.out.println("NOT available");
                return false;
            }
        }
        System.out.println("available");
        return true;
    }

    // getter and setter modified ************
    public void setRoom(Room room) {
        this.room = room;
        if (nights > 0) {
            this.totalPrice = this.room.getPrice() * nights;
        }
    }

    public void setNights(int nights) {
        this.nights = nights;
        if (this.room != null) {
            this.totalPrice = this.room.getPrice() * nights;
        }
    }

    // getter and setter ******************
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
        this.arrivalStr = DateUtils.dateToString(arrival);
    }

    public String getArrivalStr() {
        return arrivalStr;
    }
    
    

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
