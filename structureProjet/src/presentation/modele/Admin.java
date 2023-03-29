package presentation.modele;

public class Admin  extends Utilisateur{

    private static  Admin ADMIN = new Admin();

    public Admin(){
        login       = "admin";
        motDePasse  = "1234";
        role        = "Admin";
        nom         =  "Admin";
        prenom      = "Omar";

    }


    public static Admin getInstance(){
        return ADMIN;
    }
    
    

}
