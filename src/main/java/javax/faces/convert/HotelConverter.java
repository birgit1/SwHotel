/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.faces.convert;

import com.birgit.swhotel.entity.EntityClass;
import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.service.HotelService;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@FacesConverter("hotelConverter")
public class HotelConverter extends AbstractConverterClass
{
    @Inject
    private HotelService hotelService;

    public EntityClass findById(Long id) 
    {
        return hotelService.getHotelById(id);
    }
    
}
