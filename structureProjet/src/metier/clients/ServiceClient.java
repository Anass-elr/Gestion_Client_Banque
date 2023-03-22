package metier.clients;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import metier.admin.ServiceAdmin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Filtre;
import presentation.modele.Log;
import presentation.modele.TypeLog;

public class ServiceClient implements IServiceClient{
	
	private Banque B;
	private Client c;
	
	public ServiceClient(Banque b) {
  		this.B=b; 
	}
	
	public ServiceClient(Banque b,Client c) {
	 	this.B=b; 
	 	this.c=c;
	}
	
	@Override
	public boolean versement(Client cl,Compte c,Double solde) {
		if(solde > 0) {
			c.setSolde(c.getSolde()+solde);

		Log obj = new Log( LocalDate.now() , LocalTime.now() ,
					 TypeLog.VERSEMENT,"Versement de :"+solde+"DH");
		
		 c.getLogs().add(obj);
		 cl.getLogs().add(obj);
			return true;
		}
		return false;
	}
	
	

	@Override
	public boolean retrait(Client cl,Compte c,Double solde) {
		if(c.getSolde() >= solde) {
			c.setSolde(c.getSolde()-solde);
			
			Log obj = new Log( LocalDate.now() , LocalTime.now() ,
					 TypeLog.RETRAIT,"Versement de :"+solde+"DH");
		
			c.getLogs().add(obj);
			cl.getLogs().add(obj);
			return true;
		}
 	 	System.out.println("Solde insufusant");
		return false;
	}
	
	public void derniereresOpérations() {
		System.out.print(c.getLogs().get(c.getLogs().size()-1));
	}

	public Banque getB() {
		return B;
	}

	public void setB(Banque b) {
		B = b;
	}

	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}

	@Override
	public boolean retrait(Compte c,int choixRetrait) {
	 /*	switch(choixRetrait) {
		
			case 1 : { if(c.getSolde()>100) { c.setSolde(c.getSolde()-100); } } break;
			case 2 : { if(c.getSolde()>200) { c.setSolde(c.getSolde()-200); } } break ;
			case 3 : { if(c.getSolde()>500) { c.setSolde(c.getSolde()-500); } } ;
			case 4 : { if(c.getSolde()>1000) { c.setSolde(c.getSolde()-1000); } } ;
			case 5 : { if(c.getSolde()>2000) { c.setSolde(c.getSolde()-2000); } } ;
			// case 6 : {  retrait(c,solde); } ;
			case 7 : {  break} ;
			default : ;
		}
		*/
		return false; 
		
	}
	//name = ((city.getName() == null) ? "N/A" : city.getName());
	@Override
	public boolean virement(Compte c,String numDes,Double solde) {
		
		if( solde >0 && c.getSolde()>solde ) {	
			for(Client cl:B.getClientsDeBanque()) {
				for(Compte cp:cl.getComptesClient()) {
					  if(cp.getNumeroCompte().equals(numDes)) {
							c.setSolde(c.getSolde()-solde);
							cp.setSolde(cp.getSolde()+solde);
							return true;
					   }
				}
			}
			return false;
		}
		return false; 
	}
	
	

	@Override
	public boolean modifierProfile(int choixModification,Long id) {
		 ServiceAdmin Admin = new ServiceAdmin(B);
		switch(choixModification) {
			case 1 : { Admin.modifierClient("nom",id); }; break;
			case 2 : { Admin.modifierClient( Filtre.PASS.getLibelle(),id); }; break;
			case 3 : { Admin.modifierClient( Filtre.EMAIL.getLibelle(),id); }; break;
			case 4 : { Admin.modifierClient( "cin",id); }; break;
			case 5 : { Admin.modifierClient( "tel",id); }; break;
		}
		return false;
	}




	

	@Override
	public Double afficherSolde() {
		return choisirCompte().getSolde();
	}

	@Override
	public Compte choisirCompte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afficherTicket() {
		// TODO Auto-generated method stub
		
	}
	
}
