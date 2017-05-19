
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
import javax.enterprise.context.RequestScoped;
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
     UserRepo userRepo;
    
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
    
    // ##### not working: query 
    public List<Hotel> searchHotel()
    {
        System.out.println("search for hotel: "+search);
        this.hotels = hotelRepo.findHotelByString(search);
        return hotels;
    }
    
    public String showDetail(Hotel hotel)
    {
        currentHotel = hotel;
        currentBooking = new Booking();
        //loadRooms();
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
    
    //private Room currentRoom = null;
    private Booking currentBooking = null;
    
    public void getAvailableRooms()
    {
        Date date = DateUtils.stringToDate(dateDay, dateMonth, dateYear);
        currentBooking.setArrival(date);
        currentBooking.setNights(nights);
        rooms = bookingService.getAvailableRooms(currentHotel, date, nights);
    }
    
    public String bookRoom(Room room)
    {
        //currentRoom = room;
        //currentBooking = new Booking();
        currentBooking.setRoom(room);
        //currentBooking.setArrival(DateUtils.stringToDate(dateDay, dateMonth, dateYear));
        //currentBooking.setNights(nights);
        User user = userService.checkAuthentification();
        if(user == null)
            return "loginOrRegister";
        
        currentBooking.setUser(user);
       
        return "bookingDetail";
    }
    
    @Transactional
    public String makeBooking()
    {
        /**register();
        if(loggedInUser == null)
        {
            message = "wrong log in data";
            return "bookingDetails";
        }*/
        User user = userService.checkAuthentification();
        currentBooking.setUser(user);
        Booking booking = bookingService.makeBooking(currentBooking);
        System.out.println("2 DATE: "+booking.getArrival());
        
        getBookingsForUser();
        return "bookings";
    }
    
    
    // bookings
    
    
    List<Booking> userBookings;
    
    public String getBookingsForUser()
    {
        User user = userService.checkAuthentification();
        if(user != null)
        {
            userBookings = userRepo.getUserBookings(user);
            return "bookings";
        }
        else
            return "login";
    }
    
    
    
    public void deleteBooking(Booking booking)
    {
        bookingService.removeBooking(booking);
        userBookings.remove(booking);
    }
    
    // USER ***********************************
    
    
    /*private String email, password;
    private User loggedInUser = null;
    private String message;
    
    public void register()
    {
        loggedInUser = userRepo.authenticateUser(email, password);
    }*/
    
    
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

    /*public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/

    public List<Booking> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }
    
    
    
    
}
 
