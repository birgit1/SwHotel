
package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.RoomType;
import java.io.Serializable;
import javax.enterprise.context.Dependent;

@Dependent
public class RoomTypeRepo extends SingleIdEntityRepository implements Serializable
{
   
    
    public RoomTypeRepo()
    {
        super(RoomType.class);
    }
   
}
