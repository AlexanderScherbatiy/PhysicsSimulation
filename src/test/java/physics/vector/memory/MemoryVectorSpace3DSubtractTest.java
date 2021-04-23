package physics.vector.memory;

import org.junit.Assert;
import org.junit.Test;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;

import static physics.vector.memory.TestConstants.DELTA;

public class MemoryVectorSpace3DSubtractTest {

    @Test
    public void testSubtract() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();


        Vector vector2 = factory.createVector3D(10, 9, 8);
        Vector vector1 = factory.createVector3D(1, 2, 3);
        Vector golden = factory.createVector3D(9, 7, 5);
        Vector result = ops.subtract(vector2, vector1);

        Assert.assertTrue(ops.equals(golden, result, DELTA));
    }
}
