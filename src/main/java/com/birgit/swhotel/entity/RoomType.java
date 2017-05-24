
package com.birgit.swhotel.entity;

import javax.persistence.Entity;

@Entity
public class RoomType extends SingleIdEntity
{
    private int beds;
    private String roomName;
    private double standardPrice;
    
    
    public RoomType()
    {
        super();
    }
    
     public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
    }
    
}
