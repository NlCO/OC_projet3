package fr.nico.ocprojet.StepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import fr.nico.ocprojet.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class MastermindStepdefs {
    private Launcher launcher;
    private GamePlay partie;
    private Player testeur;

    @Before
    public void background(){
        launcher = new Launcher();
        launcher.initPlayers();
        launcher.setGames(Games.M);
        launcher.setMode(GameMode.CHALLENGER);
        launcher.attributionDesRoles(GameMode.CHALLENGER);
        launcher.creerPartie();
        partie = launcher.getPartie();
    }


    @Given("Un joueur de Mastermind ayant le statut codeur")
    public void unJoueurDeMastermindAyantLeStatutCodeur() {
        testeur = launcher.getPlayers().get(1);
    }

    @When("il génère une combinaison de symbole")
    public void ilGénèreUneCombinaisonDeSymbole() {
        partie.initialiserLaPartie();
    }

/*    @Then("cette combinaison de symboles est associée à son adversaire")
    public void cetteCombinaisonDeSymbolesEstAssociéeÀSonAdversaire() {
        Assert.assertEquals(1, partie.getCombinaisons().size());
        Assert.assertEquals(partie.getTailleCombinaison() ,partie.getCombinaisons().get(partie.getAdversaire(testeur)).length());
    }
*/
    @Then("cette combinaison de symboles est associée à son adversaire et contient seulement les symboles autorisés")
    public void cetteCombinaisonDeSymbolesEstAssociéeÀSonAdversaireEtContientSeulementLesSymbolesAutorisés() {
        Assert.assertEquals(1, partie.getCombinaisons().size());
        Assert.assertEquals(partie.getTailleCombinaison() ,partie.getCombinaisons().get(partie.getAdversaire(testeur)).length());
        Assert.assertTrue(partie.getCombinaisons().get(partie.getAdversaire(testeur)).matches("([A-J]){" + partie.getTailleCombinaison() + "}"));
    }

    @Given("un joueur ayant généré une combinaison de symbole")
    public void unJoueurAyantGénéréUneCombinaisonDeSymbole() {
        testeur = launcher.getPlayers().get(1);
        partie.initialiserLaPartie();
    }

    @When("l'adversaire propose une combinaison de symbole")
    public void lAdversaireProposeUneCombinaisonDeSymbole() {
        List<String[]> propositions = new ArrayList<>();
        partie.getPlayersPropostions().put(testeur, propositions);
        String proposition = partie.demandeDeCombinaison(testeur);
        String resultat = partie.evaluerProposition(partie.getCombinaisons().get(partie.getAdversaire(testeur)),proposition);
        String[] resultatTour = {proposition, resultat};
        propositions = partie.getPlayersPropostions().get(testeur);
        propositions.add(resultatTour);
        partie.getPlayersPropostions().put(partie.getAdversaire(testeur), propositions);
    }

/*    @Then("Le resultat de l'evaluation est de la bonne longeur et contient seulement les symboles autorisés")
    public void leResultatDeLEvaluationEstDeLaBonneLongeurEtContientSeulementLesSymbolesAutorisés() {
        List<String[]> propositions = partie.getPlayersPropostions().get(partie.getAdversaire(testeur));
        String lastResultat = propositions.get(propositions.size() - 1)[1];
        System.out.println(lastResultat);
        //Assert.assertEquals(partie.getTailleCombinaison(), lastResultat.length());
        //Assert.assertTrue(lastResultat.matches("([A-J]){" + partie.getTailleCombinaison() + "}"));
        Assert.assertTrue(lastResultat.matches("\\d+,\\d+"));
    }
*/

    @Then("Le resultat de l'evaluation est deux nombre séparés par une virgule")
    public void leResultatDeLEvaluationEstDeuxNombreSéparésParUneVirgule() {
        List<String[]> propositions = partie.getPlayersPropostions().get(partie.getAdversaire(testeur));
        String derniereProposition = propositions.get(propositions.size() - 1)[0];
        String dernierResultat = propositions.get(propositions.size() - 1)[1];
        Assert.assertTrue(derniereProposition.matches("([A-J]){" + partie.getTailleCombinaison() + "}"));
        Assert.assertTrue(dernierResultat.matches("\\d+,\\d+"));
    }
}
