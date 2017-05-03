
package com.birgit.swhotel.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Room extends EntityClass
{
    private RoomType roomType;
    
    @ManyToOne
    private Hotel hotel;
    private double price;
    
    
    public Room()
    {
        super();
    }
    
    public Room(Hotel hotel, RoomType roomType)
    {
        super();
        this.hotel = hotel;
        this.roomType = roomType;
        this.price = hotel.getPriceFactor() * roomType.getStandardPrice();
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }
    
    
}
