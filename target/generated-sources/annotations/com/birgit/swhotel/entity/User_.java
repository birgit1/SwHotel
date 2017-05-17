package com.birgit.swhotel.entity;

import com.birgit.swhotel.entity.Booking;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-15T20:02:51")
@StaticMetamodel(User.class)
public class User_ extends EntityClass_ {

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Booking> bookings;
    public static volatile SingularAttribute<User, String> email;

}