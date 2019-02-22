package fr.nico.ocprojet;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.List;
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

    @Override
    public String proposeUneCombinaison(int tailleCombinaison, List<String[]> historique) {
        StringBuilder proposition = new StringBuilder();
        if (historique.size() >0) {
            proposition.append(String.format("%5" + tailleCombinaison + "d"));
        } else {
            String borneMin = String.format("%0" + tailleCombinaison + "d");
            String borneMax = String.format("%9" + tailleCombinaison + "d");
            for (String[] resultat: historique) {
                int position = 0;
                for (String c: resultat[1].split("")) {
                    switch (c){
                        case "=":
                            borneMin.charAt(position) = resultat[0].charAt(position);
                            borneMax.charAt(position) = resultat[0].charAt(position);
                            break;
                        case "-":

                            break;
                        case "+":
                            break;
                    }
                }

            }
        }
        return proposition.toString();
    }

    public String getName() {
        return name;
    }
}
