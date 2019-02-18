package fr.nico.ocprojet.TestUnit;

import fr.nico.ocprojet.Launcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LauncherTest {

    @Test
    public void AjouterUnJeuAListeVide() {
        //arrange
        Launcher lanceur = new Launcher();

        //act
        lanceur.ajouterJeu("Recherche +/-");
        List<String> listeJeuxDisponibles = lanceur.jeuxDispos();

        //Assert
        Assert.assertEquals("Recherche +/-", listeJeuxDisponibles.get(0));
    }


    @Test
    public void AjouterUnJeuAListeExistanteNonVide() {
        //arrange
        Launcher lanceur = new Launcher();
        lanceur.ajouterJeu("Recherche +/-");


        //act
        lanceur.ajouterJeu("Mastermind");
        List<String> listeJeuxDisponibles = lanceur.jeuxDispos();

        //Assert
        Assert.assertEquals("Recherche +/-", listeJeuxDisponibles.get(0));
        Assert.assertEquals("Mastermind",listeJeuxDisponibles.get(listeJeuxDisponibles.size()-1));
    }

}

