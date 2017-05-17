
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.RoomType;
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
public class RoomTypeRepo 
{
    @PersistenceContext(unitName="SwHotelPU")
    private EntityManager entityManager;
    
    @Inject
    Logger logger;
    
    
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
    
}
