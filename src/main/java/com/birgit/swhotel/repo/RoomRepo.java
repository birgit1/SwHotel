
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.entity.User;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class RoomRepo 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    @Inject
    Logger logger;
    
    // Schreibzugriff
    @Transactional
    public Room addRoom(Room room)
    {
        entityManager.persist(room);
        return room;
    }
    
    @Transactional
    public Room deleteRoom(Room room)
    {
        room = entityManager.merge(room);
        entityManager.remove(room);
        return room;
    }
    
    // Lesezugriff
    public List<Room> getAllRooms()
    {
        TypedQuery<Room> query = entityManager.createQuery("SELECT r FROM Room AS r", Room.class);
        List<Room> result = query.getResultList();
        System.out.println("service: rooms retrieved: "+result.size());
        return result;
    }
    
    public Room getRoomById(long id)
    {
        Room room = entityManager.find(Room.class, id);
        return room;
    }
    
    // Schreibzugriff
    @Transactional
    public RoomType addRoomType(RoomType room)
    {
        entityManager.persist(room);
        return room;
    }
    
    @Transactional
    public RoomType deleteRoomType(RoomType roomType)
    {
        roomType = entityManager.merge(roomType);
        entityManager.remove(roomType);
        return roomType;
    }
    
    // Lesezugriff
    public List<RoomType> getAllRoomTypes()
    {
        TypedQuery<RoomType> query = entityManager.createQuery("SELECT r FROM RoomType AS r", RoomType.class);
        List<RoomType> result = query.getResultList();
        System.out.println("service: roomTypes retrieved: "+result.size());
        return result;
    }
    
    public RoomType getRoomTypeById(long id)
    {
        System.out.println("getRoomTypeById");
        RoomType roomType = entityManager.find(RoomType.class, id);
        return roomType;
    }
    
    @Transactional
    public List<Room> getHotelRooms(Hotel hotel)
    {
        long hotelId = hotel.getId();
        logger.info("######### search rooms: "+hotelId);
        
        try{
        TypedQuery<Room> query = entityManager.createQuery(
            "SELECT r FROM Room r WHERE  r.hotel.id = :parameter1", Room.class);
        query.setParameter("parameter1", hotelId);
        List<Room> hotelRooms = query.getResultList();
        
        
        logger.info("######### rooms found: "+hotelRooms.size());
        
        if(hotelRooms.size() <= 0)
            return null;
        else 
            return hotelRooms;
        }
        catch(Exception e)
        {
            logger.info("######### exception: ");
            e.printStackTrace();
        }
        return null;
    }
}
