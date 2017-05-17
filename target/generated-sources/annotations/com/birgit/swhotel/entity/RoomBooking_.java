package com.birgit.swhotel.entity;

import com.birgit.swhotel.entity.Booking;
import com.birgit.swhotel.entity.Room;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-15T20:02:51")
@StaticMetamodel(RoomBooking.class)
public class RoomBooking_ extends EntityClass_ {

    public static volatile SingularAttribute<RoomBooking, List> date;
    public static volatile SingularAttribute<RoomBooking, Booking> booking;
    public static volatile SingularAttribute<RoomBooking, Room> room;

}