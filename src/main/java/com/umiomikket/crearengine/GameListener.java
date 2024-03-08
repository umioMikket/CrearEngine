package com.umiomikket.crearengine;

import com.umiomikket.crearengine.GameBox;

public abstract class GameListener {
    public abstract void update(GameBox gameBox, int updateNumber, boolean endUpdate);
    public abstract void render(GameBox gameBox, int frameNumber, boolean endFrame);
    public abstract void start(GameBox gameBox);
    public abstract void exit(GameBox gameBox);
}
