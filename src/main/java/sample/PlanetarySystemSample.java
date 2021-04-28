package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import physics.newton.NewtonSpace;
import physics.newton.NewtonSpaceBody;
import physics.vector.memory.MemoryVectorSpace3D;
import physics.vector.Vector;
import physics.vector.VectorFactory;
import physics.vector.VectorSpace;

import java.util.List;
import java.util.stream.Collectors;

public class PlanetarySystemSample extends Application {

    private static final int STAGE_WIDTH = 1000;
    private static final int STAGE_HEIGHT = 800;

    private static final double POSITION_SCALE = 3e-9;

    private static final String PROP_RADIUS_SCALE = "planetary_system.radius.scale";

    @Override
    public void start(Stage stage) {

        NewtonSpace space = createSpace();

        List<BodyShape> shapes = createShapes(space);
        List<Node> nodes = shapes.stream().map(shape -> shape.circle).collect(Collectors.toList());
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

        runAnimation(space, shapes);
    }

    private static NewtonSpace createSpace() {

        VectorSpace vectorSpace = new MemoryVectorSpace3D();
        NewtonSpace space = new NewtonSpace(vectorSpace);

        VectorFactory vectorFactory = vectorSpace.getFactory();
        Vector sunPosition = vectorFactory.createVector3D(0, 0, 0);

        double R = 150e9;
        Vector earthPosition = vectorFactory.createVector3D(R, 0, 0);

        Vector sunVelocity = vectorFactory.createVector3D(0, 0, 0);

        double velocity = 30e3;
        Vector earthVelocity = vectorFactory.createVector3D(0, velocity, 0);

        double sunMass = 2e30;
        double earthMass = 6e24;

        double sunRadius = 696e6;
        double earthRadius = 6.3e6;

        NewtonSpaceBody sun = new NewtonSpaceBody(sunMass, sunRadius, sunPosition, sunVelocity);
        NewtonSpaceBody earth = new NewtonSpaceBody(earthMass, earthRadius, earthPosition, earthVelocity);

        sun.putProperty(PROP_RADIUS_SCALE, 30.0 * POSITION_SCALE);
        earth.putProperty(PROP_RADIUS_SCALE, 300.0 * POSITION_SCALE);

        space.addBody(sun);
        space.addBody(earth);

        return space;
    }

    private static void runAnimation(NewtonSpace space, List<BodyShape> shapes) {

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame evaluationFrame = new KeyFrame(Duration.seconds(0.0001), (event) -> {
            space.evaluate();
            shapes.stream().forEach(shape -> {
                shape.update();
            });
        });

        timeline.getKeyFrames().add(evaluationFrame);
        timeline.play();
    }

    List<BodyShape> createShapes(NewtonSpace space) {

        return space
                .getBodies()
                .stream()
                .map(body -> new BodyShape(space, body))
                .collect(Collectors.toList());
    }

    public static void main(String args[]) {
        launch(args);
    }

    private static class BodyShape {

        private final Circle circle;
        private final NewtonSpace space;
        private final NewtonSpaceBody body;

        public BodyShape(NewtonSpace space, NewtonSpaceBody body) {
            this.space = space;
            this.body = body;
            this.circle = new Circle();
            double radiusScale = (double) body.getProperty(PROP_RADIUS_SCALE);
            circle.setRadius(body.getRadius() * radiusScale);
            circle.setFill(Color.ORANGE);
        }

        public void update() {
            final VectorSpace vectorSpace = space.getVectorSpace();
            Vector position = body.getCoordinates();
            double x = vectorSpace.getOperations().getValue(0, position);
            double y = vectorSpace.getOperations().getValue(1, position);
            circle.setCenterX(x * POSITION_SCALE);
            circle.setCenterY(y * POSITION_SCALE);
        }
    }
}
