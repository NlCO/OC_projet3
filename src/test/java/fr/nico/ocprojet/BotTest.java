package fr.nico.ocprojet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BotTest {

    @Test
    public void listeCombinaisonsPossibles() {
        //arrange
        Bot testeur = new Bot();
        StringBuilder motif = new StringBuilder();
        int tailleCombinaison = 4;
        List<String> colors = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J"));
        List<String[]> historique = new ArrayList<>();
        String[] dernierResultat = {"ABCD" , "0,2"};
        historique.add(dernierResultat);
        //act
        testeur.constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
        String[] dernierResultat2 = {"ABFA" , "1,2"};
        historique.add(dernierResultat2);
        testeur.constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
        String[] dernierResultat3 = {"BAFG" , "3,0"};
        historique.add(dernierResultat3);
        testeur.constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
        String[] dernierResultat4 = {"JHIG" , "0,0"};
        historique.add(dernierResultat4);
        testeur.constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
        //assert

    }
}