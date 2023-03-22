package dao;

import presentation.modele.Client;


import java.sql.Connection;
import java.util.List;

public interface IDao<T, ID> {

    SingConection singCon=SingConection.getInstance();
    static Connection Con= singCon.getCon();

    List<T> findAll();
    T       findById(ID id);


    T       save(T t);
    List<T> saveAll(List<T> liste);
    T       update(T t);

    Boolean delete(T t);
    Boolean deleteById(ID id);

}
