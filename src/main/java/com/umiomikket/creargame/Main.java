package com.umiomikket.creargame;

import com.umiomikket.crearengine.GameBox;
import com.umiomikket.crearengine.listeners.RenderListener;

public class Main extends RenderListener {
    public static void main(String[] args) {
        GameBox game = new GameBox();
    }

    public void render(GameBox gameBox, int frameNumber, boolean endFrame) {}

    public void exit(GameBox gameBox) {}
}
