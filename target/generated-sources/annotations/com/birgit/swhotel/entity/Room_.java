package com.birgit.swhotel.entity;

import com.birgit.swhotel.entity.Hotel;
import com.birgit.swhotel.entity.RoomType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-24T21:01:43")
@StaticMetamodel(Room.class)
public class Room_ extends SingleIdEntity_ {

    public static volatile SingularAttribute<Room, Double> price;
    public static volatile SingularAttribute<Room, Hotel> hotel;
    public static volatile SingularAttribute<Room, RoomType> roomType;

}