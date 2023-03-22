package metier;

import java.util.List;
import java.util.Scanner;

import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;

public interface InteractiveConsole {

    Scanner sc = new Scanner(System.in);

    default void fermerClavier(){
            sc.close();
    }


	

}
