package metier.authentification;

import presentation.modele.Banque;
import presentation.modele.Utilisateur;

public interface IAuth {
     Utilisateur  seConnecter(String login,String pass,Boolean verif);
     void SeDéconnecter();
}
