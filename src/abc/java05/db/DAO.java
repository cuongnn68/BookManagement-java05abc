package abc.java05.db;

import abc.java05.model.Book;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();

    T get(String id);

    void save (T book);

    void update (T book);

    void delete (T book);
}
