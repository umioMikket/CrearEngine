package com.umiomikket.crearengine;

import com.umiomikket.crearengine.treads.RenderThread;

public class GameRenderLoop {
    private final Thread thread;
    private int framesPerSecond;
    private double frameTime;

    private boolean isRunning;

    public GameRenderLoop(GameBox gameBox) {
        thread = new Thread(new RenderThread(gameBox));
        setFramesPerSecond(60);
        isRunning = false;
    }

    public void start() {
        if (isRunning) return;
        isRunning = true;
        thread.start();
    }

    public void stop() { isRunning = false; }

    public double getFrameTime() { return frameTime; }
    public int getFramesPerSecond() { return framesPerSecond; }
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        frameTime = 1000000000D / (framesPerSecond - 1);
    }

    public boolean isRunning() { return isRunning; }
}
