/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.faces.convert;

import com.birgit.swhotel.entity.EntityClass;
import com.birgit.swhotel.repo.RoomTypeRepo;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@FacesConverter("roomTypeConverter")
public class RoomTypeConverter extends  AbstractConverterClass
{
    @Inject
    private RoomTypeRepo roomTypeService;

    @Override
    public EntityClass findById(Long id) 
    {
        return roomTypeService.getRoomTypeById(id);
    }
    
}
