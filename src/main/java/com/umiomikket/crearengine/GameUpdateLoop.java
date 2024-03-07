package com.umiomikket.crearengine;

import com.umiomikket.crearengine.treads.UpdateThread;

public class GameUpdateLoop {
    private final Thread thread;
    private int framesPerSecond;
    private double frameTime;

    private boolean isRunning;

    public GameUpdateLoop(GameBox gameBox) {
        thread = new Thread(new UpdateThread(gameBox));
        setUpdatesPerSecond(60);
        isRunning = false;
    }

    public void start() {
        if (isRunning) return;
        isRunning = true;
        thread.start();
    }

    public void stop() { isRunning = false; }

    public double getUpdateTime() { return frameTime; }
    public int getUpdatesPerSecond() { return framesPerSecond; }
    public void setUpdatesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        frameTime = 1000000000D / framesPerSecond;
    }

    public boolean isRunning() { return isRunning; }
}
