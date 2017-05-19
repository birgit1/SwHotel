package com.birgit.swhotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-18T14:56:23")
@StaticMetamodel(RoomType.class)
public class RoomType_ extends SingleIdEntity_ {

    public static volatile SingularAttribute<RoomType, Double> standardPrice;
    public static volatile SingularAttribute<RoomType, Integer> beds;
    public static volatile SingularAttribute<RoomType, String> roomName;
    public static volatile SingularAttribute<RoomType, Boolean> bathroom;

}