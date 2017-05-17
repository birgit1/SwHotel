
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.HotelRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.repo.UserRepo;
import com.birgit.swhotel.service.BookingService;
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
    private HotelRepo hotelService;
    
    @Inject 
    private BookingService bookingService;
    
    @Inject
    private RoomRepo roomRepo;
    
    @Inject 
    private UserRepo userRepo;
    
    
    private List<Hotel> hotels;
    
    private String search = null;
    private Hotel currentHotel = null;

    
    @PostConstruct
    public void init() 
    {
        this.hotels = hotelService.getAllHotels();
    }
    
    
    public List<Hotel> searchHotel()
    {
        System.out.println("search for hotel: "+search);
        this.hotels = hotelService.findHotelByString(search);
        return hotels;
    }
    
    // booking ********************************************
    
    public String showDetail(Hotel hotel)
    {
        currentHotel = hotel;
        loadRooms();
        return "hotelDetail";
    }
    
   
    /*public void checkAvailability()
    {
        String dateString = dateYear+"-"+dateMonth+"-"+dateDay;
        Date date = Date.valueOf(dateString);
        List<Room> availableRooms = bookingService.checkAvailibility(currentHotel, date, nights);
        rooms2rent = "#rooms:  "+availableRooms.size();
        
    }*/
    private int dateDay = 1;
    private int dateMonth = 8;
    private int dateYear = 2017;
    private int nights = 2;
    
    
    private List<Room> rooms ;
    
    public void loadRooms()
    {
        rooms = roomRepo.getHotelRooms(currentHotel);
    }
    
    private Room currentRoom = null;
    private Booking currentBooking = null;
    
    public void getAvailableRooms()
    {
        Date date = DateUtils.stringToDate(dateDay, dateMonth, dateYear);
        rooms = bookingService.getAvailableRooms(currentHotel, date, nights);
    }
    
    public String bookRoom(Room room)
    {
        currentRoom = room;
        currentBooking = new Booking();
        currentBooking.setRoom(currentRoom);
        System.out.println("0 DATE: "+dateDay+"."+dateMonth+"."+dateYear);
        currentBooking.setArrival(DateUtils.stringToDate(dateDay, dateMonth, dateYear));
        currentBooking.setNights(nights);
        System.out.println("1 DATE: "+currentBooking.getArrival());
        
        return "bookingDetail";
    }
    
    // USER ***********************************
    
    
    private String email, password;
    private User loggedInUser = null;
    private String message;
    
    @Transactional
    public String makeBooking()
    {
        register();
        if(loggedInUser == null)
        {
            message = "wrong log in data";
            return "bookingDetails";
        }
        currentBooking.setUser(loggedInUser);
        Booking booking = bookingService.makeBooking(currentBooking, loggedInUser);
        System.out.println("2 DATE: "+booking.getArrival());
        message = "booking successful: "+booking.getId();
        getBookingsForUser();
        return "bookings";
    }
    
    // show user booking ****************************
    
    List<Booking> userBookings;
    
    public void getBookingsForUser()
    {
        userBookings = userRepo.getUserBookings(loggedInUser);
    }
    
    public void register()
    {
        loggedInUser = userRepo.authenticateUser(email, password);
    }
    
    public void deleteBooking(Booking booking)
    {
        bookingService.removeBooking(loggedInUser, booking);
        userBookings.remove(booking);
    }
    

    // getter & setter **********************************
    

    public Hotel getCurrentHotel() {
        return currentHotel;
    }

    public void setCurrentHotel(Hotel currentHotel) 
    {
        this.currentHotel = currentHotel;
    }
    
    public List<Hotel> getHotels() 
    {  
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

    public Room getCurrentRoom() {
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
    }

    public List<Booking> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }
    
    
    
    
}
 
