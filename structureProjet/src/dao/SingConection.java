package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingConection {
        static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
        static final String USER = "root";
        static final String PASS = "";

        //  static final String QUERY = "SELECT id, first, last, age FROM Employees";
        private static Connection con;
        private static SingConection  singConection = new SingConection();

       private SingConection()  {
           setCon();
       }

       public static SingConection getInstance(){
           return singConection;
       }

       private void setCon()  {
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection(DB_URL, USER, PASS);
               System.out.println("Connexion etabli avec succes");
           } catch (Exception e) {
              e.printStackTrace();
           }
       }


        public static Connection getCon() {
           if(con == null ) new  SingConection();
            return con;
        }



}
