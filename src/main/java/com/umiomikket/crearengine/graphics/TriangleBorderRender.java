package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.vectors.Vector;

import java.awt.*;

public class TriangleBorderRender {
    private final RenderAbstract render;

    private Color color;
    public final Vector point1, point2, point3;
    private int strokeSize;

    public TriangleBorderRender(RenderAbstract render) {
        this.render = render;

        color = Color.WHITE;
        point1 = new Vector(0, 0);
        point2 = new Vector(0, 0);
        point3 = new Vector(0, 0);
        strokeSize = 1;
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void setColor(int color) { this.color = new Color(color); }

    public int getStrokeSize() { return strokeSize; }
    public void setStrokeSize(int strokeSize) { this.strokeSize = strokeSize; }

    public void render() {
        Graphics2D g2d = render.getGraphics();

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeSize));
        g2d.drawPolygon(
            new int[] {point1.getX(), point2.getX(), point3.getX()},
            new int[] {point1.getY(), point2.getY(), point3.getY()},
            3
        );
        g2d.dispose();
    }
}
