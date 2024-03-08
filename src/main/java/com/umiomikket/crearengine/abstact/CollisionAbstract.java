package com.umiomikket.crearengine.abstact;

import com.umiomikket.crearengine.collisions.CheckCollision;
import com.umiomikket.crearengine.graphics.LineRender;
import com.umiomikket.crearengine.utils.vectors.VectorFloat;

public abstract class CollisionAbstract {
    public abstract VectorFloat[] getVertices();

    public boolean touch(CollisionAbstract collisionComponent) {
        return CheckCollision.touch(
            CheckCollision.toMassive(getVertices()),
            CheckCollision.toMassive(collisionComponent.getVertices())
        );
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

    public void render(RenderAbstract renderComponent) {
        LineRender lineRender = new LineRender(renderComponent);
        VectorFloat[] vectors = getVertices();

        for (int i = 1; i < vectors.length; i++) {
            lineRender.point1.setPosition((int) vectors[i - 1].x, (int) vectors[i - 1].y);
            lineRender.point1.setPosition((int) vectors[i].x, (int) vectors[i].y);
            lineRender.render();
        }
    }
}
