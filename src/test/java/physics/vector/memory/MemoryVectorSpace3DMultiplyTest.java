package physics.vector.memory;

import org.junit.Assert;
import org.junit.Test;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;

import static physics.vector.memory.TestConstants.DELTA;

public class MemoryVectorSpace3DMultiplyTest {

    @Test
    public void testMultiply() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();

        Vector vector = factory.createVector3D(1, 2, 3);
        Vector golden = factory.createVector3D(2, 4, 6);
        Vector result = ops.multiply(2, vector);

        Assert.assertTrue(ops.equals(golden, result, DELTA));
    }
}
