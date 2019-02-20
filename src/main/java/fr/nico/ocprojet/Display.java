package fr.nico.ocprojet;


/**
 * Display prend en charge l'affichage
 */
public class Display {


    public void afficheGameList() {
        for (Games games : Games.values()) {
            System.out.println(games.getAbbreviation() + " -> " + games.toString());
        }
    }

    public void demandeChoixJeu(){
        afficheGameList();
        System.out.println("Veuillez choisir un jeu (R ou M) : ");
    }

    public void erreurSaisie() {
        System.out.println("la valeur saisie ne correspond pas aux valeurs ou formats attendues");
    }

    public void afficheModeList() {
        for (GameMode mode: GameMode.values()) {
            System.out.println(mode.getCode() + " -> " + mode.toString());
        }
    }

    public void demandeChoixMode(){
        afficheModeList();
        System.out.println("Veuillez choisir un mode (C, D ou VS) : ");
    }

    public void inviteGenerationCombinaison() {
        System.out.println("Veuillez saisir un combinaison de 4 chiffres");
    }

}
