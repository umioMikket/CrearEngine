package com.umiomikket.crearengine.collisions;

import com.umiomikket.crearengine.utils.vectors.VectorFloat;

import java.util.ArrayList;

public class PolygonCollision {
    public final ArrayList<VectorFloat> points;

    public PolygonCollision() {
        points = new ArrayList<>();
    }

    public VectorFloat[] getVertices() {
        return points.toArray(new VectorFloat[0]);
    }
}
