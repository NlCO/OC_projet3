package fr.nico.ocprojet.StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.nico.ocprojet.*;
import org.junit.Assert;

public class RechercheStepdefs {
    private Launcher launcher;
    private GamePlay partie;

    @Given("Un joueur ayant le statut codeur")
    public void unJoueurAyantLeStatutCodeurEtLeParamètreTaille() {
        launcher = new Launcher();
        launcher.attributionDesRoles(GameMode.CHALLENGER);
        partie = new Recherche(launcher.getPlayers());
    }

    @When("il génère une combinaison")
    public void ilGénèreUneCombinaison() {
        partie.initialiserLaPartie();
    }

    @Then("cette combinaison est associée à son adversaire")
    public void cetteCombinaisonEstAssociéeÀSonAdversaire() {
        for (Player p: launcher.getPlayers()) {
            if (p.isCodeur()){
                Assert.assertEquals(1, partie.getCombinaisons().size());
                Assert.assertEquals(partie.getTailleCombinaison() ,partie.getCombinaisons().get(partie.getAdversaire(p)).length());

            }
        }

    }
}
