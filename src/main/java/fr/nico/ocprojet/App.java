package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.List;

/**
 * App correspond à la classe main
 *
 * initialise la liste des joueurs
 */
public class App 
{
    public static void main( String[] args )
    {
        /**
         * création d'une liste de joueurs
         *
         * Les jeux disponibles permettent seulement un mode 2 joueurs
         */
        List<Player> players = new ArrayList<>();
        Player joueur = new Human();
        Player adversaire = new Bot();
        players.add(joueur);
        players.add(adversaire);

    }
}
