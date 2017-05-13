/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.service.HotelService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class HotelModel implements Serializable
{
    @Inject
    HotelService hotelService;
    
    private List<Hotel> hotels;
    
    private String search;
    private Hotel currentHotel = null;

    
    
    /*private List<Hotel> getAllHotels()
    {
        System.out.println("get all hotels");
        hotels = hotelService.getAllHotels();
        System.out.println("# hotels: "+hotels.size());
        return hotels;
    }*/
    
    public String showDetail(Hotel hotel)
    {
        currentHotel = hotel;
        return "hotelDetail";
    }
    
    public List<Hotel> searchHotel()
    {
        System.out.println("search for hotel: "+search);
        this.hotels = hotelService.findHotelByString(search);
        return hotels;
    }
    
    
    

    // getter & setter
    public Hotel getCurrentHotel() {
        return currentHotel;
    }

    public void setCurrentHotel(Hotel currentHotel) {
        this.currentHotel = currentHotel;
    }
    
    public List<Hotel> getHotels() 
    {  
        System.out.println("get hotels");
        this.hotels = hotelService.getAllHotels();
        return hotels;
    }
    
    public void setHotels(List<Hotel> hotels) 
    {
        this.hotels = hotels;
    }

    public String getSearch() 
    {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    
    
}
 
