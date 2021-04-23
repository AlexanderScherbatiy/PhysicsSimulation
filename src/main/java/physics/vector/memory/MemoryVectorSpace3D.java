package physics.vector.memory;

import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorOperations;
import physics.vector.VectorSpace;

public class MemoryVectorSpace3D implements VectorSpace {

    private final VectorFactory factory = new MemoryVectorFactory3D();
    private final VectorOperations operations = new MemoryVectorOperations3D();

    @Override
    public VectorFactory getFactory() {
        return factory;
    }

    @Override
    public VectorOperations getOperations() {
        return operations;
    }

    private static class MemoryVector3D implements Vector {

        private double x;
        private double y;
        private double z;

        public MemoryVector3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class MemoryVectorFactory3D implements VectorFactory {

        @Override
        public Vector createVector3D(double x, double y, double z) {
            return new MemoryVector3D(x, y, z);
        }
    }

    private static class MemoryVectorOperations3D implements VectorOperations {

        @Override
        public double getValue(int index, Vector vector) {
            MemoryVector3D memoryVector = getMemoryVector(vector);
            switch (index) {
                case 0:
                    return memoryVector.x;
                case 1:
                    return memoryVector.y;
                case 2:
                    return memoryVector.z;
                default:
                    throw new IndexOutOfBoundsException("Get index from 3D vector: " + index);
            }
        }

        private static MemoryVector3D getMemoryVector(Vector vector) {
            if (vector instanceof MemoryVector3D) {
                return (MemoryVector3D) vector;
            }
            throw new RuntimeException("Vector is not a memory vector: " + vector);
        }
    }
}
