package ra.service;

import java.util.List;

public interface IGenericService<E> {
    List<E> findAll();
    void save(E e);
    E findById(int id);
    void deleteById(int id);

    List<E> searchByName(String searchName);

    boolean changeUserStatus(int id);
}