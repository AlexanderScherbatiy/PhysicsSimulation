package physics.vector;

public interface VectorOperations {

    double getValue(int index, Vector vector);

    Vector subtract(Vector vector2, Vector vector1);

    Vector multiply(double a, Vector vector);

    boolean equals(Vector vector2, Vector vector1, double delta);

    double length(Vector vector);

    void updateAdd(Vector accumulator, Vector vector);
}
