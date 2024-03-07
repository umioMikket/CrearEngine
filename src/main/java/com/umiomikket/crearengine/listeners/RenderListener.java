package com.umiomikket.crearengine.listeners;

import com.umiomikket.crearengine.GameBox;

public abstract class RenderListener {
    public abstract void render(GameBox gameBox, int frameNumber, boolean endFrame);
}
