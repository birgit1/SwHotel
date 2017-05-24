package com.birgit.swhotel.entity;

import com.birgit.swhotel.entity.Room;
import com.birgit.swhotel.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T18:03:45")
@StaticMetamodel(Booking.class)
public class Booking_ extends SingleIdEntity_ {

    public static volatile SingularAttribute<Booking, Date> arrival;
    public static volatile SingularAttribute<Booking, Double> totalPrice;
    public static volatile SingularAttribute<Booking, Integer> nights;
    public static volatile SingularAttribute<Booking, User> user;
    public static volatile SingularAttribute<Booking, Room> room;
    public static volatile SingularAttribute<Booking, String> arrivalStr;

}