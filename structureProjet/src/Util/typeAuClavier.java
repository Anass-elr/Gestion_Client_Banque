package Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class typeAuClavier {
	
	public static String StringAuClavier(Scanner inp,String obj)  {
		System.out.println("-----------Entrez "+ obj +" -------------");
		 String c=inp.nextLine();
		return c;
	} 
	
	public static Integer IntegerAuClavier(Scanner inp,String obj) throws Exception {
		
		int nb_essais=1;
		Integer c;
		do {
			try {
				System.out.println("-----------Entrez "+ obj +" -------------");
			    c=inp.nextInt();
			 	return c;
			}
			catch(InputMismatchException e) {
				System.out.println("Il faut Entrez un Entier !!!,Recomencez");
				nb_essais++;
				inp.nextLine();
			}
			
		}while(nb_essais<=3);
		throw new Exception("Saisie Echoué");
	
	} 
	
	
	
	public static Long LongAuClavier(Scanner inp,String obj) throws Exception {
		
		int nb_essais=0;
		Long c;
		do {
			try {
				System.out.println("-----------Entrez "+ obj +" -------------");
				c=inp.nextLong();
			 	return c;
			}
			catch(InputMismatchException e) {
				System.out.println("Il faut Entrez un Numero !!!,Recomencez");
				nb_essais++;
				inp.nextLine();
			}
			
		}while(nb_essais<=3);
		throw new Exception("Saisie Echoué");
	
	} 

	
	public static Double DoubleAuClavier(Scanner inp,String obj) throws Exception {
	
		
		int nb_essais=0;
		Double c;
		do {
			try {
				System.out.println("-----------Entrez "+ obj +" -------------");
				c=inp.nextDouble();
			 	return c;
			}
			catch(InputMismatchException e) {
				System.out.println("Il faut Entrez un Numero !!!,Recomencez");
				nb_essais++;
				inp.nextLine();
			}
			
		}while(nb_essais<=3);
		throw new Exception("Saisie Echoué");
	}
	
	
	
	
}
