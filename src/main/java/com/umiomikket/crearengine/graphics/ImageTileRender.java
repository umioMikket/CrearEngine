package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.sizes.Size;
import com.umiomikket.crearengine.utils.vectors.Vector;
import com.umiomikket.crearengine.utils.vectors.VectorRotated;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageTileRender {
    private final RenderAbstract render;
    private BufferedImage image;
    private BufferedImage subImage;
    private boolean isSaveSubImage;

    public final VectorRotated positionRotated;
    public final Vector offset;

    public final Vector tilePosition;
    public final Size tileSize;
    public final Vector tileOffset;

    public ImageTileRender(RenderAbstract render) {
        this.render = render;

        positionRotated = new VectorRotated(0, 0, 0f);
        offset = new Vector(0, 0);

        tilePosition = new Vector(0, 0);
        tileSize = new Size(0, 0);
        tileOffset = new Vector(0, 0);
    }

    public BufferedImage getImage() { return image; }
    public void setImage(Image image) { this.image = (BufferedImage) image; saveSubImage(); }
    public void setImage(BufferedImage image) { this.image = image; saveSubImage(); }

    public void setImage(String path) {
        image = null;

        try { image = ImageIO.read(new File(path)); }
        catch (Exception e) { e.printStackTrace(); }

        saveSubImage();
    }

    public boolean hasSavedSubImage() { return isSaveSubImage; }

    public BufferedImage getSavedSubImage() {
        if (!isSaveSubImage) return null;
        return subImage;
    }

    public void saveSubImage() {
        if (!isSaveSubImage) isSaveSubImage = true;

        subImage = new BufferedImage(tileSize.width, tileSize.height, BufferedImage.TYPE_INT_RGB);
        int xp = tilePosition.x - tileOffset.x, yp = tilePosition.y - tileOffset.getY();

        for (int y = 0; y < tileSize.height; y++) {
            for (int x = 0; x < tileSize.width; x++) {
                subImage.setRGB(x, y, image.getRGB(xp + x, yp + y));
            }
        }
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
        if (tileSize.width == 0 || tileSize.height == 0) return;

        Graphics2D g2d = render.getGraphics();
        AffineTransform transform = new AffineTransform();
        transform.translate(positionRotated.getX() - offset.getX(), positionRotated.getY() - offset.getY());
        transform.rotate(Math.toRadians(positionRotated.getRotation()), offset.getX(), offset.getY());
        transform.scale(scaleX, scaleY);

        if (isSaveSubImage) g2d.drawImage(subImage, transform, null);
        else { saveSubImage(); g2d.drawImage(subImage, transform, null); }

        g2d.dispose();
    }

    public void dispose() {
        image.flush();
        subImage.flush();
    }
}
