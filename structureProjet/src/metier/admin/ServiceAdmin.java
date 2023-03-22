package metier.admin;

import java.security.KeyStore.Entry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import Util.typeAuClavier;
import dao.IDao;
import dao.daoFiles.ClientDao;
import dao.daoMySql.daoClient;
import metier.InteractiveConsole;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Filtre;
import presentation.modele.Sexe;
import presentation.modele.TableauDeBord;

public class ServiceAdmin implements IServiceAdmin,InteractiveConsole{
	
	
	private Banque B;
    private IDao dao=new dao.daoMySql.daoClient();

	public ServiceAdmin(Banque b) {
		super();
		B = b;
	}

	@Override
	public Client nouveauClient() {
		sc.nextLine();
		String nom=typeAuClavier.StringAuClavier(sc,"Nom");
		
		String prenom=typeAuClavier.StringAuClavier(sc,"Prenom");
		String login=typeAuClavier.StringAuClavier(sc,"Login");
		String pass=typeAuClavier.StringAuClavier(sc,"Password");
		String email=typeAuClavier.StringAuClavier(sc,"Email");
		String cin=typeAuClavier.StringAuClavier(sc,"CIN");
		String tel=typeAuClavier.StringAuClavier(sc,"Tel");

		Client c=new Client(login,pass,nom,prenom,email,cin,tel,Sexe.HOMME);
		
		if(!B.getClientsDeBanque().contains(c)) {
			B.getClientsDeBanque().add(c);
			dao.save(c);
			return c;
		}
		return null;	
	}
	
	

	public Client nouveauCompteClientExistant(Long id) {
		Client c=chercherClientParId(id);
		if(c!=null) {
			System.out.println("-----------Entrez le Solde -------------\n");
			 Double s=sc.nextDouble();
			 Compte cpt=new Compte(s);
			 cpt.setPropriataire(c);
			 c.getComptesClient().add(new Compte(s));
			 
		}
		return c;
	}

	
	@Override
	public Client chercherClientParId(Long id) {
		for(Client cl:B.getClientsDeBanque()){  
			if(cl.getId().equals(id)) return cl;
		}  
		return null;
	}

	@Override
	public List<Client> chercherClientParNom(String nom) {
		
		List<Client> ls=new ArrayList<Client>();
		
		for(Client cl:B.getClientsDeBanque()){  
			if(cl.getNom().equals(nom)) 
				     ls.add(cl);
		}  
		return ls;
	}

	@Override
	public List<Client> chercherClientParPrénom(String prenom) {
		List<Client> ls=new ArrayList<Client>();
		
		for(Client cl:B.getClientsDeBanque()){  
			if(cl.getPrenom().equals(prenom)) ls.add(cl);
		}  
		return ls;	
	}

	@Override
	public Client chercherClientParCin(String cin) {
		for(Client cl:B.getClientsDeBanque()){  
			if(cl.getCin().equals(cin)) return cl;
		}  
		return null;	
	}

	@Override
	public Client chercherClientParEmail(String email) {
		for(Client cl:B.getClientsDeBanque()){  
			if(cl.getEmail().equals(email)) return cl;
		}  
		return null;	
	}

	@Override
	public Compte chercherCompteParId(String idCompte) {
		for(Client cl:B.getClientsDeBanque()) {
			for(Compte c:cl.getComptesClient()) {
				if(  c.getNumeroCompte().equals(idCompte) ) return c;
			}
		}
		return null;
	}

	@Override
	public List<Compte> chercherCompteParSolde(double solde) {
		List<Compte> compte=new ArrayList<Compte>();
		
		B.getClientsDeBanque().forEach(
		    client->{ client.getComptesClient().forEach(
				cpt->{ 
					if(cpt.getSolde().equals(solde)) 
						compte.add(cpt);
					});
		    }
				
		);
		
		return compte;
			
	}

	@Override
	public List<Compte> chercherCompteParDateCreation(LocalDateTime date) {
		List<Compte> compte=new ArrayList<Compte>();
		
		B.getClientsDeBanque().forEach(
		    client->{ client.getComptesClient().forEach(
				cpt->{ 
					if(cpt.getDateCreation().equals(date)) 
						compte.add(cpt);
					});
		    }
				
		);
		
		return compte;
	}

