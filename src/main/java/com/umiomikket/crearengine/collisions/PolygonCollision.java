package com.umiomikket.crearengine.collisions;

import com.umiomikket.crearengine.utils.vectors.VectorFloat;

import java.util.ArrayList;

public class PolygonCollision {
    public final ArrayList<VectorFloat> points;

    public PolygonCollision() {
        points = new ArrayList<>();
    }

    public VectorFloat[] getVertices() {
        VectorFloat[] v = points.toArray(new VectorFloat[0]);
        return v;
    }

    public boolean touch(VectorFloat[] points) {
        return CheckCollision.touch(
            CheckCollision.toMassive(getVertices()),
            CheckCollision.toMassive(points)
        );
    }

    public boolean touch(float[][] points) {
        return CheckCollision.touch(
            CheckCollision.toMassive(getVertices()),
            points
        );
    }
}
