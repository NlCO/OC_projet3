package fr.nico.ocprojet.StepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.nico.ocprojet.*;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.List;

public class RechercheStepdefs {
    private Launcher launcher;
    private GamePlay partie;
    private Player testeur;

    @Before
    public void background(){
        launcher = new Launcher();
        launcher.initPlayers();
        launcher.setGames(Games.R);
        launcher.setMode(GameMode.CHALLENGER);
        launcher.attributionDesRoles(GameMode.CHALLENGER);
        launcher.creerPartie();
        partie = launcher.getPartie();
    }

    @Given("Un joueur de recherche ayant le statut codeur")
    public void unJoueurDeRechercheAyantLeStatutCodeur() {
        testeur = launcher.getPlayers().get(1);
    }

    @When("il génère une combinaison de chiffres")
    public void ilGénèreUneCombinaisonDeChiffres() {
        partie.initialiserLaPartie();
    }

    @Then("cette combinaison chiffrée est associée à son adversaire")
    public void cetteCombinaisonChiffréeEstAssociéeÀSonAdversaire() {
        Assert.assertEquals(1, partie.getCombinaisons().size());
        Assert.assertEquals(partie.getTailleCombinaison() ,partie.getCombinaisons().get(partie.getAdversaire(testeur)).length());
    }

    @Given("un joueur ayant généré une combinaison de chiffres")
    public void unJoueurAyantGénéréUneCombinaisonDeChiffres() {
        testeur = launcher.getPlayers().get(1);
        partie.initialiserLaPartie();
    }

    @When("l'adversaire propose une combinaison de chiffres")
    public void lAdversaireProposeUneCombinaisonDeChiffres() {
        List<String[]> propositions = new ArrayList<>();
        partie.getPlayersPropostions().put(testeur, propositions);
        String proposition = partie.demandeDeCombinaison(testeur);
        String resultat = partie.evaluerProposition(partie.getCombinaisons().get(partie.getAdversaire(testeur)),proposition);
        String[] resultatTour = {proposition, resultat};
        propositions = partie.getPlayersPropostions().get(testeur);
        propositions.add(resultatTour);
        partie.getPlayersPropostions().put(partie.getAdversaire(testeur), propositions);
    }

    @Then("Le resultat de l'evaluation est de la bonne longeur et contient seulement les opérateurs autorisés")
    public void leResultatDeLEvaluationEstDeLaBonneLongeurEtContientSeulementLesOpérateursAutorisés() {
        List<String[]> propositions = partie.getPlayersPropostions().get(partie.getAdversaire(testeur));
        String lastResultat = propositions.get(propositions.size() - 1)[1];
        Assert.assertEquals(partie.getTailleCombinaison(), lastResultat.length());
        Assert.assertTrue(lastResultat.matches("(=|-|\\+){" + partie.getTailleCombinaison() + "}"));
    }
}
