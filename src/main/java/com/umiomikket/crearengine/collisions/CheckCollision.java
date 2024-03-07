package com.umiomikket.crearengine.collisions;

import com.umiomikket.crearengine.utils.vectors.VectorFloat;

public class CheckCollision {
    public static float[][] toMassive(VectorFloat[] positions) {
        float[][] intMassive = new float[positions.length][2];

        for (int i = 0; i < positions.length; i++) {
            intMassive[i] = new float[] {positions[i].x, positions[i].y};
        }

        return intMassive;
    }

    public static boolean touch(float[][] A, float[][] B) {
        if (A.length < 3 || B.length < 3) return false;

        for (int i = 0; i < A.length; i++) {
            float nx = A[(i + 1) % A.length][1] - A[i][1];
            float ny = A[i][0] - A[(i + 1) % A.length][0];

            float minA = Float.POSITIVE_INFINITY, maxA = Float.NEGATIVE_INFINITY;
            float minB = Float.POSITIVE_INFINITY, maxB = Float.NEGATIVE_INFINITY;

            for (float[] ints : A) {
                float proj = ints[0] * nx + ints[1] * ny;
                minA = Math.min(minA, proj);
                maxA = Math.max(maxA, proj);
            }

            for (float[] ints : B) {
                float proj = ints[0] * nx + ints[1] * ny;
                minB = Math.min(minB, proj);
                maxB = Math.max(maxB, proj);
            }

            if (maxA < minB || maxB < minA) return false;
        }

        for (int i = 0; i < B.length; i++) {
            float nx = B[(i + 1) % B.length][1] - B[i][1];
            float ny = B[i][0] - B[(i + 1) % B.length][0];

            float minA = Float.POSITIVE_INFINITY, maxA = Float.NEGATIVE_INFINITY;
            float minB = Float.POSITIVE_INFINITY, maxB = Float.NEGATIVE_INFINITY;

            for (float[] ints : A) {
                double proj = ints[0] * nx + ints[1] * ny;
                minA = (float) Math.min(minA, proj);
                maxA = (float) Math.max(maxA, proj);
            }

            for (float[] ints : B) {
                double proj = ints[0] * nx + ints[1] * ny;
                minB = (float) Math.min(minB, proj);
                maxB = (float) Math.max(maxB, proj);
            }

            if (maxA < minB || maxB < minA) return false;
        }

        return true;
    }
}
