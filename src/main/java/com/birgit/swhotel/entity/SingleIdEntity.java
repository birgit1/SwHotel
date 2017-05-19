
package com.birgit.swhotel.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class SingleIdEntity implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    public long getId()
    {
        return id;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SingleIdEntity other = (SingleIdEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
    
    @Override
    public String toString() {
        return "Entity{" + "Id=" + id + '}';
    }
    
}
