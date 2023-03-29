package dao;

import dao.daoExceptions.DAOConfigException;
import dao.daoMySql.FactoryMySQL;
import presentation.modele.*;

public abstract class Factory {

    public static final int MYSQL_DAO_FACTORY = 1;

    public static final int  FILE_DAO_FACTORY = 0;


    public abstract  IDao<Client,Long> getClientDao();

    public abstract  IDao<Utilisateur,Long> getUserDao();

    public abstract  IDao<Banque,Long> getBanqueDao();

    public abstract  IDao<Admin,Long> getAdminDao();

    public abstract  IDao<Compte,Long> getCompteDao();


    public abstract  IDao<Log,Long> getLogDao();

    public static Factory getFactory(int choix)  {
        switch (choix){
            case  MYSQL_DAO_FACTORY :
                try {
                    return FactoryMySQL.getInstance();
                } catch (DAOConfigException e) {
                    e.printStackTrace();
                    }


            case  FILE_DAO_FACTORY  :
                  return null;
                ;
            default :  return null ;

        }

    }


}
