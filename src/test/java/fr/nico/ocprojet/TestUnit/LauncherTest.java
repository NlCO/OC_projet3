package fr.nico.ocprojet.TestUnit;

import fr.nico.ocprojet.Launcher;
import fr.nico.ocprojet.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LauncherTest {

    @Test
    public void initialisationPlayers(){
        //arrange
        Launcher lanceur = new Launcher();
        //act
        List<Player> players = lanceur.getPlayers();
        //assert
        Assert.assertEquals(2, players.size());
    }
}

