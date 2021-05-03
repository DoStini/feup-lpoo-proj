package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.model.util.Position;

public class CircularMovement implements MovementController{
    private final Element element;
    private final double radius;
    private double angle;
    private final double angularSpeed;

    public CircularMovement(Element element, double radius, double initAngle, double angularSpeed) {
        this.element = element;
        this.radius = radius;
        this.angle = Math.toRadians(initAngle);
        this.angularSpeed = Math.toRadians(angularSpeed);
    }

    @Override
    public Element move() {
        Position curCenter = element.getPosition().sub(
            new Position(
                Math.toIntExact(Math.round(radius*Math.cos(this.angle))),
                Math.toIntExact(Math.round(radius*Math.sin(this.angle)))
            )
        );

        this.angle = angle + angularSpeed;

        this.angle %= 2*Math.PI;

        Position newPosition = new Position(
                Math.toIntExact(Math.round(radius*Math.cos(this.angle))),
                Math.toIntExact(Math.round(radius*Math.sin(this.angle)))
        ).add(curCenter);

        element.setPosition(newPosition);

        return element;
    }
}
