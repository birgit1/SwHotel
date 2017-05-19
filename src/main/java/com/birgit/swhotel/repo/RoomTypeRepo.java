
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.RoomType;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Dependent
public class RoomTypeRepo extends SingleIdEntityRepository implements Serializable
{
    //@PersistenceContext(unitName="SwHotelPU")
    //private EntityManager entityManager;
    
    @Inject
    Logger logger;
   
    
    public RoomTypeRepo()
    {
        super(RoomType.class);
    }
    
    
    
}
