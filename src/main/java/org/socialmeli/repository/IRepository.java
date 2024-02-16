package org.socialmeli.repository;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Vendor;

import java.util.List;

public interface IRepository<E> {
     List<E> findAll();
     void save(E item);
     E findOne (Integer id);
     void deleteOne(Integer id);
}
