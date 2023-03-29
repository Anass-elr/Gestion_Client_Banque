package dao.daoMySql;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class Utilitaire {


    public static PreparedStatement initPS(Connection Cnx,String requeteSQL,
                                           boolean generateKey ,Object...Columns) throws SQLException {

        PreparedStatement ps = null;

        ps= Cnx.prepareStatement(requeteSQL,generateKey ? Statement.RETURN_GENERATED_KEYS
                                                    : Statement.NO_GENERATED_KEYS);

        for(int i=0; i<Columns.length ;i++){
            ps.setObject(i+1,Columns[i]);
        }

        return ps;

    }


    public static void closeDAOObjects(Resultset rs){
        try {

            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Problem while closing resultSet "+e.getMessage());

        }
        
        
    }
    
    public static void closeDAOObjects(PreparedStatement ps){
        try {
           if(ps!=null)  { ps.close(); }
        } catch (SQLException e) {
            System.out.println("Problem while closing statement "+e.getMessage());
        }
    }

    public static void closeDAOObjects(Connection cnx){
        try {
            if(cnx!=null)  { cnx.close(); }
        } catch (SQLException e) {
            System.out.println("Problem while closing Connexion "+e.getMessage());
        }

    }

    public static void closeDAOObjects(Resultset rs,PreparedStatement ps){
        closeDAOObjects(rs);
        closeDAOObjects(ps);
    }

    public static void closeDAOObjects(Connection cnx,PreparedStatement ps){
        closeDAOObjects(cnx);
        closeDAOObjects(ps);
    }



    public static void closeDAOObjects(Connection cnx,PreparedStatement ps,Resultset rs){
        closeDAOObjects(cnx);
        closeDAOObjects(ps);
        closeDAOObjects(rs);
    }




}
