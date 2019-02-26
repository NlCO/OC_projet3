package fr.nico.ocprojet.TestUnit;

import fr.nico.ocprojet.GameMode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameModeTest {

    @Test
    public void modeFromCode() {
        //arrange
        String testLettreC = "C";
        String testLettreD = "D";
        String testLettreVS = "VS";
        //act
        GameMode modeFromC = GameMode.modeFromCode(testLettreC);
        GameMode modeFromD = GameMode.modeFromCode(testLettreD);
        GameMode modeFromVS = GameMode.modeFromCode(testLettreVS);
        //assert
        Assert.assertEquals(GameMode.CHALLENGER, modeFromC);
        Assert.assertEquals(GameMode.DEFENSEUR, modeFromD);
        Assert.assertEquals(GameMode.DUEL, modeFromVS);
    }
}