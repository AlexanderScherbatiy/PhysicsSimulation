package physics.vector.memory;

import org.junit.Assert;
import org.junit.Test;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;
import static physics.vector.memory.TestConstants.DELTA;

public class MemoryVectorSpace3DLengthTest {

    @Test
    public void testLength() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();

        Vector vector = factory.createVector3D(1, 2, 3);
        Assert.assertEquals(Math.sqrt(14), ops.length(vector), DELTA);
    }
}
