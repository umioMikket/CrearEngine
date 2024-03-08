package com.umiomikket.crearengine.treads;

import com.umiomikket.crearengine.GameBox;

public class RenderThread extends Thread {
    private final GameBox gameBox;

    public RenderThread(GameBox gameBox) {
        this.gameBox = gameBox;
    }

    public void run() {
        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        int frameNumber = 0;

        while (gameBox.renderLoop.isRunning()) {
            long nowTime = System.nanoTime();
            delta += (nowTime - lastTime) / gameBox.renderLoop.getFrameTime();
            lastTime = nowTime;

            while (delta >= 1) {
                render(frameNumber, false);
                frameNumber++;
                delta -= 1;
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                frameNumber++;
                render(frameNumber, true);
                frameNumber = 0;
            }
        }
    }

    public void render(int frameNumber, boolean isEndFrame) {
        if (gameBox.window.canvas.getGraphics() == null) return;

        gameBox.renderManager.clear();
        gameBox.playRender(frameNumber, isEndFrame);
        gameBox.renderManager.update();
    }
}
