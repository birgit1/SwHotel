
package com.birgit.swhotel.model;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.repo.HotelRepo;
import com.birgit.swhotel.repo.RoomRepo;
import com.birgit.swhotel.repo.RoomTypeRepo;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AdminModel 
{
    @Inject
    private HotelRepo hotelService;
    @Inject
    private RoomRepo roomService;
    @Inject
    private RoomTypeRepo roomTypeService;
    
    @PostConstruct
    public void init() 
    {
        this.hotelList = hotelService.getAll();
        this.roomTypeList = roomTypeService.getAll();
        this.roomList = roomService.getAll();
    }
    
    // authentification
    private String adminPassword;
    private String wrongPasswordMsg = null;
    
    public String authenticateAdmin()
    {
        if(adminPassword.equals("admin"))
            return "admin";
        else
        {
            wrongPasswordMsg = "wrong password";
            return null;
        }
    }
    
    // hotel ***********************************
    private List<Hotel> hotelList;
    private String hotelName;
    private String city;
    private String country;
    private String hotelAddress;
    private String hotelInfo = null;
    private double priceFactor = 1;
    
    
    public void addHotel()
    {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setAddress(hotelAddress, city, country);
        hotel.setPriceFactor(priceFactor);
        hotel.setInfo(hotelInfo);
        
        Hotel addedHotel = (Hotel) hotelService.persist(hotel);
        hotelList.add(addedHotel);
        System.out.println("hotel added "+addedHotel.getId());
        hotelName = null;
        city = null;
        country = null;
        hotelAddress = null;
        hotelInfo = null;
        priceFactor = 1;
    }
    
    public void deleteHotel(Hotel hotel)
    {
        System.out.println("delete hotel "+hotel.getName());
        hotelService.delete(hotel);
        hotelList.remove(hotel);
        System.out.println("hotel "+hotel.getId()+" deleted");
    }
    
   
    // room types **************************************************
    
    private List<RoomType> roomTypeList;
    private String roomTypeName = null;
    private int beds;
    private double standardPrice;
    
    public void addRoomType()
    {
        RoomType roomType = new RoomType();
        roomType.setRoomName(roomTypeName);
        roomType.setBeds(beds);
        roomType.setStandardPrice(standardPrice);
        roomTypeService.persist(roomType);
        roomTypeList.add(roomType);
        System.out.println("roomType added "+roomType.getId());
        roomTypeName = null;
        beds = 0;
        standardPrice = 0;
    }
    
    public void deleteRoomType(RoomType roomType)
    {
        roomTypeService.delete(roomType);
        roomTypeList.remove(roomType);
    }
    
    
   // room *******************************************
    
    private List<Room> roomList;
    private long room_roomTypeId;
    private long room_hotelId;
    private Hotel currentHotelRoomSelection = null;
    private RoomType currentRoomTypeRoomSelection = null;
    
    public void addRoom()
    {
        Room room = new Room(currentHotelRoomSelection, currentRoomTypeRoomSelection);
       
        roomService.persist(room);
        roomList.add(room);
        System.out.println("room added "+room.getId());
        currentHotelRoomSelection = null;
        currentRoomTypeRoomSelection = null;
    }
    
    public void deleteRoom(Room room)
    {
        roomService.delete(room);
        roomList.remove(room);
    }
    
    
    // lists *****************************************************
 
    public List<Hotel> getHotelList()
    {
        return hotelList;
    }
    
    public List<RoomType> getRoomTypeList()
    {
        return roomTypeList;
    }
    
    public List<Room> getRoomList()
    {
        return roomList;
    }
  // getter & setter *****************************************
    public void setCurrentRoomTypeRoomSelection(RoomType currentRoomTypeRoomSelection) {    
        this.currentRoomTypeRoomSelection = currentRoomTypeRoomSelection;
    }
    
    public Hotel getCurrentHotelRoomSelection() {
        return currentHotelRoomSelection;
    }

    public void setCurrentHotelRoomSelection(Hotel currentHotelRoomSelection) {
        this.currentHotelRoomSelection = currentHotelRoomSelection;
    }

    public RoomType getCurrentRoomTypeRoomSelection() {
        return currentRoomTypeRoomSelection;
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



    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }


    public void setRoomTypeList(List<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }


    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public double getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getWrongPasswordMsg() {
        return wrongPasswordMsg;
    }

    public void setWrongPasswordMsg(String wrongPasswordMsg) {
        this.wrongPasswordMsg = wrongPasswordMsg;
    }
    
    
    
    
}