	@Override
	public List<Compte> chercherCompteParPropriaitaire(Client propriataire) {
		List<Compte> compte=new ArrayList<Compte>();
		
		B.getClientsDeBanque().forEach(
		    client->{ client.getComptesClient().forEach(
				cpt->{ 
					if(cpt.getPropriataire().getCin().equals(propriataire.getCin())) 
						compte.add(cpt);
					});
		    }	
		);
		
		return compte;
	}
	

	public long LongAuClavier(Scanner inp,String obj) {
		System.out.println("-----------Entrez "+ obj +" -------------\n");
		long c=inp.nextInt();
		 return c;
	} 
	
	@Override
	public Client modifierClient(String filtre,Long id) {
		Client c=null;
		
		try {
			c=chercherClientParId(id);
		}
		catch(InputMismatchException e){
			System.out.println(e.getMessage());
		}
		
		
		if(c!= null) {
			if( filtre.equals(Filtre.ID.getLibelle()) ) {
				 Long   n=LongAuClavier(sc,"ENTREZ LE NOUVEAU ID :");
					c.setId(n);	
			}
			
			else if(filtre.equals(Filtre.EMAIL.getLibelle())){
				sc.nextLine();
				String st=typeAuClavier.StringAuClavier(sc,"=> LE NOUVEAU EMAIL ");
					c.setEmail(st);
			}	
			else if(filtre.equals(Filtre.CIN.getLibelle())){
				sc.nextLine();
				String st=typeAuClavier.StringAuClavier(sc,"=>LE NOUVEAU CIN");
					c.setCin(st);
				
			}	
			else if(filtre.equals(Filtre.NOM.getLibelle())){
				sc.nextLine();
			   String st=typeAuClavier.StringAuClavier(sc,"=>LE NOUVEAU NOM");
			   c.setNom(st);
		    }
			else if(filtre.equals(Filtre.PRENOM.getLibelle())){
			    	sc.nextLine();
				   String st=typeAuClavier.StringAuClavier(sc,"=>LE NOUVEAU PRENOM");
				   c.setPrenom(st);
			}
			else if(filtre.equals(Filtre.LOGIN.getLibelle())) {
				 sc.nextLine(); 
				 String st=typeAuClavier.StringAuClavier(sc,"=>LE NOUVEAU LOGIN");
				 c.setLogin(st);
			}
			else if(filtre.equals(Filtre.PASS.getLibelle())) {
				sc.nextLine();
				 String st=typeAuClavier.StringAuClavier(sc,"=>LE NOUVEAU PASSWORD");
				c.setMotDePasse(st);
			}
		}

		return c;
	}

	@Override
	public boolean supprimerClient(Long id) {
		Client c=chercherClientParId(id);
		if( c  != null ) {
			B.getClientsDeBanque().remove(c);
			return true;
		}
		return false;
	}
	
	
	
	public boolean supprimerClient(String Nom) {
		List<Client>  lc=new ArrayList<Client>();
		lc=chercherClientParNom(Nom);
		if( lc.size()==0 ) {
			B.getClientsDeBanque().removeAll(lc);
			return true;
		}
		return false;
	}
	
	public boolean supprimerClientPrenom(String Prenom) {
		List<Client>  lc=new ArrayList<Client>();
		lc=chercherClientParPrénom(Prenom);
		if( lc.size()==0 ) {
			B.getClientsDeBanque().removeAll(lc);
			return true;
		}
		return false;
	}
	
	public boolean supprimerClientEmail(String email) {
		Client c=chercherClientParEmail(email);
		if( c  != null ) {
			B.getClientsDeBanque().remove(c);
			return true;
		}
		return false;
	}
	
	public boolean supprimerClientCin(String CIN) {
		Client c=chercherClientParCin(CIN);
		if( c  != null ) {
			B.getClientsDeBanque().remove(c);
			return true;
		}
		return false;
	}
	
	public boolean supprimerClientLogin(String Login) {
		Client c=chercherClientParCin(Login);
		if( c  != null ) {
			B.getClientsDeBanque().remove(c);
			return true;
		}
		return false;
	}
	
	
	public void supprimerTousClients() {
		B.getClientsDeBanque().clear();
	}
	
	
	public void supprimerTouscompte() {
		B.getClientsDeBanque().forEach(client ->  client.getComptesClient().clear());
	}
	
	
	//Tableau de Bord Methodes
	  
	private int nbClients() {
		return B.getClientsDeBanque().size();
	}
	
