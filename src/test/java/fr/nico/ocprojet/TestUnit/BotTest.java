package fr.nico.ocprojet.TestUnit;

import fr.nico.ocprojet.Bot;
import org.junit.Assert;
import org.junit.Before;
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
        //assert
        Assert.assertEquals(1896,testeur.getCombinaisonsPossibles().size());
    }

    @Test
    public void miseAListeCombinaison() {
        //arrange
        Bot testeur = new Bot();
        StringBuilder motif = new StringBuilder();
        int tailleCombinaison = 4;
        List<String> colors = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J"));
        List<String[]> historique = new ArrayList<>();
        String[] premierResultat = {"ABCD" , "0,2"};
        historique.add(premierResultat);
        testeur.constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
        String[] secondResultat = {"EFGH", "1,1"};
        historique.add(secondResultat);
        //act
        testeur.constitutionListeCombinaisonPossible(tailleCombinaison, colors, historique);
        //assert
        Assert.assertEquals(168,testeur.getCombinaisonsPossibles().size());
    }
}