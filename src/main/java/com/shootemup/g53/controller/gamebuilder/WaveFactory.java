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
        if (isBossWave()) {
            result = getBossWave(gameController, gameWidth);
        } else {
            result = getNormalWave(gameController, gameWidth);
        }

        wave++;
        return result;
    }

    private Wave getNormalWave(GameController gameController, int gameWidth) {
        Wave result;
        ElementGenerator generator;
        generator = new SpaceshipGenerator(gameController, 0, gameWidth, 2, 4,
                2, 5, 10);

        float skip = Math.max(1, baseSkip*(1-timeFactor*(wave-1)));

        result = new Wave(gameController, (long) skip,
                generator, Math.round(baseEnemies+(wave-1)*enemiesFactor));
        return result;
    }

    private Wave getBossWave(GameController gameController, int gameWidth) {
        Wave result;
        ElementGenerator generator;
        generator = new SpaceshipGenerator(gameController, 0, gameWidth, 1, 2,
                25, 30, 5);
        result = new Wave(gameController, 1, generator,
                wave/bossWaveFactor);
        return result;
    }

    private boolean isBossWave() {
        return wave % bossWaveFactor == 0;
    }
}
