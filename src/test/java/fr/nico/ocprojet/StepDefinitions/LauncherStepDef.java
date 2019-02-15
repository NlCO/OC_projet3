package fr.nico.ocprojet.StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.nico.ocprojet.Launcher;
import org.junit.Assert;


import java.util.List;

public class LauncherStepDef {
    private Launcher lanceur;
    private List<String> listeJeuxDispos;
    private List<Launcher.GameMode> modesGame;

    @Given("les jeux {string}, {string} et {string} sont disponibles")
    public void lesJeuxEtSontDisponibles(String jeu1, String jeu2, String jeu3) {
        lanceur = new Launcher();
        lanceur.ajouterJeu(jeu1);
        lanceur.ajouterJeu(jeu2);
        lanceur.ajouterJeu(jeu3);
    }

    @When("j'appelle la liste des jeux")
    public void jAppelleLaListeDesJeux() {
        listeJeuxDispos = lanceur.jeuxDispos();
    }

    @Then("j'obtiens une liste de {int} jeux : {string}, {string} et {string}")
    public void jObtiensUneListeDeJeuxEt(int nombreJeu, String jeu1, String jeu2, String jeu3) {
        Assert.assertEquals(nombreJeu, listeJeuxDispos.size());
        Assert.assertEquals(jeu1, listeJeuxDispos.get(0));
        Assert.assertEquals(jeu2, listeJeuxDispos.get(1));
        Assert.assertEquals(jeu3, listeJeuxDispos.get(2));
    }

    @Given("aucun jeu n'est disponible")
    public void aucunJeuNEstDisponible() {
         lanceur = new Launcher();
    }

    @Then("j'obtiens une liste vide de {int} jeu")
    public void jObtiensUneListeVideDeJeu(int nombreJeu) {
        Assert.assertEquals(nombreJeu, listeJeuxDispos.size());

    }

    @Given("un fois le jeu choisi")
    public void unFoisLeJeuChoisi() {
        lanceur = new Launcher();
    }

    @When("j'affiche les modes jeu disponibles")
    public void jAfficheLesModesJeuDisponibles() {
        modesGame = lanceur.gameModes();
    }

    @Then("les modes {word}, {word} et {word} doivent être proposés")
    public void lesModesEtDoiventÊtreProposés(String mode1, String mode2, String mode3) {
        //TODO : prise en charge des enum dans cucumber
        Launcher.GameMode mode1Convert = Launcher.GameMode.valueOf(mode1);
        Launcher.GameMode mode2Convert = Launcher.GameMode.valueOf(mode2);
        Launcher.GameMode mode3Convert = Launcher.GameMode.valueOf(mode3);
        Assert.assertTrue(modesGame.contains(mode1Convert));
        Assert.assertTrue(modesGame.contains(mode2Convert));
        Assert.assertTrue(modesGame.contains(mode3Convert));
    }

    @Given("pour un joueur")
    public void pourUnJoueur() {
        //player = new Player();
    }

    @When("je choisi le {}")
    public void jeChoisiLeGameMode(Launcher.GameMode role) {
        //player.setRoles(role);
    }

    @Then("le statuts des roles est {} et {}")
    public void leStatutsDesRolesEstAttaquantEtDéfenseur() {
    }
}
