package dao;

import dao.daoExceptions.DAOException;

import java.util.List;

public interface IDao<T, ID> {



    List<T> findAll();
    T       findById(ID id);


    T       save(T t);
    List<T> saveAll(List<T> liste);
    T       update(T t) throws DAOException;

    Boolean delete(T t) throws DAOException;
    Boolean deleteById(ID id) throws DAOException;

}
