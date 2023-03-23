package dao.daoMySql;

import dao.IDao;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class daoCompte implements IDao<Compte,String> {


    @Override
    public List<Compte> findAll() {
        List<Compte> cpt = new ArrayList<Compte>();

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("SELECT * FROM Compte");

            ResultSet rs = pstmt.executeQuery();
            Compte cli;
            while (rs.next()) {
                cli = new Compte();
                cli.setNumeroCompte( rs.getString("2") );
                cli.setSolde(rs.getDouble("3"));
               //  cli.setDateCreation(rs.getDate("4"));
                cli.getPropriataire().setId(rs.getLong("5"));

                cpt.add(cli);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cpt;
    }

    @Override
    public Compte findById(String s) {

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("SELECT * FROM Compte WHERE numeroCompte=?");
            pstmt.setString(1,s);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Compte cli = new Compte();
                cli.setNumeroCompte( rs.getString("2") );
                cli.setSolde(rs.getDouble("3"));
            //    cli.setDateCreation(rs.getDate("4"));
                cli.getPropriataire().setId(rs.getLong("5"));

             return cli;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Compte save(Compte compte) {
        return null;
    }

    @Override
    public List<Compte> saveAll(List<Compte> liste) {
        return null;
    }

    @Override
    public Compte update(Compte compte) {
        return null;
    }

    @Override
    public Boolean delete(Compte compte) {
        return null;
    }

    @Override
    public Boolean deleteById(String s) {
        return null;
    }
}
