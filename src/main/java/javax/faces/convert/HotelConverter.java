/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.faces.convert;

import com.birgit.swhotel.entity.SingleIdEntity;
import com.birgit.swhotel.repo.HotelRepo;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@FacesConverter("hotelConverter")
public class HotelConverter extends AbstractConverterClass
{
    @Inject
    private HotelRepo hotelService;

    @Override
    public SingleIdEntity findById(Long id) 
    {
        return hotelService.getById(id);
    }
    
}
