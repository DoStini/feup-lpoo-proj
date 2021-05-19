package com.shootemup.g53.controller.gamebuilder;

import com.shootemup.g53.controller.game.GameController;
import com.shootemup.g53.controller.gamebuilder.element.ElementGenerator;
import com.shootemup.g53.controller.gamebuilder.element.SpaceshipGenerator;

public class WaveFactory {
    int wave;
    private final int baseEnemies;
    private final float enemiesFactor;
    private final int bossWaveFactor;
    private final long baseSkip;
    private final float timeFactor;

    public WaveFactory(int baseEnemies, float enemiesFactor, int bossWaveFactor, int baseSkip, float timeFactor) {
        this.baseEnemies = baseEnemies;
        this.enemiesFactor = enemiesFactor;
        this.bossWaveFactor = bossWaveFactor;
        this.baseSkip = baseSkip;
        this.timeFactor = timeFactor;
        this.wave = 1;
    }

    public Wave getNextWave(GameController gameController) {
        ElementGenerator generator;
        Wave result;
        int gameWidth = gameController.getGameModel().getWidth();
        if (wave % bossWaveFactor == 0) {
            generator = new SpaceshipGenerator(gameController, 0, gameWidth , 1, 2,
                    25, 30, 5);
            result = new Wave(gameController, 1, generator,
                    wave/bossWaveFactor);
        } else {
            generator = new SpaceshipGenerator(gameController, 0, gameWidth, 4, 8,
                    2, 10, 10);

            result = new Wave(gameController, (long) (baseSkip*(1-timeFactor*wave)),
                    generator, Math.round(baseEnemies+wave*enemiesFactor));
        }

        wave++;
        return result;
    }
}
