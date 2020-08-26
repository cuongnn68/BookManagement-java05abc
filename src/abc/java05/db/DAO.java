package abc.java05.db;

import abc.java05.model.Book;

import java.util.List;

//KO DUNG
public interface DAO<T> { //Data Access Object: db -> object
    List<T> getAll();

    T get(String id);

    void save (T book);

    void update (T book);

    void delete (T book);
}
