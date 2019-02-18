package fr.nico.ocprojet;

import java.util.Scanner;

/**
 * Human étend la classe Player et représente un joueur humain
 */
public class Human extends Player {

    /**
     * Method pour récupéré les saisies clavier du joueur
     *
     * @return la saisie clavier sous forme texte
     */
    public String reponse() {
        Scanner sc = new Scanner(System.in);
        String saisie = sc.nextLine();
        return saisie;
    }

}
