package com.umiomikket.crearengine.managers;

import com.umiomikket.crearengine.GameBox;
import com.umiomikket.crearengine.GameWindow;
import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.inTools.GraphicManager;
import com.umiomikket.crearengine.utils.inTools.RenderSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class RenderManager extends RenderAbstract {
    private final GameWindow window;
    private BufferedImage screen;

    public final GraphicManager graphicsManager;
    public final RenderSettings renderingSettings;

    public RenderManager(GameBox gameBox) {
        window = gameBox.window;

        screen = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        resize();

        graphicsManager = new GraphicManager(this);
        renderingSettings = new RenderSettings();
    }

    public BufferedImage getImage() { return screen; }

    public Dimension getSize() { return new Dimension(getWidth(), getHeight()); }
    public int getWidth() { return screen.getWidth(); }
    public int getHeight() { return screen.getHeight(); }

    public Graphics2D getGraphics() {
        Graphics2D g2d = screen.createGraphics();
        renderingSettings.setRenderingSettings(g2d);
        return g2d;
    }

    public void clear() {
        Arrays.fill(((DataBufferInt) screen.getRaster().getDataBuffer()).getData(), 0);
    }
    public void update() {
        window.canvas.getGraphics().drawImage(screen, 0, 0, null);
    }

    public void resize() {
        screen.flush();
        window.canvas.setSize(window.frame.getSize());

        screen = new BufferedImage(
            window.canvas.getWidth(),
            window.canvas.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );
    }

    public void dispose() {
        screen.flush();
    }
}
