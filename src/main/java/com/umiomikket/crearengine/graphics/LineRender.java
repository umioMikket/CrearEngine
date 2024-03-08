package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.vectors.Vector;

import java.awt.*;

public class LineRender {
    private final RenderAbstract render;

    public final Vector point1;
    public final Vector point2;
    private int strokeSize;
    private Color color;

    public LineRender(RenderAbstract render) {
        this.render = render;
        point1 = new Vector(0, 0);
        point2 = new Vector(0, 0);
        strokeSize = 1;
        color = Color.WHITE;
    }

    public int getStrokeSize() { return strokeSize; }
    public void setStrokeSize(int strokeSize) { this.strokeSize = strokeSize; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void setColor(int color) { this.color = new Color(color); }

    public void render() {
        Graphics2D g2d = render.getGraphics();
        g2d.setStroke(new BasicStroke(strokeSize));
        g2d.setColor(color);
        g2d.drawLine(point1.x, point1.y, point2.x, point2.y);
        g2d.dispose();
    }
}
