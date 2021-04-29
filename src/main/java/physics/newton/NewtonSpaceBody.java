package physics.newton;

import physics.vector.VariableVector;

import java.util.HashMap;
import java.util.Map;

public class NewtonSpaceBody {

    private double mass;
    private double radius;
    private VariableVector coordinates;
    private VariableVector velocity;
    private Map<String, Object> properties;

    public NewtonSpaceBody(double mass, double radius, VariableVector coordinates, VariableVector velocity) {
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

    public VariableVector getCoordinates() {
        return coordinates;
    }

    public VariableVector getVelocity() {
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
