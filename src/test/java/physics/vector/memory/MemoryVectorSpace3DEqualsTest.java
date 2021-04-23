package physics.vector.memory;

import org.junit.Assert;
import org.junit.Test;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;

public class MemoryVectorSpace3DEqualsTest {

    @Test
    public void testEqualsTrue() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();

        double delta = 0.01;

        Vector vector2 = factory.createVector3D(1, 2, 3);
        Vector vector1 = factory.createVector3D(1, 2, 3);

        Assert.assertTrue(ops.equals(vector2, vector1, delta));
    }

    @Test
    public void testEqualsFalse() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();

        double delta = 0.01;

        Vector v = factory.createVector3D(1, 2, 3);

        Assert.assertFalse(ops.equals(v, factory.createVector3D(1.1, 2, 3), delta));
        Assert.assertFalse(ops.equals(v, factory.createVector3D(1, 2.1, 3), delta));
        Assert.assertFalse(ops.equals(v, factory.createVector3D(1, 2, 3.1), delta));

        Assert.assertFalse(ops.equals(v, factory.createVector3D(0.9, 2, 3), delta));
        Assert.assertFalse(ops.equals(v, factory.createVector3D(1, 1.9, 3), delta));
        Assert.assertFalse(ops.equals(v, factory.createVector3D(1, 2, 2.9), delta));
    }
}
