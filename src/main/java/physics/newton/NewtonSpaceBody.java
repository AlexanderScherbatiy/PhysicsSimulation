package physics.newton;

import physics.vector.Vector;

public class NewtonSpaceBody {

    private double mass;
    private double radius;
    private Vector coordinates;
    private Vector velocity;

    public NewtonSpaceBody(double mass, double radius, Vector coordinates, Vector velocity) {
        this.mass = mass;
        this.radius = radius;
        this.coordinates = coordinates;
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getCoordinates() {
        return coordinates;
    }

    public Vector getVelocity() {
        return velocity;
    }
}
