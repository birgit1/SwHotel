
package com.birgit.swhotel.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;


@Entity
public class Hotel extends SingleIdEntity
{
    // primary key
    private String name;
    private String info;
    private Address address;
    private double priceFactor;
    
    public Hotel()
    {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(String address, String city, String country) {
   
        this.address = new Address(address, city, country);
    }

    @Override
    public String toString() 
    {
        return "Hotel{" + "name=" + name + ", info=" + info + ", address=" + address + '}';
    }
    
    
}
