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

        @Override
        public String toString() {
            return String.format("MemoryVector3D(%f, %f, %f)", x, y, z);
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
        public Vector subtract(Vector v2, Vector v1) {
            MemoryVector3D m2 = castVector(v2);
            MemoryVector3D m1 = castVector(v1);
            return new MemoryVector3D(m2.x - m1.x, m2.y - m1.y, m2.z - m1.z);
        }

        @Override
        public Vector multiply(double a, Vector vector) {
            MemoryVector3D m = castVector(vector);
            return new MemoryVector3D(a * m.x, a * m.y, a * m.z);
        }

        @Override
        public double length(Vector vector) {
            MemoryVector3D m = castVector(vector);
            return Math.sqrt(m.x * m.x + m.y * m.y + m.z * m.z);
        }

        @Override
        public void updateAdd(Vector vector2, Vector vector1) {
            MemoryVector3D m2 = castVector(vector2);
            MemoryVector3D m1 = castVector(vector1);
            m2.x += m1.x;
            m2.y += m1.y;
            m2.z += m1.z;
        }

        @Override
        public boolean equals(Vector vector2, Vector vector1, double delta) {
            MemoryVector3D m1 = castVector(vector2);
            MemoryVector3D m2 = castVector(vector1);

            if (Math.abs(m2.x - m1.x) > delta) {
                return false;
            }

            if (Math.abs(m2.y - m1.y) > delta) {
                return false;
            }

            if (Math.abs(m2.z - m1.z) > delta) {
                return false;
            }

            return true;
        }

        @Override
        public double getValue(int index, Vector vector) {
            MemoryVector3D memoryVector = castVector(vector);
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

        private static MemoryVector3D castVector(Vector vector) {
            return (MemoryVector3D) vector;
        }
    }
}
