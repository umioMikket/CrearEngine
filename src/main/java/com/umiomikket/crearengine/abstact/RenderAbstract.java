package com.umiomikket.crearengine.abstact;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class RenderAbstract {
    public abstract BufferedImage getImage();
    public abstract Dimension getSize();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract Graphics2D getGraphics();
    public abstract void clear();
    public abstract void update();
    public abstract void resize();
}
