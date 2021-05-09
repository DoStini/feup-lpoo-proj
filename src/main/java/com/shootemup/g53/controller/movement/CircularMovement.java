package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.element.MovableElement;
import com.shootemup.g53.model.util.Position;

public class CircularMovement implements MovementController{
    private final double radius;
    private double angle;
    private double angularSpeed;

    public CircularMovement(double radius, double initAngle, double angularSpeed) {
        this.radius = radius;
        this.angle = Math.toRadians(initAngle);
        this.angularSpeed = Math.toRadians(angularSpeed);
    }

    @Override
    public Position move(Position position, int speed) {
        Position curCenter = position.sub(
                new Position(
                        Math.toIntExact(Math.round(radius*Math.cos(this.angle))),
                        Math.toIntExact(Math.round(radius*Math.sin(this.angle)))
                )
        );

        this.angle = angle + angularSpeed;

        this.angle %= 2*Math.PI;

        return new Position(
                Math.toIntExact(Math.round(radius*Math.cos(this.angle))),
                Math.toIntExact(Math.round(radius*Math.sin(this.angle)))
        ).add(curCenter);
    }

    @Override
    public void handleFailedMovement() {
        this.angularSpeed = -this.angularSpeed;
    }
}