	private int nbCompte() {
		int s=0;
		for(Client c:B.getClientsDeBanque()) {
			s+=c.getComptesClient().size();
		}
		
		return s;
	}
	
	
	private Double maxSolde() {
		Double d=Double.valueOf(0);

		for(Client c:B.getClientsDeBanque()) {
			for(Compte cpt:c.getComptesClient()) {
				if(cpt.getSolde() > d) {
					 d=cpt.getSolde();
				}
			}
		}	
		return d;
	}
	
	
	private Map<Client,Double> clientSoldeTotal() {
		Double d=Double.valueOf(0);
	    Map<Client,Double> MapTotalSolde=new HashMap<Client,Double>();
		
		for(Client c:B.getClientsDeBanque()) {
			d=Double.valueOf(0);
			for(Compte cpt:c.getComptesClient()) {
				d+=cpt.getSolde();
			}
			MapTotalSolde.put(c, d);
		}
		return MapTotalSolde;
	}
	
	private Client clientLePulsRich() {	
		Double d=Double.valueOf(0);
		Client clientPlusRich=new Client();
		for(Map.Entry<Client,Double> m:clientSoldeTotal().entrySet()){  
				if(d<+m.getValue()) {
					d=+m.getValue();
					clientPlusRich=m.getKey();
				}
		}
		return clientPlusRich;
	}
	
	private Long nbClientHomme() {
		Long s=0l;
		for(Client c:B.getClientsDeBanque()) {
			if(c.getSexe().equals(Sexe.HOMME))
				s++;
		}
		return s;
	}

	public TableauDeBord calculerEtAfficherStatistiques(Banque B) {
		TableauDeBord tab= new TableauDeBord();
		
		//-Nombre Client
		tab.setNombreTotaleClient((long) nbClients());
		
		//-Nombre Compte
	    tab.setNombreTotaleCompte((long)nbCompte());
		
		//-Solde Max
		tab.setMaxSolde(maxSolde());
	
		//plus-Rich		
		tab.setNomClientLePlusRiche(clientLePulsRich().getNomComplet());
		
		//-NBFemme NbHomme
		tab.setTotaleClientsHomme(nbClientHomme());
		tab.setTotalClientsFemme(B.getClientsDeBanque().size()-nbClientHomme());
     			
		return tab;
	}   
	
	/*
	 static void trierParAgeAsc(List<Personne> personnes){
	        Collections.sort(personnes, (p1,p2) -> p2.age.compareTo(p1.age));
	    }
	*/
	
	public void AfficherClients() {
		B.getClientsDeBanque().forEach(cl -> {System.out.println(cl.toString());});
	}
	
	
	@Override
	public void trierClientParNom() {
	  Collections.sort(B.getClientsDeBanque(), 
			  			(p1,p2) -> p1.getNom().compareTo(p2.getNom()));
	}
	

	@Override
	public List<Client> trierClientParCin() {
		Collections.sort(B.getClientsDeBanque(), 
	  		(p1,p2)-> p1.getCin().compareTo(p2.getCin()));
		return B.getClientsDeBanque();
	}

	@Override
	public List<Client> trierClientParEmail() {
		Collections.sort(B.getClientsDeBanque(), 
		  		(p1,p2)->p1.getEmail().compareTo(p2.getEmail()) );
			return B.getClientsDeBanque();
	}

	@Override
	public List<Client> trierClientParAdresse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> trierClientParSoldeCompte() {
		
		Map<Client,Double> map=clientSoldeTotal();
		Map<Client,Double> sortedMap=new HashMap<Client,Double>();
		List<Double> list=new ArrayList<>();
		List<Client> cList=new ArrayList<>();
		
		return null;
	}
	
	
	
	public Map<Client,Double> trierClientParSoldeCompteTotal() {
		
		Map<Client,Double> map=clientSoldeTotal();
		
		Map<Client,Double> sortedMap=new HashMap<Client,Double>();
		List<Double> list=new ArrayList<>();
		List<Client> cList=new ArrayList<>();
		
		for(Map.Entry<Client, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
		}
		Collections.sort(list,(e1,e2)->e2.compareTo(e1));
		  for (Double val : list) {
	            for (Map.Entry<Client, Double>  entry : map.entrySet()) {
	                if (entry.getValue().equals(val)) {
	                    sortedMap.put(entry.getKey(), val);
	                }
	            }
	       }
		return sortedMap;
	}

	@Override
	public List<Compte> trierComptesParSolde() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> trierComptesParDateDeCreation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> trierComptesParNomPropriaitaire() {
		// TODO Auto-generated method stub
		return null;
	}

}
