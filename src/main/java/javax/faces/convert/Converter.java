
package javax.faces.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


public interface Converter 
{
    public Object getAsObject(FacesContext context, UIComponent component, String value); 
    public String getAsString(FacesContext context, UIComponent component, Object value); 
    
}
