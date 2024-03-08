package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.sizes.Size;
import com.umiomikket.crearengine.utils.vectors.Vector;
import com.umiomikket.crearengine.utils.vectors.VectorRotated;

import java.awt.*;

public class RectangleBorderRender {
    private final RenderAbstract render;

    private Color color;
    public final VectorRotated positionRotated;
    public final Vector offset;
    public final Size size;
    private int strokeSize;

    public RectangleBorderRender(RenderAbstract render) {
        this.render = render;

        color = Color.WHITE;
        positionRotated = new VectorRotated(0, 0, 0f);
        offset = new Vector(0, 0);
        size = new Size(0, 0);
        strokeSize = 1;
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void setColor(int color) { this.color = new Color(color); }

    public int getStrokeSize() { return strokeSize; }
    public void setStrokeSize(int strokeSize) { this.strokeSize = strokeSize; }

    public void render() { render(1d, 1d); }

    public void render(int width, int height) {
        double scaleX = 0, scaleY = 0;

        if (width < size.getWidth()) scaleX = (double) width / size.getWidth();
        else if (width > size.getWidth()) scaleX = (double) size.getWidth() / width;
        if (height < size.getHeight()) scaleY = (double) width / size.getHeight();
        else if (height > size.getHeight()) scaleY = (double) size.getHeight() / width;

        render(scaleX, scaleY);
    }

    public void render(double scaleX, double scaleY) {
        if (strokeSize == 0) return;
        if (scaleX == 0 || scaleY == 0) return;

        Graphics2D g2d = render.getGraphics();

        g2d.translate(positionRotated.getX() - offset.getX(), positionRotated.getY() - offset.getY());
        g2d.rotate(Math.toRadians(positionRotated.getRotation()), offset.getX(), offset.getY());
        g2d.scale(scaleX, scaleY);
        g2d.translate(-positionRotated.getX() + offset.getX(), -positionRotated.getY() + offset.getY());

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeSize));
        g2d.drawRect(
            positionRotated.getX() - offset.getX(), positionRotated.getY() - offset.getY(),
            size.getWidth(), size.getHeight()
        );

        g2d.dispose();
    }
}
