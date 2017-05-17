

package com.birgit.swhotel.repo;

import com.birgit.swhotel.entity.EntityClass;
import java.util.List;


public interface RepoInterface<T> 
{
    public T add(T entity);
    public T delete(T entity);
    public List<T> getAll();
    public T findById(long id);
}
