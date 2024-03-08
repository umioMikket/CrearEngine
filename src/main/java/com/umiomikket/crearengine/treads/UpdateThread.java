package com.umiomikket.crearengine.treads;

import com.umiomikket.crearengine.GameBox;

public class UpdateThread implements Runnable {
    private final GameBox gameBox;

    public UpdateThread(GameBox gameBox) {
        this.gameBox = gameBox;
    }

    public void run() {
        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        int updateNumber = 0;

        while (gameBox.updateLoop.isRunning()) {
            long nowTime = System.nanoTime();
            delta += (nowTime - lastTime) / gameBox.updateLoop.getUpdateTime();
            lastTime = nowTime;

            while (delta >= 1) {
                gameBox.playUpdate(updateNumber, false);
                updateNumber++;
                delta -= 1;
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                updateNumber++;
                gameBox.playUpdate(updateNumber, true);
                updateNumber = 0;
            }
        }
    }
}
