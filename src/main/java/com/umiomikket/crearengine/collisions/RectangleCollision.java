package com.umiomikket.crearengine.collisions;

import com.umiomikket.crearengine.abstact.CollisionAbstract;
import com.umiomikket.crearengine.utils.sizes.SizeFloat;
import com.umiomikket.crearengine.utils.vectors.VectorFloat;
import com.umiomikket.crearengine.utils.vectors.VectorRotatedFloat;

public class RectangleCollision extends CollisionAbstract {
    public final VectorRotatedFloat positionRotated;
    public final SizeFloat size;
    public final VectorFloat offset;

    public RectangleCollision() {
        positionRotated = new VectorRotatedFloat(0f, 0f, 0f);
        size = new SizeFloat(0f, 0f);
        offset = new VectorFloat(0f, 0f);
    }

    public VectorFloat[] getVertices() {
        double rad = Math.toRadians(positionRotated.rotation);
        double sin = Math.sin(rad),
               cos = Math.cos(rad);

        float centerX = positionRotated.x,
              centerY = positionRotated.y;

        float pX = -offset.x,
              pY = -offset.y,
              pWidth = size.width - offset.x,
              pHeight = size.height - offset.y;

        return new VectorFloat[] {
            new VectorFloat(centerX + (float) (pX * cos - pY * sin), centerY + (float) (pX * sin + pY * cos)),
            new VectorFloat(centerX + (float) (pWidth * cos - pY * sin), centerY + (float) (pWidth * sin + pY * cos)),
            new VectorFloat(centerX + (float) (pWidth * cos - pHeight * sin), centerY + (float) (pWidth * sin + pHeight * cos)),
            new VectorFloat(centerX + (float) (pX * cos - pHeight * sin), centerY + (float) (pX * sin + pHeight * cos))
        };
    }
}
