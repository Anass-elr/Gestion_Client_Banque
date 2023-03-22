package metier.clients;

import metier.authentification.IServiceIHM;
import presentation.modele.Client;

public interface IServiceIHMClient extends IServiceIHM {
    int menuModification();
    int menuRetrait();
    int menuInformations();
 
}
