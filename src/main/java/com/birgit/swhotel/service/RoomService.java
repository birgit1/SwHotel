
package com.birgit.swhotel.service;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.RoomType;
import com.birgit.swhotel.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class RoomService 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
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
        return result;
    }
    
    public Room getRoomById(int id)
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
        return result;
    }
    
    public RoomType getRoomTypeById(int id)
    {
        System.out.println("getRoomTypeById");
        RoomType roomType = entityManager.find(RoomType.class, id);
        return roomType;
    }
}
