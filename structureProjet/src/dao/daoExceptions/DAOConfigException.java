package dao.daoExceptions;

public class DAOConfigException extends  Exception{

    public DAOConfigException() {
        super("Erreur De Config DAO ");
    }

    public DAOConfigException(String message) {
        super(message);
    }
}
