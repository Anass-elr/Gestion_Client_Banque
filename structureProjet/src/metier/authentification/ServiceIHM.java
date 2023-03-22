package metier.authentification;

import Util.typeAuClavier;
import dao.IDao;
import metier.InteractiveConsole;
import metier.Form.ValiderFormLogin;
import metier.admin.ServiceIHMAdmin;
import metier.clients.ServiceIHMClient;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Utilisateur;
import dao.daoFiles.*;


public class ServiceIHM implements IServiceIHM,InteractiveConsole{
	
	private String login;
	private String pass;
	private ValiderFormLogin Auth;
	private Banque B;
	private Utilisateur user;
	
	private ServiceIHMClient homeClient ;
	private ServiceIHMAdmin homeAdmin ;
	// private IDao CompteDao=new ;
	
	public ServiceIHM(Banque b) {
		super();
		B = b;
		homeAdmin= new ServiceIHMAdmin(B);
		Auth=new ValiderFormLogin(B);

	}
	
	private void formConn() {
		String st;
		  System.out.println("**************** MENU CONNECTION ******************");

		  do {
			  login=typeAuClavier.StringAuClavier(sc, "Le Login");
			  pass=typeAuClavier.StringAuClavier(sc, "Le password");
			  st=typeAuClavier.StringAuClavier(sc, "Administrateur ? (Oui/Non)");
			 
			  user=Auth.validerSession(login,pass,st.equals("Oui") ? true : false);
		
			  if(user != null) {
					System.out.println(Auth.getResMes());  
				   if(user instanceof Client) {
					  // -Menu Client
					  
					  homeClient = new ServiceIHMClient(B,(Client)user);
					  homeClient.menuGlobal();
				  }
				  else if(user instanceof Admin){
					  // -Apres Le Client
					  homeAdmin.menuGlobal();
					 
				  }
			  }
			  else {
				System.out.println(Auth.getResMes());  
				  Auth.getError().forEach((k,v) -> {
					       System.out.println(k + ", \nErreur : " + v);
					    });
			  }
		  }while(user==null);
	}

	@Override
	public int menuGlobal() {
		  String st;
		do {
	     formConn();
	     sc.nextLine();
	     	System.out.println("=> 1 MENU CONNECTION");
	     	System.out.println("=> 2 MENU QUITTER");
	         st=typeAuClavier.StringAuClavier(sc, "");
		}while(st.equals("1"));
	       return 0;
	}


	



}
