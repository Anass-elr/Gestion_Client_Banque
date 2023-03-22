package metier.Form;

import java.util.HashMap;
import java.util.Map;

import metier.authentification.ServiceAuth;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Utilisateur;

public class ValiderFormLogin {
	
	private static final String FILED_LOGIN="Login",
								FIELD_PASS="Pass";
	
	private Map<String,String> Error=new HashMap<String,String>();
	
	private String resMes;
	
	private ServiceAuth Dao;

	public Map<String, String> getError() {
		return Error;
	}
	
	private Banque B;
	
	public ValiderFormLogin() {}
	
	public ValiderFormLogin(Banque b) {
		this.B=b;
		this.Dao=new ServiceAuth(B);
	}
	

	//Fonction de verification 
	public void verifierLogin(String login) throws FormException{
		if(login != null && login.trim().length()!=0) {
			if(login.trim().length() < 4) 
				 throw new FormException("Your Login must have atleast 4 caracteres");
		}
		else 
			 throw new FormException("Enter a Login !!");

	}
	
	
	public void verifierPass(String pass) throws FormException{
		if(pass != null && pass.trim().length()!=0) {
			if(pass.trim().length() < 4) 
				 throw new FormException("Your Pass must have atleast 4 caracteres");
		}
		else 
			 throw new FormException("Enter a Pass !!");

	}
	
	
	public Boolean validerLogin(String login) {
		try {
			verifierLogin(login);
			return true;
		}
		catch(FormException e) {
			 setError(getFiledLogin(),e.getMessage());
			 return false;
		}
	}
	
	public Boolean validerPass(String pass) {
		try {
			verifierPass(pass);
			return true;
		}
		catch(FormException e) {
			    setError(getFieldPass(),e.getMessage());
				return false;
		}
	}
	
	
	public Utilisateur validerSession(String login,String pass,Boolean verif) {
	
		Utilisateur session=null;
		Error.clear();
		
		validerPass(pass);
		validerLogin(login);
		
		if(Error.isEmpty()) {
			session=Dao.chercherUser(login, pass, verif);
			if(session instanceof Admin) {
				setResMes("=>Connection Valide [Admin] : "+session.getNomComplet());
			}
			else if(session instanceof Client) {
				setResMes("=>Connection Valide [Client] : "+session.getNomComplet());

			}
			return session;
		}
		else {
		  setResMes("Information Invalide");
		}
		
		return session;
	}
	
	
	
	
	
	

	public Banque getB() {
		return B;
	}

	public void setB(Banque b) {
		B = b;
	}

	public void setError(String fieldName,String error) {
		getError().put(fieldName, error);
	}

	public String getResMes() {
		return resMes;
	}

	public void setResMes(String resMes) {
		this.resMes = resMes;
	}

	public static String getFiledLogin() {
		return FILED_LOGIN;
	}

	public static String getFieldPass() {
		return FIELD_PASS;
	}
	
	
	
	
	
	
	
	

}
