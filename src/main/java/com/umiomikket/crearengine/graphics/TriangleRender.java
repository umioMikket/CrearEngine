package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.managers.RenderManager;
import com.umiomikket.crearengine.utils.vectors.Vector;

import java.awt.*;

public class TriangleRender {
    private final RenderManager renderManager;

    private Color color;
    public final Vector point1, point2, point3;

    public TriangleRender(RenderManager renderManager) {
        this.renderManager = renderManager;

        color = Color.WHITE;
        point1 = new Vector(0, 0);
        point2 = new Vector(0, 0);
        point3 = new Vector(0, 0);
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void setColor(int color) { this.color = new Color(color); }

    public void render() {
        Graphics2D g2d = renderManager.getGraphicsScreen();

        g2d.setColor(color);
        g2d.fillPolygon(
            new int[] {point1.getX(), point2.getX(), point3.getX()},
            new int[] {point1.getY(), point2.getY(), point3.getY()},
            3
        );
        g2d.dispose();
    }
}
