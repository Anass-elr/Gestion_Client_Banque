package presentation.controleur;

import metier.authentification.*;
import metier.authentification.ServiceIHM;
import metier.clients.ServiceIHMClient;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Sexe;

import java.util.Scanner;

public class MaBanque {
        public static IAuth loginService;
        public static void main(String[] args) {
        		
         
        
                Banque maBanque
                        = new Banque(   "BP",
                                        "Hassan Rabat",
                                        "212535224433",
                                        "bp@banquePop.ma");

                maBanque.getClientsDeBanque().add(
                  new Client("xx","xx","xx","xx","xx"));
              
              ServiceIHM menuApp=new ServiceIHM(maBanque);
              
              
              
              menuApp.menuGlobal();
              
                      	
        	
              
              
        }

		
}
