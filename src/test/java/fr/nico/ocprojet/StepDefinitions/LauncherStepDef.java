package fr.nico.ocprojet.StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.nico.ocprojet.*;
import org.junit.Assert;


import java.util.List;

public class LauncherStepDef {
    private Launcher lanceur;
    private List<Game> listeJeuxDispos;
    private List<GameMode> modesGame;
    private Player player;
    private Player adversaire;

    @Given("le lancement du programme")
    public void leLancementDuProgramme() {
        lanceur = new Launcher();
    }

    @When("j'appelle la liste des jeux")
    public void jAppelleLaListeDesJeux() {
        listeJeuxDispos = lanceur.jeuxDispos();
    }

    @Then("j'obtiens une liste de {int} jeux contenant {word} et {string}")
    public void jObtiensUneListeDeJeux(int nombreJeu, String jeu1, String jeu2) {
        Assert.assertEquals(nombreJeu, listeJeuxDispos.size());
        Assert.assertTrue(listeJeuxDispos.toString().contains(jeu1));
        Assert.assertTrue(listeJeuxDispos.toString().contains(jeu2));
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
        Assert.assertTrue(modesGame.contains(GameMode.valueOf(mode1)));
        Assert.assertTrue(modesGame.contains(GameMode.valueOf(mode2)));
        Assert.assertTrue(modesGame.contains(GameMode.valueOf(mode3)));
    }

    @Given("pour une liste de joueurs")
    public void pourUneListeDeJoueurs() {
        lanceur = new Launcher();
    }

    @When("le joueur choisi le mode {word}")
    public void leJoueurChoisiLeGameMode(String gamemode) {
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
