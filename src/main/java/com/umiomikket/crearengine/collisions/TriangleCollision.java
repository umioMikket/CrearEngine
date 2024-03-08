package com.umiomikket.crearengine.collisions;

import com.umiomikket.crearengine.utils.vectors.VectorFloat;

public class TriangleCollision {
    public final VectorFloat point1;
    public final VectorFloat point2;
    public final VectorFloat point3;

    public TriangleCollision() {
        point1 = new VectorFloat(0, 0);
        point2 = new VectorFloat(0, 0);
        point3 = new VectorFloat(0, 0);
    }

    public VectorFloat[] getVertices() {
        VectorFloat[] positions = new VectorFloat[3];
        positions[0] = point1;
        positions[1] = point2;
        positions[2] = point3;

        return positions;
    }
}
