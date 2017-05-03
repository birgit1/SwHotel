
package com.birgit.swhotel.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address 
{
    private String address;
    private String city;
    private String country;

    public Address(String address, String city, String country) 
    {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    // Getter *******************
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() 
    {
        return "Address{ city=" + city + ", country=" + country + '}';
    }
    
   
}
