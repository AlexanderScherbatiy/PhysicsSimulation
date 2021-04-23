package physics.vector.memory;

import org.junit.Assert;
import org.junit.Test;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;

import static physics.vector.memory.TestConstants.DELTA;

public class MemoryVectorSpace3DUpdateAddTest {

    @Test
    public void testUpdateAdd() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();


        Vector vector2 = factory.createVector3D(1, 2, 3);
        Vector vector1 = factory.createVector3D(4, 5, 6);
        Vector golden = factory.createVector3D(5, 7, 9);
        ops.updateAdd(vector2, vector1);

        Assert.assertTrue(ops.equals(golden, vector2, DELTA));
    }
}
