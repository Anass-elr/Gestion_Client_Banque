package metier.clients;

import java.time.LocalDate;

import Util.typeAuClavier;
import metier.InteractiveConsole;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Utilisateur;

public class ServiceIHMClient implements IServiceIHMClient,InteractiveConsole{
	private Banque B;
	private Client c;
	private Integer posCompte;
	
	String chaine;
	Double solde;
	
	private ServiceClient SrvClient;
	
	public ServiceIHMClient(Banque b,Client c) {
		super();
		B = b;
		this.c=c;
		SrvClient=new ServiceClient(B,c);
	}
	
	
	public int choixCompte() throws Exception {
		System.out.println("=*=*=*=*=*=*=*=*=*==*=*=*=*=**=*==*=*=*=*=*=*=*=*=*=");
		System.out.println("=*=                                              =*=");
		System.out.println("=*= Welcome : "+c.getNomComplet()             + "=*=");
		System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
		System.out.println("=*=Veuiller choisir votre compte                 =*=");
		
		for(int i=0;i<c.getComptesClient().size();i++) {
			System.out.println("= Tapez"+(i+1)+" pour  choisir"+c.getComptesClient().get(i).
	    		   getNumeroCompte()+"=");
		}
		
		System.out.println("=*=                                              =*=");
		System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
		
		System.out.println("");
			
		int numcompte=typeAuClavier.IntegerAuClavier(sc,"");
		
		
		return numcompte;

	}
	
	
	@Override
	public int menuGlobal() {
		try {
		 this.posCompte=choixCompte()-1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Integer choix;
		
		do {
			System.out.println("=*=*=*=*=*=*=  MENU [PRINCIPALE] =*=*=*=*=*=*=*=*=*=");
			System.out.println("=*=                                              =*=");
			System.out.println("=*=Compte N: "+c.getComptesClient().get(posCompte)
											.getNumeroCompte()+"               =*=");
			System.out.println("=*=                                              =*=");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
			System.out.println("=*=                                              =*=");
			System.out.println("= Tapez 1 pour faire un versement                  =");
			System.out.println("= Tapez 2 pour faire un retrait                    =");
			System.out.println("= Tapez 3 pour faire un virement                   =");
			System.out.println("= Tapez 4 pour modifier votre profile              =");
			System.out.println("= Tapez 5 pour afficher des informations du compte =");
			System.out.println("= Tapez 6 pour afficher le solde actuel du compte  =");            
			System.out.println("= Tapez 7 pour changer de compte                   =");
			System.out.println("= Tapez 8 pour se déconnecter                      =");
			System.out.println("=*=                                              =*=");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
			System.out.println("");

			
			chaine=typeAuClavier.
					    StringAuClavier(sc, "Entrez Votre choix : ");
			choix = sc.nextInt();
			
			switch(choix) {
			
				case 1 :{
				  chaine=typeAuClavier.
						  StringAuClavier(sc, "Entrez Le montant a verser : ");
				  solde=sc.nextDouble();
				
				  SrvClient.versement(c,c.getComptesClient().get(posCompte),solde);
			    
				} break; 
				
				case 2 :{
					menuRetrait();
				} break; 
				
				case 3 :{ 
					try {
						solde= typeAuClavier.
								DoubleAuClavier(sc, "Entrez le solde : ");
					    	sc.nextLine();
					  	
						  chaine=typeAuClavier.
								  StringAuClavier(sc, "Entrez Le numero du compte destination: ");
						System.out.println(chaine);
						
						  SrvClient.virement(c.getComptesClient().get(posCompte),chaine,solde);
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					
				};break ; 
				
				case 4 :{
					menuModification();
				} ;break; 
				
				case 5 :{ menuInformations();} ; 
				case 6 :{System.out.println("Solde :"+
					       c.getComptesClient().get(posCompte).getSolde());}break; 
				case 7 :{menuGlobal();}; break; 
			}
		
		}while(choix >= 1 && choix <= 7  );
		
		return 0;
	}

	@Override
	public int menuModification() {
		Integer choix;
		
		do {
			System.out.println("=*=*=*=*=*=*=  MENU [MODIFICATION] =*=*=*=*=*=*=*=*=*=*=");
			System.out.println("=*=                                                  =*=");
			System.out.println("=*=**************************************************=*=");
			System.out.println("= Tapez 1 pour modifier votre nom et prénom            =");
			System.out.println("= Tapez 2 pour modifier email                          =");
			System.out.println("= Tapez 3 pour modifier CIN                            =");
			System.out.println("= Tapez 4 pour modifier Tel                            =");
			System.out.println("= Tapez 5 pour modifier Password                       =");
			System.out.println("= Tapez 6 pour Retourner Au men principale             =");
			System.out.println("=*=                                                  =*=");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
			System.out.println("");

			chaine=typeAuClavier.
					    StringAuClavier(sc, "Entrez Votre choix : ");
			choix = sc.nextInt();
			
			switch(choix) {
				case 1 :{  
					SrvClient.modifierProfile(1,c.getId());
						} break ; 
				case 2 :{
					SrvClient.modifierProfile(3,c.getId());
				} break ; 
				case 3 :{
					SrvClient.modifierProfile(4,c.getId());
				}break ; 
				case 4 :{ 
					SrvClient.modifierProfile(5,c.getId());
				} ; break ;  
			}
		} while(choix >= 1 && choix < 6  );
		
		return 0;
	}

	@Override
	public int menuRetrait() {
		Integer choix;
		
		do {
			System.out.println("=*=*=*=*=*=*=  MENU [RETRAIT ARGENT] =*=*=*=*=*=*=*=*=*=");
			System.out.println("=*=                                                  =*=");
			System.out.println("=*=                                                  =*=");
			System.out.println("= Tapez 1 pour faire un retrait de 100 DH              =");
			System.out.println("= Tapez 2 pour faire un retrait de 200 DH              =");
			System.out.println("= Tapez 3 pour faire un retrait de 400 DH              =");
			System.out.println("= Tapez 4 pour faire un retrait de 1000 DH             =");
			System.out.println("= Tapez 5 pour faire un retrait de 2000 DH             =");
			System.out.println("= Tapez 6 pour Retourner Au men principale             =");
			System.out.println("=*=                                                  =*=");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
			System.out.println("");

			
			chaine=typeAuClavier.
					    StringAuClavier(sc, "Entrez Votre choix : ");
			choix = sc.nextInt();
			
			switch(choix) {
				case 1 :{  SrvClient.
					    retrait(c,c.getComptesClient().get(posCompte),Double.valueOf(100)); 
						} break ; 
				case 2 :{
					SrvClient.
				    retrait(c,c.getComptesClient().get(posCompte),Double.valueOf(200)); 
				} ; break ; 
				case 3 :{
					SrvClient.
				    retrait(c,c.getComptesClient().get(posCompte),Double.valueOf(400)); 
				}break ; 
				case 4 :{ 
					SrvClient.
				    retrait(c,c.getComptesClient().get(posCompte),Double.valueOf(1000)); 
				} ; break ; 
				case 5 :{
					SrvClient.
				    retrait(c,c.getComptesClient().get(posCompte),Double.valueOf(2000)); 
				} ;  break ; 
			}
		
		} while(choix >= 1 && choix < 6 );
		
		return 0;
	}

	@Override
	public int menuInformations() {
		Integer choix;
		do {
			System.out.println("=*=*=*=*=*=*=  MENU [INFORMATION] =*=*=*=*=*=*=*=*=*=*=*=*=*=");
			System.out.println("=*=                                                      =*=");
			System.out.println("=*=                                                      =*=");
			System.out.println("= Tapez 1 pour afficher votre profile                      =");
			System.out.println("= Tapez 2 pour afficher le solde de votre compte           =");
			System.out.println("= Tapez 3 pour afficher les dernieres operation du compte  =");
			System.out.println("= Tapez 4 pour afficher les operations d'aujourd'hui       =");
			System.out.println("= Tapez 5 pour Retourner Au men principale                 =");
			System.out.println("=*=                                                      =*=");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
			System.out.println("");

			
			chaine=typeAuClavier.
					   StringAuClavier(sc, "Entrez Votre choix : ");
			choix = sc.nextInt();
			
			switch(choix) {
				case 1 :{  
						System.out.println(c.toString());
						} break ; 
				case 2 :{
					System.out.println( c.getComptesClient().get(posCompte).getSolde() ); 
				}  break ; 
				case 3 :{
					SrvClient.derniereresOpérations();
				}break ; 
				
				case 4 :{ 
					SrvClient.getC().getLogs().forEach(
							log -> {
								if( log.getDate().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
									log.getDate().getMonth() == LocalDate.now().getMonth()  &&
									log.getDate().getYear() == LocalDate.now().getYear() ) 
								{
									System.out.println(log.toString());
								}
							}
					);
				   
				} break; 
		
			}
		
		} while(choix >= 1 && choix < 5 );
		return 0;
	}





}
