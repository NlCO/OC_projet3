package fr.nico.ocprojet;

import java.util.Random;

/**
 * Bot étend la classe @see Player et représente un joueur non-humain
 */
public class Bot extends Player {

    public Games choixDuJeu(){
        return null;
    }

    public GameMode choixDuMode(){
        return null;
    }

    @Override
    public String genereUneCombinaison() {
        Random random = new Random();
        return String.format("%04X", random.nextInt(10000));
    }

}
