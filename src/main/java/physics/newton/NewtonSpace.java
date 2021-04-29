package physics.newton;

import physics.vector.Vector;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;
import physics.vector.VariableVector;

import java.util.LinkedList;
import java.util.List;

public class NewtonSpace {

    private static final double G = 6.67e-11;
    private static final double DELTA_T = 200;

    private VectorSpace vectorSpace;
    private List<NewtonSpaceBody> bodies = new LinkedList<>();

    public NewtonSpace(VectorSpace vectorSpace) {
        this.vectorSpace = vectorSpace;
    }

    public VectorSpace getVectorSpace() {
        return vectorSpace;
    }

    public void addBody(NewtonSpaceBody body) {
        bodies.add(body);
    }

    public List<NewtonSpaceBody> getBodies() {
        return bodies;
    }

    public void evaluate() {
        VectorOperations ops = vectorSpace.getOperations();
        for (NewtonSpaceBody body1 : bodies) {
            for (NewtonSpaceBody body2 : bodies) {
                if (body1 == body2) {
                    continue;
                }

                Vector deltaR = ops.subtract(body2.getCoordinates(), body1.getCoordinates());
                double length = ops.length(deltaR);

                VariableVector velocity = body2.getVelocity();
                Vector acceleration = ops.multiply(-G * body1.getMass() / (length * length * length), deltaR);
                Vector dv = ops.multiply(DELTA_T, acceleration);
                ops.updateAdd(velocity, dv);

                VariableVector coordinates = body2.getCoordinates();
                Vector dr = ops.multiply(DELTA_T, velocity);
                ops.updateAdd(coordinates, dr);
            }
        }
    }
}
