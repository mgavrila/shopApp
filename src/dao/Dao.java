package dao;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public interface Dao<T> {
    
    Optional<T> get(long id);
     
    List<T> getAll();
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);
    
    void getId(T t);
    
}