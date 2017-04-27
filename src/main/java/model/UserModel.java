
package model;

import com.birgit.swhotel.entity.User;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

    
@Named
@SessionScoped
public class UserModel implements Serializable 
{
    public static final long serialVersionUID = 1L;
    
    private String username;
    private String email;
    private String password;
    
    
    public void addUser()
    {
        User user = new User();
        user.setEmail(email);
        user.setName(username);
        user.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

