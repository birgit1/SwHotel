package com.birgit.swhotel.entity;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Payment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-22T10:56:14")
@StaticMetamodel(User.class)
public class User_ extends SingleIdEntity_ {

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Payment> payment;
    public static volatile ListAttribute<User, Booking> bookings;
    public static volatile SingularAttribute<User, String> email;

}