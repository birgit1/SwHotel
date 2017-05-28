
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.HotelRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.repo.UserRepo;
import com.birgit.swhotel.service.BookingService;
import com.birgit.swhotel.service.UserService;
import com.birgit.swhotel.utils.DateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;


@Named
@SessionScoped
public class HotelModel implements Serializable
{
    @Inject
     HotelRepo hotelRepo;
    
    @Inject 
    private BookingService bookingService;
    
    @Inject
     RoomRepo roomRepo;
    
    
    @Inject
    private UserService userService;
    
    
    private Hotel currentHotel = null;

    
    @PostConstruct
    public void init() 
    {
        this.hotels = hotelRepo.getAll();
    }
    
    // hotels ********************************************
    private List<Hotel> hotels;
    
    private String search = null;
    
    
    public void searchHotel()
    {
        List<Hotel> searchResult = hotelRepo.findHotelByString(search);
        for(int i=0; i<hotels.size(); i++)
        {
            if(!searchResult.contains(hotels.get(i)))
            {
                hotels.remove(i);
                i--;
            }
        }
    }
    
    
    public String showDetail(Hotel hotel)
    {
        currentHotel = hotel;
        currentBooking = new Booking();
        
        getAvailableRooms();
        return "hotelDetail";
    }
    
    
    
    // hotel Detailed  ********************************************
   
    private int dateDay = 1;
    private int dateMonth = 8;
    private int dateYear = 2017;
    private int nights = 2;
    
    
    private List<Room> rooms ;
    
    public void loadRooms()
    {
        rooms = roomRepo.getHotelRooms(currentHotel);
    }
    
    
    private Booking currentBooking = null;
    
    public void getAvailableRooms()
    {
        Date date = DateUtils.stringToDate(dateDay, dateMonth, dateYear);
        currentBooking.setArrival(date);
        currentBooking.setNights(nights);
        rooms = bookingService.getAvailableRooms(currentHotel, date, nights);
    }
    
    public boolean hotelBookedOut()
    {
        if(rooms == null || rooms.size() <= 0)
            return true;
        return false;
    }
    
    public boolean hotelRoomsAvailable()
    {
        return (!hotelBookedOut());
    }
    
    
    // page 2:hotelDetail: choose room
    
    public String bookRoom(Room room)
    {
        currentBooking.setRoom(room);
         User user = userService.checkAuthentification();
        if(user == null)
            return "loginOrRegister";
        
        currentBooking.setUser(user);
        return "bookingDetail";
    }
    
    // page 3: bookingDetail: confirm booking
    private String payEmail;
    private String payPassword;
    
    @Transactional
    public String makeBooking()
    {
        Booking booking = bookingService.makeBooking(currentBooking, payEmail, payPassword);
        payPassword=null;
        payEmail=null;
        
        if(booking == null)
            return "bookingFail";
        
        return "bookings";
    }
    
    public String bookingFailed()
    {
        currentBooking = null;
        return "hotels";
    }
    
    
    // special getter
    
    public List<Hotel> getHotels() 
    {  
        return hotelRepo.getAll();
    }

    // getter & setter **********************************
    

    public Hotel getCurrentHotel() {
        return currentHotel;
    }

    public void setCurrentHotel(Hotel currentHotel) 
    {
        this.currentHotel = currentHotel;
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

    public int getDateDay() {
        return dateDay;
    }

    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public int getDateYear() {
        return dateYear;
    }

    public void setDateYear(int dateYear) {
        this.dateYear = dateYear;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    public Booking getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking = currentBooking;
    }

    public String getPayEmail() {
        return payEmail;
    }

    public void setPayEmail(String payEmail) {
        this.payEmail = payEmail;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
  
}
 
