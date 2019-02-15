package fr.nico.ocprojet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Launcher {
    private List<String> listeDeJeu = new ArrayList<>();

    public enum GameMode {
        CHALLENGER("Challenger"), DEFENSEUR("Défenseur"), DUEL("Duel");

        private String label;

        GameMode(String label){
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public void ajouterJeu (String nomDuJeu){
        listeDeJeu.add(nomDuJeu);
    }

    public List<String> jeuxDispos() {
        return listeDeJeu;
    }

    public List<GameMode> gameModes() {
        return Arrays.asList(GameMode.values());
    }

}

