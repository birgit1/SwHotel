/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.faces.convert;

import com.birgit.swhotel.entity.SingleIdEntity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


public abstract class AbstractConverterClass implements Converter
{
    public abstract SingleIdEntity findById(Long id); 
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value == null) {
            return "";
        }        
        SingleIdEntity entity = findById(Long.valueOf(value));
        if(entity == null) {
            return "";   
        }
        return entity;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null) 
        {
            return null;
        }
        /*if(!value.getClass().equals(EntityClass.class))
            return null;*/
        long id = ((SingleIdEntity)value).getId();
        return String.valueOf(id);
    }
    
}
