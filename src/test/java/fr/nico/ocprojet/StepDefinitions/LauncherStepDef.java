package fr.nico.ocprojet.StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.nico.ocprojet.*;
import org.junit.Assert;


import java.util.List;

public class LauncherStepDef {
    private Launcher lanceur;
    private List<String> listeJeuxDispos;
    private List<GameMode> modesGame;
    private Player player;
    private Player adversaire;

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

    @Then("La liste contient {int} jeu")
    public void laListeContientJeu(int nombreJeu) {
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
        GameMode mode1Convert = GameMode.valueOf(mode1);
        GameMode mode2Convert = GameMode.valueOf(mode2);
        GameMode mode3Convert = GameMode.valueOf(mode3);
        Assert.assertTrue(modesGame.contains(mode1Convert));
        Assert.assertTrue(modesGame.contains(mode2Convert));
        Assert.assertTrue(modesGame.contains(mode3Convert));
    }

    @Given("pour un joueur et son adversaire")
    public void pourUnJoueur() {
        player = new Human();
        adversaire = new Bot();
    }

    @When("le joueur choisi le mode {word}")
    public void ilChoisiLeGameMode(String gamemode) {
        GameMode mode = GameMode.valueOf(gamemode);
        //player.setRoles(mode);
    }

    @Then("le statut de ses roles est {word}, {word} et celui de son adversaire {word}, {word}")
    public void leStatutDeSesRolesEstEtCeluiDeSonAdversaire(String playerdecodeur, String playercodeur, String advdecodeur, String advcodeur) {
        //Assert.assertEquals(playerdecodeur, player.isDecodeur());
        //Assert.assertEquals(playercodeur, player.isCodeur());
        //Assert.assertEquals(advdecodeur, adversaire.isDecodeur());
        //Assert.assertEquals(advcodeur, adversaire.isCodeur());
    }
}
