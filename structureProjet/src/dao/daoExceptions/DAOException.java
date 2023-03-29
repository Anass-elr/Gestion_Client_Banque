package dao.daoExceptions;

public class DAOException extends  Exception{

    public DAOException() {
        super("Erreur DAO ");
    }

    public DAOException(String message) {
        super(message);
    }
}
