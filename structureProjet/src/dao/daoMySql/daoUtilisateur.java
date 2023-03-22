package dao.daoMySql;

import dao.IDao;
import presentation.modele.Client;
import presentation.modele.Sexe;
import presentation.modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class daoUtilisateur implements IDao<Utilisateur,Long> {

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> cl = new ArrayList<Utilisateur>();

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("SELECT * FROM Utilisateur ;");

            ResultSet rs = pstmt.executeQuery();
            Utilisateur ut;
            while (rs.next()) {
                ut = new Utilisateur();
                ut.setId(  rs.getLong("1"));
                ut.setNom( rs.getString("2"));
                ut.setPrenom( rs.getString("3"));
                ut.setLogin( rs.getString("4"));
                ut.setMotDePasse(rs.getString("5"));
                ut.setRole(rs.getString("6"));

                cl.add(ut);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cl;
    }

    @Override
    public Utilisateur findById(Long aLong) {
        Utilisateur ut;

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("SELECT * FROM Utilisateur " +
                    "WHERE idUtilisateur = ?");

            pstmt.setLong(1,aLong);
            ResultSet rs = pstmt.executeQuery();

             if(rs.next()) {
                ut = new Utilisateur();
                ut.setId(  rs.getLong("1"));
                ut.setNom( rs.getString("2"));
                ut.setPrenom( rs.getString("3"));
                ut.setLogin( rs.getString("4"));
                ut.setMotDePasse(rs.getString("5"));
                ut.setRole(rs.getString("6"))
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return uts;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("INSERT INTO Utilisateur VALUE(?,?,?,?,?,?) ");

            pstmt.setLong(1,utilisateur.getId());
            pstmt.setString(2,utilisateur.getNom());
            pstmt.setString(3,utilisateur.getPrenom());
            pstmt.setString(4,utilisateur.getLogin());
            pstmt.setString(5,utilisateur.getLogin());
            pstmt.setString(6,utilisateur.getRole());

            int rs = pstmt.executeUpdate();

            if(rs != 0)
                return utilisateur;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> saveAll(List<Utilisateur> liste) {

        liste.forEach( uti -> save(uti) );

        return liste;
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("" +
                    "UPDATE utilisateur SET " +
                    "nom=?,prenom=?,login=?,motDePasse=?,role=? WHERE idUtilisateur=? ;");


            pstmt.setString(1,utilisateur.getNom());
            pstmt.setString(2,utilisateur.getPrenom());
            pstmt.setString(3,utilisateur.getLogin());
            pstmt.setString(4,utilisateur.getMotDePasse());
            pstmt.setString(5,utilisateur.getRole());

            pstmt.setLong(6,utilisateur.getId());
            int rs = pstmt.executeUpdate();

            if(rs != 0)
                return utilisateur;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(Utilisateur utilisateur) {
        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("" +
                    "DELETE FROM Utilisateur WHERE idUtilisateur = ?");

            pstmt.setLong(1,utilisateur.getId());

            int rs = pstmt.executeUpdate();

            if(rs != 0)
                return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public Boolean deleteById(Long aLong) {
        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("" +
                    "DELETE FROM Utilisateur WHERE idUtilisateur = ?");

            pstmt.setLong(1,aLong);

            int rs = pstmt.executeUpdate();

            if(rs != 0)
                return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
