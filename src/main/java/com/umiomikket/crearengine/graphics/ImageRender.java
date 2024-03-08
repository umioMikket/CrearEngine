package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.vectors.Vector;
import com.umiomikket.crearengine.utils.vectors.VectorRotated;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageRender {
    private final RenderAbstract render;
    private BufferedImage image;

    public final VectorRotated positionRotated;
    public final Vector offset;

    public ImageRender(RenderAbstract render) {
        this.render = render;

        positionRotated = new VectorRotated(0, 0, 0f);
        offset = new Vector(0, 0);
    }

    public BufferedImage getImage() { return image; }
    public void setImage(Image image) { this.image = (BufferedImage) image; }
    public void setImage(BufferedImage image) { this.image = image; }

    public void setImage(String path) {
        image = null;

        try { image = ImageIO.read(new File(path)); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void render() { render(1d, 1d); }

    public void render(int width, int height) {
        double scaleX = 0, scaleY = 0;

        if (width < image.getWidth()) scaleX = (double) width / image.getWidth();
        else if (width > image.getWidth()) scaleX = (double) image.getWidth() / width;
        if (height < image.getHeight()) scaleY = (double) width / image.getHeight();
        else if (height > image.getHeight()) scaleY = (double) image.getHeight() / width;

        render(scaleX, scaleY);
    }

    public void render(double scaleX, double scaleY) {
        if (image == null) return;
        if (scaleX == 0 || scaleY == 0) return;

        Graphics2D g2d = render.getGraphics();
        AffineTransform transform = new AffineTransform();

        transform.translate(positionRotated.getX() - offset.getX(), positionRotated.getY() - offset.getY());
        transform.rotate(Math.toRadians(positionRotated.getRotation()), offset.getX(), offset.getY());
        transform.scale(scaleX, scaleY);

        g2d.drawImage(image, transform, null);
        g2d.dispose();
    }

    public void dispose() { image.flush(); }
}
