package presentation.modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Client extends Utilisateur implements Comparable<Client> {

    private String email, cin, tel;
    private Sexe sexe;
    private List<Compte> comptesClient;
    private List<Log>   Logs = new ArrayList<Log>();
    
   
	public List<Log> getLogs() {
		return Logs;
	}
	
	public void setLogs(List<Log> logs) {
		Logs = logs;
	}
	public String       getCin() {
        return cin;
    }
    public String       getTel() {
        return tel;
    }
    public String       getEmail() {
        return email;
    }
    public List<Compte> getComptesClient() {
        return comptesClient;
    }
    public Sexe     getSexe() {
        return sexe;
    }
    public void     setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    public void         setEmail(String email) {
        this.email = email;
    }
    public void         setTel(String tel) {
        this.tel = tel;
    }
    public void         setCin(String cin) {
        this.cin = cin;
    }
    public void         setComptesClient(List<Compte> comptesClient) {
        this.comptesClient = comptesClient;
    }

    public Client(){
    	  comptesClient = new ArrayList<Compte>(Arrays.asList(
          		new Compte(),new Compte(),new Compte()));
    	  
    	  for(Compte c:comptesClient)
        	  c.setPropriataire(this);
    }

    public Client(String login, String pass){
        super(login, pass, "Client");
        comptesClient = new ArrayList<Compte>(Arrays.asList(
        		new Compte(),new Compte(),new Compte()));
        
        for(Compte c:comptesClient)
        	  c.setPropriataire(this);
    }

    public Client(String login, String pass, String n, String p){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        comptesClient = new ArrayList<Compte>(Arrays.asList(
        		new Compte(),new Compte(),new Compte()));
        
        for(Compte c:comptesClient)
      	     c.setPropriataire(this);
    }
    
    
    public Client(String login, String pass, String n, String p,String cin){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        setCin(cin);
        comptesClient = new ArrayList<Compte>(Arrays.asList(
        		new Compte(),new Compte(),new Compte()));
        
        for(Compte c:comptesClient)
      	  c.setPropriataire(this);
    }
    
    public Client(String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
        comptesClient = new ArrayList<Compte>(Arrays.asList(
        		new Compte(),new Compte(),new Compte()));
        
        for(Compte c:comptesClient)
      	    c.setPropriataire(this);
    }

    @Override
    public String toString() {

        String      clientStr  = "------------------------------------------------------\n";
                    clientStr += "| Identifiant du Client     : "   + this.id        + "\n";
                    clientStr += "| Nom Complet               : "   + this.getNomComplet() + "\n" ;
                    clientStr += "| Adresse email             : "   + this.email     + "\n" ;
                    clientStr += "| Numéro téléphone          : "   + this.tel       + "\n" ;
                    clientStr += "| Numéro de CIN             : "   + this.cin       + "\n" ;
                    clientStr += "------------------------------------------------------\n";

        return clientStr;
    }
    
    
    public Utilisateur  verifierLoginPass(String login,String pass) {
    	if(login.equals(login) && motDePasse.equals(pass)) {
    	
    		return this;
    	}
    	
    	return null;
    }
    
    
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Client ) {
			if( nom.equals(((Client) obj).getNom())        &&
			    prenom.equals(((Client) obj).getPrenom())  &&
			    cin.equals(((Client) obj).getCin())) 
				    return true;	
		}
		return false;
	}

	
	public int hashCode() {
		return Objects.hash(nom,prenom,cin);
	}
	

	@Override
	public int compareTo(Client o) {
		if(this.nom.compareTo(o.nom) ==0)
            if(this.prenom.compareTo(o.prenom)==0)
                    return cin.compareTo(o.cin);
            else return prenom.compareTo(o.prenom);
        else return nom.compareTo(o.nom);
	}
	
	
	/*
	  public int compareTo(Personne p) {
	        if(this.nom.compareTo(p.nom) ==0)
	            if(this.prenom.compareTo(p.prenom)==0)
	                if(age.compareTo(p.age)==0)
	                    return sexe.compareTo(p.sexe);
	                else return age.compareTo(p.age);
	            else return prenom.compareTo(p.prenom);
	        else return nom.compareTo(p.nom);
	    }
	    
	   */

}
