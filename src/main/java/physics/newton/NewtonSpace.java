package physics.newton;

import physics.vector.VectorSpace;

import java.util.LinkedList;
import java.util.List;

public class NewtonSpace {

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
        throw new UnsupportedOperationException();
    }
}
