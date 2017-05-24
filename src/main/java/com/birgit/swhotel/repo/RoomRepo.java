
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.Room;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
public class RoomRepo extends SingleIdEntityRepository implements Serializable
{
    @Inject
    Logger logger;
    
    public RoomRepo()
    {
        super(Room.class);
    }
    
    
    @Transactional
    public List<Room> getHotelRooms(Hotel hotel)
    {
        long hotelId = hotel.getId();
        
        TypedQuery<Room> query = this.getEntityManager().createQuery(
            "SELECT r FROM Room r WHERE  r.hotel.id = :parameter1", Room.class);
        query.setParameter("parameter1", hotelId);
        List<Room> hotelRooms = query.getResultList();
        
        logger.info(hotelRooms.size()+" rooms at "+hotel.getId());
        if(hotelRooms.size() <= 0)
            return null;
        else 
            return hotelRooms;
    }
    
    
    
}
