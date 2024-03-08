package com.umiomikket.crearengine;

import com.umiomikket.crearengine.collisions.RectangleCollision;
import com.umiomikket.crearengine.graphics.*;
import com.umiomikket.crearengine.graphics.light.LightMap;
import com.umiomikket.crearengine.managers.InputManager;
import com.umiomikket.crearengine.graphics.light.LightRender;
import com.umiomikket.crearengine.utils.inTools.GraphicManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends GameListener {
    public static LightMap lightMap;
    public static RectangleRender rr;
    public static RectangleRender orr;
    public static TextRender tr;
    public static RectangleCollision rc;
    public static RectangleCollision orc;
    public static GraphicManager gm;
    
    public static float angle = 34f;

    public static float x = 100f, y = 100f;
    public static float xs = 0f, ys = 0f;

    public static float ox = 100f, oy = 100f;
    public static float oxs = 0f, oys = 0f;

    public static float c = 0f;
    public static float oc = 0f;

    public static int hp = 10;
    public static int ohp = 10;

    public static void main(String[] args) {
        GameBox gameBox = new GameBox(new Main());
        gm = gameBox.renderManager.graphicsManager;
        tr = gm.createText();

        lightMap = new LightMap(gameBox.renderManager);
        lightMap.size.setSize(gameBox.renderManager.getWidth(), gameBox.renderManager.getHeight());
        lightMap.setColor(new Color(0, 0, 0, 255));
        lightMap.saveImage();

        rr = gm.createRectangle();
        rr.setColor(new Color(128, 255, 99, 205));
        rr.size.setSize(50, 50);
        rr.offset.setPosition(25, 25);

        rc = new RectangleCollision();
        rc.size.setSize(50f, 50f);
        rc.offset.setPosition(25f, 25f);

        orr = gm.createRectangle();
        orr.setColor(new Color(121, 202, 255, 219));
        orr.size.setSize(50, 50);
        orr.offset.setPosition(25, 25);

        orc = new RectangleCollision();
        orc.size.setSize(50f, 50f);
        orc.offset.setPosition(25f, 25f);

        gameBox.run();
    }

    public void update(GameBox gameBox, int updateNumber, boolean endUpdate) {}

    public void render(GameBox gameBox, int frameNumber, boolean endFrame) {
        angle += 0.1;

        x += xs; y += ys; xs *= 0.85f; ys *= 0.85f;
        ox += oxs; oy += oys; oxs *= 0.85f; oys *= 0.85f;

        if (c < 1) c = 0;
        else c -= 5f;

        if (oc < 1) oc = 0;
        else oc -= 5f;

        if (rc.touch(orc.getVertices())) rr.setColor(new Color(213, 255, 190, 205));
        else rr.setColor(new Color(128, 255, 99, 205));

        if (orc.touch(rc.getVertices())) orr.setColor(new Color(190, 223, 255, 205));
        else orr.setColor(new Color(99, 195, 255, 205));

        InputManager ip = gameBox.inputManager;
        if (ip.isKey(KeyEvent.VK_W)) ys = -10;
        if (ip.isKey(KeyEvent.VK_S)) ys = 10;
        if (ip.isKey(KeyEvent.VK_A)) xs = -10;
        if (ip.isKey(KeyEvent.VK_D)) xs = 10;
        if (ip.isKey(KeyEvent.VK_Q) && c <= 0 && hp > 0) {
            if (rc.touch(orc.getVertices())) { ohp--; oc = 100; }
            c = 100;
        }

        if (ip.isKey(KeyEvent.VK_I)) oys = -10;
        if (ip.isKey(KeyEvent.VK_K)) oys = 10;
        if (ip.isKey(KeyEvent.VK_J)) oxs = -10;
        if (ip.isKey(KeyEvent.VK_L)) oxs = 10;
        if (ip.isKey(KeyEvent.VK_U) && oc <= 0 && ohp > 0) {
            if (orc.touch(rc.getVertices())) { hp--; c = 100; }
            oc = 100;
        }

        rc.positionRotated.setPosition(x, y);
        orc.positionRotated.setPosition(ox, oy);

        tr.positionRotated.setPosition((int) x - 25, (int) y - 35);
        tr.setText(hp > 0? "Player 1 (" + (int) c + ") (" + hp + ")" : "Player 1 killed");
        tr.render();

        if (hp > 0) {
            rr.positionRotated.setPosition((int) x, (int) y);
            rr.render();
        }

        tr.positionRotated.setPosition((int) ox - 25, (int) oy - 35);
        tr.setText(ohp > 0? "Player 2 (" + (int) oc + ") (" + ohp + ")" : "Player 2 killed");
        tr.render();

        if (ohp > 0) {
            orr.positionRotated.setPosition((int) ox, (int) oy);
            orr.render();
        }

        RectangleRender rr = gm.createRectangle();
        rr.positionRotated.setPosition(300, 300);

        gameBox.renderManager.renderingSettings.setAntiAliasing(true);
        rr.positionRotated.setRotation(angle);
        gameBox.renderManager.renderingSettings.setAntiAliasingText(false);

        rr.size.setSize(100, 100);
        rr.offset.setPosition(50, 50);
        rr.setColor(Color.GRAY);
        rr.render();

        lightMap.clear();

        LightRender dlr = new LightRender(lightMap);

        dlr.setColor(new Color(255, 214, 152));
        dlr.setInnerAlpha(1f);
        dlr.setOuterAlpha(0f);
        dlr.position.setPosition(gameBox.inputManager.getMouseX(), gameBox.inputManager.getMouseY());
        dlr.setRadius(100);
        dlr.render();

        dlr.setColor(new Color(152, 231, 255));
        dlr.position.setPosition(300, 300);
        dlr.render();

        lightMap.render();

        if (endFrame) gameBox.windowManager.setTitle("FPS " + frameNumber);
    }

    public void start(GameBox gameBox) {}
    public void exit(GameBox gameBox) { gameBox.exit(); }
}
