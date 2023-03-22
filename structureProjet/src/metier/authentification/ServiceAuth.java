package metier.authentification;

import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Utilisateur;

public class ServiceAuth implements IAuth{
	
	private Banque B;
	private Utilisateur x;
	
	public ServiceAuth(Banque b) {
		B = b;
	}
	

	
	private Utilisateur chercherAdmin(String Login,String pass) {
		if(Login.equals(Admin.getInstance().getLogin()) &&
				 Admin.getInstance().getMotDePasse().equals(pass)) {
			return Admin.getInstance();
		}
		return null;
	}
	
	
	private Utilisateur chercherClient(String Login,String pass) {
		
		for(Client c:B.getClientsDeBanque()) {
			x=c.verifierLoginPass(Login, pass);
			if(x!=null)
				return x;
		}
     	return null;
	}
	
	
	public Utilisateur chercherUser(String Login,String pass,Boolean verif) {
		
		if(verif) 
		  return chercherAdmin(Login,pass);
		else 
		  return chercherClient(Login,pass);
		
	}
	
	
	
	@Override
	public Utilisateur seConnecter(String Login,String pass,Boolean verif) {
		
		return chercherUser(Login,pass,verif);
	}

	@Override
	public void SeDéconnecter() {
		// TODO Auto-generated method stub
		
	}
   
}
