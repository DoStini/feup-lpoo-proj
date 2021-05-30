package com.shootemup.g53.controller.observer;

import com.shootemup.g53.controller.movement.FallDownMovement;
import com.shootemup.g53.controller.movement.MovementStrategy;
import com.shootemup.g53.controller.player.PlayerController;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObserverTest {
    private EssenceObserver essenceObserver;
    private PlayerLifeObserver playerLifeObserver;
    private PlayerScoreObserver playerScoreObserver;
    private WaveCompletionObserver waveCompletionObserver;
    @BeforeEach
    void setup() {
        essenceObserver = Mockito.mock(EssenceObserver.class);
        playerLifeObserver = Mockito.mock(PlayerLifeObserver.class);
        playerScoreObserver = Mockito.mock(PlayerScoreObserver.class);
        waveCompletionObserver = Mockito.mock(WaveCompletionObserver.class);

    }

    @Test
    void testEssenceController() {
        EssenceController essenceController = new EssenceController();
        int essence = 5;
        essenceController.setAmount(essence);
        assertEquals(essenceController.getAmount(),essence);
        essenceController.addObserver(essenceObserver);
        assertEquals(essenceController.getEssenceObservers(), Arrays.asList(essenceObserver));
        essenceController.notifyObservers();
        Mockito.verify(essenceObserver,Mockito.times(1)).setEssence(essence);

        assertEquals(essence,essenceController.getAmount());
        essenceController.removeObserver(essenceObserver);
        assertEquals(essenceController.getEssenceObservers(), Arrays.asList());
    }

    @Test
    void testPlayerLifeController() {
        LifeController lifeController = new LifeController();
        int health = 5;
        lifeController.setLife(health);
        lifeController.addObserver(playerLifeObserver);
        assertEquals(lifeController.getPlayerLifeObservers(), Arrays.asList(playerLifeObserver));
        lifeController.notifyObservers();
        Mockito.verify(playerLifeObserver,Mockito.times(1)).updateLife(health);

        assertEquals(health,lifeController.getLifeToRemove());
        lifeController.removeObserver(playerLifeObserver);
        assertEquals(lifeController.getPlayerLifeObservers(), Arrays.asList());
    }

    @Test
    void testScoreController() {
        ScoreController scoreController = new ScoreController();

        scoreController.addObserver(playerScoreObserver);
        assertEquals(scoreController.getLevelCompletedObservers(), Arrays.asList(playerScoreObserver));
        scoreController.notifyObservers();
        scoreController.notifyObservers();
        Mockito.verify(playerScoreObserver,Mockito.times(2)).updateScore();
        scoreController.removeObserver(playerScoreObserver);
        assertEquals(scoreController.getLevelCompletedObservers(), Arrays.asList());

    }

    @Test
    void testWaveCompletionController() {
        WaveCompletionController waveCompletionController = new WaveCompletionController();

        waveCompletionController.addObserver(waveCompletionObserver);
        assertEquals(waveCompletionController.getWaveCompletionObservers(), Arrays.asList(waveCompletionObserver));
        waveCompletionController.notifyObservers();
        waveCompletionController.notifyObservers();
        Mockito.verify(waveCompletionObserver,Mockito.times(2)).updateWaveCompleted();
        waveCompletionController.removeObserver(waveCompletionObserver);
        assertEquals(waveCompletionController.getWaveCompletionObservers(), Arrays.asList());

    }

}
