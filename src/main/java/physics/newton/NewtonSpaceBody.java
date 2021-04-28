package physics.newton;

import java.util.Map;
import java.util.HashMap;

import physics.vector.Vector;

public class NewtonSpaceBody {

    private double mass;
    private double radius;
    private Vector coordinates;
    private Vector velocity;
    private Map<String, Object> properties;

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

    public void putProperty(String key, Object value) {

        if (properties == null) {
            properties = new HashMap<>();
        }

        properties.put(key, value);
    }

    public Object getProperty(String key) {
        return properties == null ? null : properties.get(key);
    }

}
