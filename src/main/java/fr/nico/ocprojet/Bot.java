package fr.nico.ocprojet;

import java.util.List;
import java.util.Random;

/**
 * La classe Bot décrit la classe {@link Player Player} pour un joueur non-humain et gére son "IA"
 */
public class Bot extends Player {
    private String name;
    private Random random;

    public Bot() {
        name = "Bot le Bot";
        random = new Random();
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
        return String.format("%0" + tailleCombinaison + "d", random.nextInt((int)Math.pow(10,tailleCombinaison)));
    }

    @Override
    public String proposeUneCombinaison(int tailleCombinaison, List<String[]> historique) {
        StringBuilder proposition = new StringBuilder();
        StringBuilder borneMin = new StringBuilder();
        StringBuilder borneMax = new StringBuilder();
        for (int i = 0; i < tailleCombinaison; i++) {
            borneMin.append(0);
            borneMax.append(9);
        }
        //System.out.println("Tentative : " + historique.size());
        //System.out.println("Bot - Recherche +/- - Etat initiale des borne Min  : " + borneMin.toString() + " / Max : " + borneMax.toString() );

        if (historique.size() == 0) {
            for (int i = 0; i < tailleCombinaison; i++) {
                proposition.append(5);
            }
            //System.out.println("Bot - Recherche +/- - première proposition : " + proposition.toString());
        } else {
            for (String[] resultat: historique) {
                int position = 0;
                for (String c: resultat[1].split("")) {
                    switch (c) {
                        case "=":
                            borneMin.setCharAt(position, resultat[0].charAt(position));
                            borneMax.setCharAt(position, resultat[0].charAt(position));
                            break;
                        case "-":
                            borneMax.setCharAt(position, resultat[0].charAt(position));
                            break;
                        case "+":
                            borneMin.setCharAt(position, resultat[0].charAt(position));
                            break;
                    }
                    //System.out.println("Bot - Recherche +- - proposition " + resultat[0] + " ajustement des bornes Min/Max : " + borneMin.toString() + "/" + borneMax.toString());
                    if ((historique.indexOf(resultat) + 1)  == historique.size()) {
                        int biais = ((borneMax.toString().charAt(position) - '0') > 5)?1:0;
                        int alea = ((borneMax.toString().charAt(position) - '0') + (borneMin.toString().charAt(position) - '0') + biais)/2;
                        proposition.append(alea);
                    }
                    position++;

                }
             //System.out.println(proposition.toString());
            }

        }
        return proposition.toString();
    }

    @Override
    public String getName() {
        return name;
    }
}
