package com.birgit.swhotel.entity;

import com.birgit.swhotel.entity.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-18T14:56:23")
@StaticMetamodel(Hotel.class)
public class Hotel_ extends SingleIdEntity_ {

    public static volatile SingularAttribute<Hotel, Address> address;
    public static volatile SingularAttribute<Hotel, String> name;
    public static volatile SingularAttribute<Hotel, Double> priceFactor;
    public static volatile SingularAttribute<Hotel, String> info;

}