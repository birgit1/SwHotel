
package com.birgit.swhotel.entity;

import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityClass 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    public long getId()
    {
        return id;
    }
    
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof EntityClass)
            return Objects.equals(this.getId(), ((EntityClass)other).getId());
        else
            return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
    
}
