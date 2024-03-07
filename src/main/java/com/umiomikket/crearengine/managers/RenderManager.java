package com.umiomikket.crearengine.managers;

import com.umiomikket.crearengine.GameBox;
import com.umiomikket.crearengine.GameWindow;
import com.umiomikket.crearengine.graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class RenderManager {
    public static final int MODE_RENDER_SPEED = 0;
    public static final int MODE_RENDER_DEFAULT = 1;
    public static final int MODE_RENDER_QUALITY = 2;

    private int renderMode;
    private boolean isAntiAliasing;
    private boolean isAntiAliasingText;
    private boolean isInterpolation;

    public final GameBox gameBox;

    private final GameWindow window;
    private BufferedImage screen;

    public RenderManager(GameBox gameBox) {
        this.gameBox = gameBox;
        window = gameBox.window;

        screen = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        resizeScreen();

        renderMode = MODE_RENDER_DEFAULT;
    }

    public BufferedImage getScreen() { return screen; }
    public int getWidthScreen() { return screen.getWidth(); }
    public int getHeightScreen() { return screen.getHeight(); }
    public Graphics2D getGraphicsScreen() {
        Graphics2D g2d = screen.createGraphics();

        if (renderMode == MODE_RENDER_SPEED)
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        else if(renderMode == MODE_RENDER_DEFAULT)
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
        else if(renderMode == MODE_RENDER_QUALITY)
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (isAntiAliasing)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isAntiAliasingText)
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (isInterpolation)
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        return g2d;
    }

    public void clearScreen() { Arrays.fill(((DataBufferInt) screen.getRaster().getDataBuffer()).getData(), 0); }
    public void updateScreen() { window.canvas.getGraphics().drawImage(screen, 0, 0, null); }

    public void resizeScreen() {
        screen.flush();
        window.canvas.setSize(window.frame.getSize());

        screen = new BufferedImage(
            window.canvas.getWidth(),
            window.canvas.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );
    }

    public int getRenderMode() { return renderMode; }
    public void setRenderMode(int value) { if (value > 0 && value < 4) renderMode = value; }

    public boolean isAntiAliasing() { return isAntiAliasing; }
    public void setAntiAliasing(boolean value) { this.isAntiAliasing = value; }
    public void enableAntiAliasing() { isAntiAliasing = true; }
    public void disableAntiAliasing() { isAntiAliasing = false; }

    public boolean isAntiAliasingText() { return isAntiAliasingText; }
    public void setAntiAliasingText(boolean antiAliasingText) { isAntiAliasingText = antiAliasingText; }
    public void enableAntiAliasingText() { isAntiAliasingText = true; }
    public void disableAntiAliasingText() { isAntiAliasingText = false; }

    public boolean isInterpolation() { return isInterpolation; }
    public void setInterpolation(boolean value) { this.isInterpolation = value; }
    public void enableInterpolation() { isInterpolation = true; }
    public void disableInterpolation() { isInterpolation = false; }

    public ImageRender createImage() { return new ImageRender(this); }
    public LineRender createLine() { return new LineRender(this); }
    public OvalRender createOval() { return new OvalRender(this); }
    public OvalBorderRender createOvalBorder() { return new OvalBorderRender(this); }
    public TriangleRender createTriangle() { return new TriangleRender(this); }
    public TriangleBorderRender createTriangleBorder() { return new TriangleBorderRender(this); }
    public RectangleRender createRectangle() { return new RectangleRender(this); }
    public RectangleBorderRender createRectangleBorder() { return new RectangleBorderRender(this); }
    public TextRender createText() { return new TextRender(this); }

    public void draw(ImageRender image) { image.render(); }
    public void draw(ImageRender image, int width, int height) { image.render(width, height); }
    public void draw(ImageRender image, double scaleX, double scaleY) { image.render(scaleX, scaleY); }

    public void draw(OvalRender oval) { oval.render(); }
    public void draw(OvalRender oval, int width, int height) { oval.render(width, height); }
    public void draw(OvalRender oval, double scaleX, double scaleY) { oval.render(scaleX, scaleY); }

    public void draw(TriangleRender polygon) { polygon.render(); }

    public void draw(RectangleRender rectangle) { rectangle.render(); }
    public void draw(RectangleRender rectangle, int width, int height) { rectangle.render(width, height); }
    public void draw(RectangleRender rectangle, double scaleX, double scaleY) { rectangle.render(scaleX, scaleY); }

    public void draw(TextRender text) { text.render(); }
    public void draw(TextRender text, double scaleX, double scaleY) { text.render(scaleX, scaleY); }

    public void draw(Color color, int x, int y) { draw(color.hashCode(), x, y); }

    public void draw(int color, int x, int y) {
        if (x <= 0 || y <= 0 ||
            x >= this.getWidthScreen() || y >= this.getHeightScreen())
            return;

        this.getScreen().setRGB(x, y, color);
    }
}
