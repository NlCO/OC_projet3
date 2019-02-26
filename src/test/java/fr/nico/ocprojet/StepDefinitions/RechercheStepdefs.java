package fr.nico.ocprojet.StepDefinitions;

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

    @Given("Un joueur ayant le statut codeur")
    public void unJoueurAyantLeStatutCodeurEtLeParamètreTaille() {
        launcher = new Launcher();
        launcher.attributionDesRoles(GameMode.CHALLENGER);
        partie = new Recherche(launcher.getPlayers(),4,10);
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
    @Given("un joueur joueur ayant généré une combinaison")
    public void unJoueurJoueurAyantGénéréUneCombinaison() {
        launcher = new Launcher();
        launcher.attributionDesRoles(GameMode.CHALLENGER);
        partie = new Recherche(launcher.getPlayers(),4,10);
        partie.initialiserLaPartie();
    }

    @When("l'adversaire propose une combinaison")
    public void ilProposeUneCombinaison() {
        for (Player p: launcher.getPlayers()) {
            if (p.isDecodeur()){
                List<String[]> propositions = new ArrayList<>();
                partie.getPlayersPropostions().put(partie.getAdversaire(p), propositions);
                String proposition = partie.demandeDeCombinaison(partie.getAdversaire(p));
                String resultat = partie.evaluerProposition(p,proposition);
                String[] resultatTour = {proposition, resultat};
                propositions = partie.getPlayersPropostions().get(p);
                propositions.add(resultatTour);
                partie.getPlayersPropostions().put(p, propositions);
            }
        }
    }

    @Then("Le resultat de l'evaluation est de la bonne longeur et contient seulement des symboles autorisés")
    public void leResultatDeLEvaluationEstDeLaBonneLongeurEtContientSeulementDesSymboles() {
        for (Player p: launcher.getPlayers()) {
            if (p.isDecodeur()){
                List<String[]> propositions = partie.getPlayersPropostions().get(p);
                String lastResultat = propositions.get(propositions.size() - 1)[1];
                Assert.assertEquals(partie.getTailleCombinaison(), lastResultat.length());
                Assert.assertTrue(lastResultat.matches("(=|-|\\+){" + partie.getTailleCombinaison() + "}"));
            }
        }
    }
}
