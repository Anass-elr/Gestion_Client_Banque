package presentation.modele;

public enum Filtre {

	    /* 
	    private String email, cin, tel;
	     private Sexe sexe;
	     
	     
	      protected Long id;
	     protected String prenom, nom;
	     protected String login, motDePasse
	     */

		ID(0,"id"), PRENOM(1,"prenom"),NOM(2,"nom"),LOGIN(3,"login"),PASS(4,"password"),
	          EMAIL(5,"email"),CIN(6,"cin"),TEL(7,"tel"),SEXE(8,"sexe");

	        String libelle;
	        long indice;
	        Filtre(){}
	        Filtre(String libelle){ this.libelle = libelle;}
	        
	        Filtre(long indice, String libelle)
	                {
	                    this.indice = indice;
	                    this.libelle = libelle;
	                }
	    
	      
	        

		    public Long getIndice() {
		        return indice;
		    }
		  
		    public String getLibelle() {
		        return libelle;
		    }
}

