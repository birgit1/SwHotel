
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.repo.BookingRepo;
import com.birgit.swhotel.repo.HotelRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.service.BookingService;
import com.birgit.swhotel.service.HotelService;
import com.birgit.swhotel.service.RoomService;
import com.birgit.swhotel.service.UserRepo;
import com.birgit.swhotel.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

    
@Named
@RequestScoped
public class GenModel implements Serializable 
{
    public static final long serialVersionUID = 1L;
    @Inject
    private UserRepo userService;
    @Inject
    private BookingRepo bookingService;
    @Inject
    private HotelRepo hotelService;
    @Inject
    private RoomRepo roomService;
    
    // user *************************************
    private String username = "birgit";
    private String email;
    private String password;
    
    // hotel ***********************************
    private String hotelName;
    private String city;
    private String country;
    
    public void addUser()
    {
        User user = new User();
        user.setEmail(email);
        user.setName(username);
        user.setPassword(password);
        System.out.println("#0 user added "+user.getId());
        userService.createUser(user);
        System.out.println("user added "+user.getId());
    }
    
    public void deleteUser(User user)
    {
        System.out.println("0) user deleted: "+user.getId());
        userService.deleteUser(user);
        System.out.println("user deleted: "+user.getId());
    }
    
    
    public void addHotel()
    {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        String address = hotelName+" "+city+" "+country;
        hotel.setAddress(address, city, country);
        hotelService.addHotel(hotel);
        System.out.println("hotel added "+hotel.getId());
    }
    
    private long room_roomTypeId;
    private long room_hotelId;
    public void addRoom()
    {
        Room room = new Room();
        Hotel hotel = hotelService.getHotelById(room_hotelId);
        room.setHotel(hotel);
        RoomType roomType = roomService.getRoomTypeById(room_roomTypeId);
        room.setRoomType(roomType);
        roomService.addRoom(room);
        System.out.println("room added "+room.getId());
    }
    
    public void deleteRoom(Room room)
    {
        System.out.println("delete room");
        roomService.deleteRoom(room);
        System.out.println("room deleted");
    }
    
    
    private String roomTypeName;
    private int beds;
    private double standardPrice;
    public void addRoomType()
    {
        RoomType roomType = new RoomType();
        roomType.setRoomName(roomTypeName);
        roomType.setBeds(beds);
        roomType.setStandardPrice(standardPrice);
        roomService.addRoomType(roomType);
        System.out.println("roomType added "+roomType.getId());
    }
    
    
    private long booking_userId;
    private long booking_roomId;
    public void addBooking()
    {
        Booking booking = new Booking();
        User user = userService.getUserById(booking_userId);
        booking.setUser(user);
        Room room = roomService.getRoomById(booking_roomId);
        //bookingService.addBooking(booking);
        System.out.println("booking added "+booking.getId());
    }
    
    // lists
    
    private List<User> userlist = new ArrayList<>();
    public List<User> getAllUsers()
    {
        userlist = userService.getAllUsers();
        System.out.println("# user: "+userlist.size());
        return userlist;
    }
    
    private List<Hotel> hotelList = new ArrayList<>();
    public List<Hotel> getAllHotels()
    {
        hotelList = hotelService.getAllHotels();
        System.out.println("# hotels: "+hotelList.size());
        return hotelList;
    }
    
    private List<RoomType> roomTypeList = new ArrayList<>();
    public List<RoomType> getAllRoomTypes()
    {
        roomTypeList = roomService.getAllRoomTypes();
        System.out.println("# roomTypes: "+roomTypeList.size());
        return roomTypeList;
    }
    
    private List<Room> roomList = new ArrayList<>();
    public List<Room> getAllRooms()
    {
        roomList = roomService.getAllRooms();
        System.out.println("# rooms: "+roomList.size());
        return roomList;
    }
    
    private List<Booking> bookingsList = new ArrayList<>();
    public List<Booking> getAllBookings()
    {
        //bookingsList = bookingService.getAllBookings();
        System.out.println("# bookings: "+bookingsList.size());
        return bookingsList;
    }
    
    
    // getter & setter *****************************************

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getRoom_roomTypeId() {
        return room_roomTypeId;
    }

    public void setRoom_roomTypeId(long room_roomTypeId) {
        this.room_roomTypeId = room_roomTypeId;
    }

    public long getRoom_hotelId() {
        return room_hotelId;
    }

    public void setRoom_hotelId(long room_hotelId) {
        this.room_hotelId = room_hotelId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
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

    public long getBooking_userId() {
        return booking_userId;
    }

    public void setBooking_userId(long booking_userId) {
        this.booking_userId = booking_userId;
    }

    public long getBooking_roomId() {
        return booking_roomId;
    }

    public void setBooking_roomId(long booking_roomId) {
        this.booking_roomId = booking_roomId;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public List<RoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(List<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<Booking> getBookingsList() {
        return bookingsList;
    }

    public void setBookingsList(List<Booking> bookingsList) {
        this.bookingsList = bookingsList;
    }
    
    
}

