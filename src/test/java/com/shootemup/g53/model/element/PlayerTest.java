package com.shootemup.g53.model.element;

import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Position fakePosition;

    @BeforeEach
    void setUp() {
        fakePosition = new Position(1,2);
    }

    @Test
    void constructorGetters() {
        Player player = new Player(fakePosition, 2, 20, "", 5);

        Assertions.assertEquals(fakePosition, player.getPosition());
        Assertions.assertEquals(2, player.getHeight());
        Assertions.assertEquals(20, player.getHealth());
        Assertions.assertEquals("", player.getColor());
        Assertions.assertEquals(5, player.getSpeed());
        Assertions.assertEquals(0, player.getCoins());
        Assertions.assertEquals(0, player.getEssence());
    }

    @Test
    void addCoinsEssence() {
        Player player = new Player(fakePosition, 2, 20, "", 5);

        Assertions.assertEquals(0, player.getCoins());
        Assertions.assertEquals(0, player.getEssence());

        player.addCoin(5);

        Assertions.assertEquals(5, player.getCoins());
        Assertions.assertEquals(0, player.getEssence());

        player.addCoin(2);

        Assertions.assertEquals(7, player.getCoins());
        Assertions.assertEquals(0, player.getEssence());

        player.addEssence(1);

        Assertions.assertEquals(7, player.getCoins());
        Assertions.assertEquals(1, player.getEssence());

        player.addEssence(3);

        Assertions.assertEquals(7, player.getCoins());
        Assertions.assertEquals(4, player.getEssence());

        Assertions.assertTrue(player.removeEssence(1));

        Assertions.assertEquals(7, player.getCoins());
        Assertions.assertEquals(3, player.getEssence());

        Assertions.assertFalse(player.removeEssence(4));

        Assertions.assertEquals(7, player.getCoins());
        Assertions.assertEquals(3, player.getEssence());

        Assertions.assertTrue(player.removeEssence(3));

        Assertions.assertEquals(7, player.getCoins());
        Assertions.assertEquals(0, player.getEssence());
    }

    @Test
    void playerClone() {
        Player player = new Player(fakePosition, 2, 20, "", 5);

        Player player2 = (Player) player.clone();

        Assertions.assertEquals(fakePosition, player2.getPosition());
        Assertions.assertEquals(2, player2.getHeight());
        Assertions.assertEquals(20, player2.getHealth());
        Assertions.assertEquals("", player2.getColor());
        Assertions.assertEquals(5, player2.getSpeed());
        Assertions.assertEquals(0, player2.getCoins());
        Assertions.assertEquals(0, player2.getEssence());
    }
}