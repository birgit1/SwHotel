
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.entity.User;
import com.birgit.swhotel.service.HotelService;
import com.birgit.swhotel.service.RoomService;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AdminModel 
{
    @Inject
    private HotelService hotelService;
    @Inject
    private RoomService roomService;
    
    // hotel ***********************************
    private List<Hotel> hotelList;
    private String hotelName;
    private String city;
    private String country;
    
    
    public void addHotel()
    {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        String address = hotelName+" "+city+" "+country;
        hotel.setAddress(address, city, country);
        hotelService.addHotel(hotel);
        System.out.println("hotel added "+hotel.getId());
    }
    
    public void deleteHotel(Hotel hotel)
    {
        hotelService.deleteHotel(hotel);
        System.out.println("hotel "+hotel.getId()+" deleted");
    }
    
    // room *******************************************
    
    private List<Room> roomList;
    private int room_roomTypeId;
    private int room_hotelId;
    
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
        roomService.deleteRoom(room);
        System.out.println("room "+room.getId()+" deleted");
    }
    
   
    // room types **************************************************
    
    private List<RoomType> roomTypeList;
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
    
    public void deleteRoomType(RoomType roomType)
    {
        roomService.deleteRoomType(roomType);
        System.out.println("room "+roomType.getId()+" deleted");
    }
    
   
    
    
    
    public List<Hotel> getHotelList()
    {
        hotelList = hotelService.getAllHotels();
        System.out.println("# hotels: "+hotelList.size());
        return hotelList;
    }
    
    public List<RoomType> getRoomTypeList()
    {
        roomTypeList = roomService.getAllRoomTypes();
        System.out.println("# roomTypes: "+roomTypeList.size());
        return roomTypeList;
    }
    
    public List<Room> getRoomList()
    {
        roomList = roomService.getAllRooms();
        System.out.println("# rooms: "+roomList.size());
        return roomList;
    }
    
    
    
    
    // getter & setter *****************************************

  

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

    public int getRoom_roomTypeId() {
        return room_roomTypeId;
    }

    public void setRoom_roomTypeId(int room_roomTypeId) {
        this.room_roomTypeId = room_roomTypeId;
    }

    public int getRoom_hotelId() {
        return room_hotelId;
    }

    public void setRoom_hotelId(int room_hotelId) {
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



    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }


    public void setRoomTypeList(List<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }


    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
    
}
