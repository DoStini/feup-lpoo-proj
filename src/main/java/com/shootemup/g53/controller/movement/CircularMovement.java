package com.shootemup.g53.controller.movement;

import com.shootemup.g53.model.util.Position;

public class CircularMovement  extends IncrementalMovement {
    protected final double radius;
    protected double angle;
    protected double angularSpeed;

    public CircularMovement(double radius, double initAngle, double angularSpeed) {
        this.radius = radius;
        this.angle = Math.toRadians(initAngle);
        this.angularSpeed = Math.toRadians(angularSpeed);
    }

    @Override
    Position moveFrame(Position position, int speed) {
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

    public double getRadius() {
        return radius;
    }

    public double getAngle() {
        return angle;
    }

    public double getAngularSpeed() {
        return angularSpeed;
    }

    @Override
    public MovementStrategy cloneStrategy() {
        return new CircularMovement(radius, angle, Math.toDegrees(angularSpeed));
    }
}
