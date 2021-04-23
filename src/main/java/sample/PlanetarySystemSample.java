package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import physics.newton.NewtonSpace;
import physics.newton.NewtonSpaceBody;
import physics.vector.memory.MemoryVectorSpace3D;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorSpace;

import java.util.List;
import java.util.stream.Collectors;

public class PlanetarySystemSample extends Application {

    private static final int STAGE_WIDTH = 800;
    private static final int STAGE_HEIGHT = 600;

    private static final double RADIUS_SCALE = 0.1;
    private static final double POSITION_SCALE = 1;

    @Override
    public void start(Stage stage) {

        NewtonSpace space = createSpace();

        List<Node> nodes = createShapes(space);

        Group root = new Group(nodes);

        double tx = STAGE_WIDTH / 2;
        double ty = STAGE_HEIGHT / 2;

        root.getTransforms().add(Transform.translate(tx, ty));

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Newton Space");
        stage.setScene(scene);
        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.show();
    }

    private static NewtonSpace createSpace() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        NewtonSpace space = new NewtonSpace(vectorSpace);

        VectorFactory vectorFactory = vectorSpace.getFactory();
        double R = 100;
        Vector position1 = vectorFactory.createVector3D(R, 0, 0);
        Vector position2 = vectorFactory.createVector3D(-R, 0, 0);

        double velocity = 200;
        Vector velocity1 = vectorFactory.createVector3D(0, velocity, 0);
        Vector velocity2 = vectorFactory.createVector3D(0, -velocity, 0);

        double mass = 100;
        double radius = 100;

        NewtonSpaceBody body1 = new NewtonSpaceBody(mass, radius, position1, velocity1);
        NewtonSpaceBody body2 = new NewtonSpaceBody(mass, radius, position2, velocity2);

        space.addBody(body1);
        space.addBody(body2);

        return space;
    }

    List<Node> createShapes(NewtonSpace space) {

        return space.getBodies().stream().map(body -> {

            final VectorSpace vectorSpace = space.getVectorSpace();
            Vector position = body.getCoordinates();
            double x = vectorSpace.getOperations().getValue(0, position);
            double y = vectorSpace.getOperations().getValue(1, position);

            Circle circle = new Circle();
            circle.setRadius(body.getRadius() * RADIUS_SCALE);
            circle.setCenterX(x * POSITION_SCALE);
            circle.setCenterY(y * POSITION_SCALE);
            circle.setFill(x > 0 ? Color.GREEN : Color.ORANGE);
            return circle;

        }).collect(Collectors.toList());
    }

    public static void main(String args[]) {
        launch(args);
    }
}
