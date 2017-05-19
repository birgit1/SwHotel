
package com.birgit.swhotel.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TUser")
public class User extends SingleIdEntity implements Serializable 
{
    
    private String email;
    private String password;
    private String name;
    
    @OneToMany(mappedBy="user")
    private List<Booking> bookings;

    public User ()
    { 
        bookings = new ArrayList<>();
    }
    
    public User(String email, String password, String name)
    {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    // Getter and Setter ***********************
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBooking(Booking booking) 
    {
        bookings.add(booking);
    }
    
    public void removeBooking(Booking booking)
    {
        bookings.remove(booking);
    }
    
    
    

    @Override
    public String toString() 
    {
        return "User{" + "email=" + email + ", password=" + password + ", name=" + name + '}';
    }

    
}
