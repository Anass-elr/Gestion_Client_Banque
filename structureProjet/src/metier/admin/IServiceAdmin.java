package metier.admin;

import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.TableauDeBord;

import java.time.LocalDateTime;
import java.util.List;

import metier.InteractiveConsole;

public interface IServiceAdmin  {
    Client          nouveauClient();
    Client          nouveauCompteClientExistant(Long id);

    Client          chercherClientParId(Long id);
    List<Client>    chercherClientParNom(String nom);
    List<Client>    chercherClientParPrénom(String prenom);
    Client          chercherClientParCin(String cin);
    Client          chercherClientParEmail(String email);

    Compte          chercherCompteParId(String idCompte);
    List<Compte>    chercherCompteParSolde(double solde);
    List<Compte>    chercherCompteParDateCreation(LocalDateTime date);
    List<Compte>    chercherCompteParPropriaitaire(Client propriataire);

    Client          modifierClient(String filtre,Long id);
    boolean         supprimerClient(Long id);

    TableauDeBord   calculerEtAfficherStatistiques(Banque B);

    void   trierClientParNom();
    List<Client>    trierClientParCin();
    List<Client>    trierClientParEmail();
    List<Client>    trierClientParAdresse();
    List<Client>    trierClientParSoldeCompte();
    List<Compte>    trierComptesParSolde();
    List<Compte>    trierComptesParDateDeCreation();
    List<Compte>    trierComptesParNomPropriaitaire();



}
