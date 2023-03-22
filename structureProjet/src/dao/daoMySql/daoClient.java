package dao.daoMySql;

import dao.IDao;
import dao.SingConection;
import presentation.modele.Client;
import presentation.modele.Sexe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class daoClient implements IDao<Client,Long> {

    @Override
    public List<Client> findAll() {
        List<Client> cl = new ArrayList<Client>();

        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("SELECT * FROM Client ORDER BY empId");

            ResultSet rs = pstmt.executeQuery();
            Client cli;
            while (rs.next()) {
                    cli = new Client();
                    cli.setId( rs.getLong("1"));
                    cli.setEmail( rs.getString("2"));
                    cli.setCin( rs.getString("3"));
                    cli.setTel( rs.getString("4"));
                    cli.setSexe(Sexe.valueOf(rs.getString("5")));
                    cl.add(cli);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cl;
    }



    @Override
    public Client findById(Long aLong) {

        Client cli=null;
        try{
            PreparedStatement pstmt = null;
            pstmt = Con.prepareStatement("SELECT * FROM Client" +
                    "                    WHERE idClient=? ");


            pstmt.setLong(1,aLong);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                cli = new Client();
                cli.setId( rs.getLong("1"));
                cli.setEmail( rs.getString("2"));
                cli.setCin( rs.getString("3"));
                cli.setTel( rs.getString("4"));
                cli.setSexe(Sexe.valueOf(rs.getString("5")));

                break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cli;
    }

    @Override
    public Client save(Client client) {


        try{
            PreparedStatement pstmt = null;

            String  sql="INSERT INTO Utilisateur VALUE(?,?,?,?,?,?);";
            pstmt = Con.prepareStatement(sql);

            pstmt.setInt(1, Math.toIntExact(client.getId()));
            pstmt.setString(2,client.getNom());
            pstmt.setString(3,client.getPrenom());
            pstmt.setString(4,client.getLogin());
            pstmt.setString(5,client.getMotDePasse());
            pstmt.setString(6   ,client.getRole());


            int  rt = pstmt.executeUpdate();

            if(rt != 0){
                sql="INSERT INTO Client VALUE(?,?,?,?,?,?,?)";
                pstmt = Con.prepareStatement(sql);

                pstmt.setInt(1, Math.toIntExact(client.getId()));
                pstmt.setString(2,client.getEmail());
                pstmt.setString(3,client.getCin());
                pstmt.setString(4,client.getTel());
                pstmt.setString(5,client.getSexe().getLibelle());
                pstmt.setInt(6,1);
                pstmt.setLong(7,client.getId());

                rt = pstmt.executeUpdate();

                if(rt != 0){
                    return client;
                }
                return null;
            }

            return null;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public List<Client> saveAll(List<Client> liste) {
        for(Client client:liste) {
                save(client);
        }
    }

    @Override
    public Client update(Client client) {

        try{
              PreparedStatement pstmt = null;

              String sql="UPDATE Client SET idClient=?," +
                      " email=?,tel=?,sexe=? WHERE cin=? ";

              pstmt = Con.prepareStatement(sql);

                pstmt.setInt(1, Math.toIntExact(client.getId()));
                pstmt.setString(2,client.getEmail());
                pstmt.setString(3,client.getTel());
                pstmt.setString(4,client.getSexe().getLibelle());
                pstmt.setString(5,client.getCin());


            int  rt = pstmt.executeUpdate();

            if(rt != 0){
                sql="INSERT INTO Utilisateur SET  VALUE(?,?,?,?,?,?,?)";
                pstmt = Con.prepareStatement(sql);

                pstmt.setInt(1, Math.toIntExact(client.getId()));
                pstmt.setString(2,client.getEmail());
                pstmt.setString(3,client.getCin());
                pstmt.setString(4,client.getTel());
                pstmt.setString(5,client.getSexe().getLibelle());
                pstmt.setInt(6,1);
                pstmt.setLong(7,client.getId());

                rt = pstmt.executeUpdate();

                if(rt != 0){
                    return client;
                }
                return null;
            }
                return client;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean delete(Client client) {
        try{
            PreparedStatement pstmt = null;
            String sql ="DELETE FROM Client WHERE idClient=?";
            pstmt = Con.prepareStatement(sql);

            pstmt.setInt(1, Math.toIntExact(client.getId()));
            int  rt = pstmt.executeUpdate();

            if(rt != 0) return true;
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
                String sql ="DELETE FROM Client WHERE idClient=?";
                pstmt = Con.prepareStatement(sql);

                pstmt.setInt(1, Math.toIntExact(aLong));
                int  rt = pstmt.executeUpdate();

                if(rt != 0) return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
