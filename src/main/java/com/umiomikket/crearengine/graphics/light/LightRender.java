package com.umiomikket.crearengine.graphics.light;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.vectors.Vector;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class LightRender {
    private final LightMap lightMap;

    public final Vector position;
    private Color color;
    private float innerAlpha;
    private float outerAlpha;
    private int radius;

    public LightRender(LightMap lightMap) {
        this.lightMap = lightMap;
        position = new Vector(0, 0);
    }

    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }

    public Color getColor() { return color; }
    public void setColor(int color) { this.color = new Color(color); }
    public void setColor(Color color) { this.color = color; }

    public float getInnerAlpha() { return innerAlpha; }
    public void setInnerAlpha(float innerAlpha) { this.innerAlpha = innerAlpha; }

    public float getOuterAlpha() { return outerAlpha; }
    public void setOuterAlpha(float outerAlpha) { this.outerAlpha = outerAlpha; }

    public void render() {
        Graphics2D g2d = (Graphics2D) lightMap.getImage().getGraphics();

        g2d.setComposite(AlphaComposite.DstOut);
        RadialGradientPaint rgp = new RadialGradientPaint(
            position.x, position.y, radius , new float[] {0f, 1f},
            new Color[] {
                new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (innerAlpha * 255)),
                new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (outerAlpha * 255))
            }
        );

        g2d.setPaint(rgp);
        g2d.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);

        g2d.setComposite(AlphaComposite.Xor);
        g2d.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);

        g2d.dispose();
    }
}
