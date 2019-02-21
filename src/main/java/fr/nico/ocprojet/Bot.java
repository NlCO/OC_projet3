package fr.nico.ocprojet;

import java.util.Random;

/**
 * Bot étend la classe @see Player et représente un joueur non-humain
 */
public class Bot extends Player {
    private String name;

    public Bot() {
        name = "Bot le Bot";
    }

    @Override
    public Games choixDuJeu(){
        return null;
    }

    @Override
    public GameMode choixDuMode(){
        return null;
    }

    @Override
    public String genereUneCombinaison(int tailleCombinaison) {
        //Todo : à revoir le cast
        Random random = new Random();
        return String.format("%0" + tailleCombinaison + "d", random.nextInt((int)Math.pow(10,tailleCombinaison)));
    }

    public String getName() {
        return name;
    }
}
