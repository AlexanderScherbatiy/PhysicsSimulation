package physics.vector.memory;

import org.junit.Assert;
import org.junit.Test;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;

import static physics.vector.memory.TestConstants.DELTA;

public class MemoryVectorSpace3DGetValueTest {


    @Test
    public void testGetValue() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        VectorFactory factory = vectorSpace.getFactory();
        VectorOperations ops = vectorSpace.getOperations();

        Vector vector = factory.createVector3D(1, 2, 3);
        Assert.assertEquals(1, ops.getValue(0, vector), DELTA);
        Assert.assertEquals(2, ops.getValue(1, vector), DELTA);
        Assert.assertEquals(3, ops.getValue(2, vector), DELTA);
    }
}
