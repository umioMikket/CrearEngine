package com.umiomikket.crearengine.graphics.light;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.sizes.Size;
import com.umiomikket.crearengine.utils.vectors.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class LightMap {
    private final RenderAbstract render;

    private BufferedImage image;
    private Color color;

    public final Vector position;
    public final Vector offset;
    public final Size size;

    private boolean isImageSaved;

    public LightMap(RenderAbstract render) {
        this.render = render;

        position = new Vector(0, 0);
        offset = new Vector(0, 0);
        size = new Size(1, 1);

        isImageSaved = false;
    }

    public BufferedImage getImage() { return image; }

    public void saveImage() {
        if (!isImageSaved) isImageSaved = true;
        image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        clear();
    }

    public Color getColor() { return color; }
    public void setColor(int color) { this.color = new Color(color); }
    public void setColor(Color color) { this.color = color; }

    public void clear() { Arrays.fill(((DataBufferInt) image.getRaster().getDataBuffer()).getData(), color.hashCode()); }

    public void render() {
        Graphics2D g2d = render.getGraphics();
        if (!isImageSaved) saveImage();
        g2d.drawImage(image, position.x - offset.x, position.y - offset.y, null);
        g2d.dispose();
    }

    public void dispose() { image.flush(); }
}
