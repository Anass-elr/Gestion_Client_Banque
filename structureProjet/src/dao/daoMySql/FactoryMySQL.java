package dao.daoMySql;

import dao.Factory;
import dao.IDao;
import dao.daoExceptions.DAOConfigException;
import presentation.modele.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryMySQL extends Factory {

    private static final String  PROPERTIES_FILE = "dao/dao.properties" ,
                                 URL = "SDB_URL",
                                 DB="DB_NAME",
                                LOGIN="SDB_LOGIN",
                                PASS="SBD_PASS",
                                DRIVER ="SDB_DRIVER";

    private static FactoryMySQL  INSTANCE = getInstance();

    private static Connection connection;

    private String url,login,pass;

    private FactoryMySQL(String url,String login ,String pass) throws SQLException {
        this.url=url;
        this.login = login;
        this.pass=pass;

        connection = DriverManager.getConnection(url,login,pass);

    }

    public static FactoryMySQL getInstance() throws DAOConfigException {

        FactoryMySQL  instance = null;

        String property_URL,property_DBNAME,property_LOGIN,property_PASS,property_DRIVER;

        Properties properties = new Properties();

        ClassLoader  classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile= classLoader.getResourceAsStream(PROPERTIES_FILE);

        // Verifié l'existence du fichier dao.properties
        if(propertiesFile == null){
            throw new DAOConfigException(PROPERTIES_FILE+" Fichier introuvable ");
        }
        else{
            try{
                properties.load(propertiesFile);

                property_URL    = properties.getProperty(URL);
                property_LOGIN   =properties.getProperty(LOGIN);
                property_PASS   = properties.getProperty(PASS);
                property_DBNAME = properties.getProperty(DB);
                property_DRIVER = properties.getProperty(DRIVER);

                propertiesFile.close();

                // Vérification de Chargement de driver
                Class.forName(property_DRIVER);
                property_URL = property_URL + "/" + property_DBNAME;
                instance = new FactoryMySQL(property_URL,property_DBNAME,property_PASS);
            }
            catch (SQLException e) {
                throw new DAOConfigException("Connection  échoué !! "+e.getMessage() );
            } catch (IOException e) {
                throw new DAOConfigException("Chargement de FICHIER est échoué !! "+ PROPERTIES_FILE) ;
            } catch (ClassNotFoundException e) {
                throw new DAOConfigException("Chargement de driver est échoué !! "+e.getMessage() );
            }
        }

        return instance;

        }




    public static  Connection getConnection(){
        if(connection == null) {
            try {
                INSTANCE = getInstance();
            } catch (DAOConfigException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static  void closeConnection() {
        if( connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public IDao<Client, Long> getClientDao() {
        return new daoClient(getConnection());
    }

    @Override
    public IDao<Utilisateur, Long> getUserDao() {
        return new daoUtilisateur(getConnection());
    }

    @Override
    public IDao<Banque, Long> getBanqueDao() {
      //  return new  daoBanque(getConnection());
        return null;

    }

    @Override
    public IDao<Admin, Long> getAdminDao() {
        // return new daoAdmin(connection);
        return null;

    }

    @Override
    public IDao<Compte, Long> getCompteDao() {
        return new daoCompte(connection);

    }

    @Override
    public IDao<Log, Long> getLogDao() {
    //    return new daoLog(getConnection());
        return null;
    }
}
