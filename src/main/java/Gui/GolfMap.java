package Gui;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import Math.PhysicsEngine;
public class GolfMap extends Application implements Runnable
{

    private static final float WIDTH = 800;
    private static final float HEIGHT = 500;
    private static final float FRAME_WIDTH = 800;
    private static final float FRAME_HEIGHT = 500;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public static double coordinatex = 5;
    public static double coordinatey = 5;
    public static double upperBoundx = 5;
    public static double upperBoundy = 10;
    public static double lowerBoundx = -5;
    public static double lowerBoundy = -10;

    public static Scene scene;
    public static Scene scene2;
    public static Stage stage;
    public static GridPane gridPane;
    public static double function(double x, double y){
        return 0.4*(0.9 - Math.exp( - (x * x + y * y) / 8.0) );
    }
    SmartGroup group;
    @Override

    public void start(Stage primaryStage) throws Exception {
        lowerBoundx = Math.min(PhysicsEngine.x0, PhysicsEngine.xt)-5;
        upperBoundx = Math.max(PhysicsEngine.x0, PhysicsEngine.xt)+5;
        lowerBoundy = Math.min(PhysicsEngine.y0, PhysicsEngine.yt)-5;
        upperBoundy = Math.max(PhysicsEngine.y0, PhysicsEngine.yt)+5;
        Pane displayPane = new Pane();
        displayPane.setPrefSize(FRAME_WIDTH, FRAME_HEIGHT);

        StackPane root = new StackPane();

        group = new SmartGroup();
        SmartGroup group2 = new SmartGroup();
        this.gridPane = new GridPane();
        this.gridPane.setTranslateX(FRAME_WIDTH - 200);
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));;
        displayPane.getChildren().add(this.gridPane);


        PhongMaterial blue = new PhongMaterial();
        blue.setDiffuseColor(Color.rgb(74, 157, 202));//61, 138, 179

        PhongMaterial lightsaberGreen = new PhongMaterial();        //https://colorcodes.io/green/
        lightsaberGreen.setDiffuseColor(Color.rgb(94, 183, 49));

        PhongMaterial irishGreen = new PhongMaterial();
        irishGreen.setDiffuseColor(Color.rgb(45, 135, 0));

        PhongMaterial christmasGreen = new PhongMaterial();
        christmasGreen.setDiffuseColor(Color.rgb(14, 94, 16));

        PhongMaterial aquaGreen = new PhongMaterial();
        aquaGreen.setDiffuseColor(Color.rgb(69, 163, 22));

        double maxHeight = -99999999;
        double minHeight = 999999999;

        for (double i = lowerBoundx; i < upperBoundx; i = i + 0.1) {
            for (double j = lowerBoundy; j < upperBoundy; j = j + 0.1) {
                maxHeight = Math.max(maxHeight, function(i, j));
                minHeight = Math.min(minHeight, function(i, j));
            }
        }

        double heightDiff = Math.abs(maxHeight - minHeight);

        for (double i = lowerBoundx; i < upperBoundx; i = i + 0.1) {
            for (double j = lowerBoundy; j < upperBoundy; j = j + 0.1) {

                Box box = new Box(0.1, 0.1, 0.1);
                box.translateXProperty().set(i);
                box.translateYProperty().set(j);
                box.translateZProperty().set(function(i, j));

                if (box.getTranslateZ() <= 0) {
                    box.setMaterial(blue);

                } else if (box.getTranslateZ() > 0 && box.getTranslateZ() <= heightDiff / 4) {
                    box.setMaterial(christmasGreen);
                } else if (box.getTranslateZ() > heightDiff / 4 && box.getTranslateZ() <= 2 * heightDiff / 4) {
                    box.setMaterial(irishGreen);
                } else if (box.getTranslateZ() > 2 * heightDiff / 4 && box.getTranslateZ() <= 3 * heightDiff / 4) {
                    box.setMaterial(aquaGreen);
                } else {
                    box.setMaterial(lightsaberGreen);
                }

                group.getChildren().add(box);
            }

        }
//        Sphere sphere = new Sphere(0.1, 1);
        GolfBall.ball.setRadius(0.4);
        GolfBall.ball.translateXProperty().set(PhysicsEngine.x0);
        GolfBall.ball.translateYProperty().set(PhysicsEngine.y0);
        GolfBall.ball.translateZProperty().set(function(PhysicsEngine.x0, PhysicsEngine.y0) + 0.45);
        group.getChildren().add(GolfBall.ball);
        //group.getChildren().add(button);
        //group.getChildren().add(prepareImageView());

//        Cylinder treetrunk = new Cylinder(2, 15);
//        treetrunk.translateXProperty().set(0);
//        treetrunk.translateYProperty().set(0);
//        treetrunk.translateZProperty().set(0.1);
//        group2.getChildren().add(treetrunk);

        Camera camera = new PerspectiveCamera();
        camera.setNearClip(0.000001);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-905);

//        group2.translateXProperty().set(70);
//        group2.translateYProperty().set(70);
//        group2.translateZProperty().set(-800);

        Cylinder trunky = new Cylinder(0.2, 3);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.rgb(105, 52, 23));
        trunky.setMaterial(material);
        trunky.setTranslateX(4);
        trunky.setTranslateY(3);
        trunky.setTranslateZ(1.8);
        trunky.setRotationAxis(Rotate.X_AXIS);
        trunky.setRotate(90);
        group.getChildren().add(trunky);

        Sphere foliage = new Sphere();
        PhongMaterial material2 = new PhongMaterial();
        material2.setDiffuseColor(Color.rgb(94, 183, 49));
        foliage.setMaterial(material2);
        foliage.setRadius(1);
        foliage.setTranslateX(4);
        foliage.setTranslateY(3);
        foliage.setTranslateZ(3);
        group.getChildren().add(foliage);

        Cylinder trunky2 = new Cylinder(0.2, 3);
        trunky2.setMaterial(material);
        trunky2.setTranslateX(-4);
        trunky2.setTranslateY(4.5);
        trunky2.setTranslateZ(1.8);
        trunky2.setRotationAxis(Rotate.X_AXIS);
        trunky2.setRotate(90);
        group.getChildren().add(trunky2);

        Sphere foliage2 = new Sphere();
        foliage2.setMaterial(material2);
        foliage2.setRadius(1);
        foliage2.setTranslateX(-4);
        foliage2.setTranslateY(4.5);
        foliage2.setTranslateZ(3);
        group.getChildren().add(foliage2);

        Cylinder trunky3 = new Cylinder(0.2, 3);
        trunky3.setMaterial(material);
        trunky3.setTranslateX(4.5);
        trunky3.setTranslateY(-3);
        trunky3.setTranslateZ(1.8);
        trunky3.setRotationAxis(Rotate.X_AXIS);
        trunky3.setRotate(90);
        group.getChildren().add(trunky3);

        Sphere foliage3 = new Sphere();
        foliage3.setMaterial(material2);
        foliage3.setRadius(1);
        foliage3.setTranslateX(4.5);
        foliage3.setTranslateY(-3);
        foliage3.setTranslateZ(3);
        group.getChildren().add(foliage3);

        Cylinder flagstock = new Cylinder(0.05, 2);
        PhongMaterial material3 = new PhongMaterial();
        material3.setDiffuseColor(Color.rgb(0, 0, 0));
        flagstock.setMaterial(material3);
        flagstock.setTranslateX(PhysicsEngine.xt);
        flagstock.setTranslateY(PhysicsEngine.yt);
        flagstock.setTranslateZ(1.3);
        flagstock.setRotationAxis(Rotate.X_AXIS);
        flagstock.setRotate(90);
        group.getChildren().add(flagstock);

        Box flag = new Box();
        flag.setTranslateX(PhysicsEngine.xt);
        flag.setTranslateY(PhysicsEngine.yt);
        flag.setTranslateZ(2);
        flag.setHeight(0.6);
        flag.setWidth(0.1);
        flag.setDepth(1);
        PhongMaterial material4 = new PhongMaterial();
        material4.setDiffuseColor(Color.RED);
        flag.setMaterial(material4);
        Rotate xRotation = new Rotate(90, Rotate.X_AXIS);
        Rotate yRotation = new Rotate(90, Rotate.Y_AXIS);
        flag.getTransforms().addAll(xRotation, yRotation);
        group.getChildren().add(flag);

        Font font = new Font(25);




        SubScene subScene = new SubScene(group,FRAME_WIDTH - 200, FRAME_HEIGHT, true, null);
        displayPane.getChildren().add(subScene);
        scene = new Scene(group, WIDTH, HEIGHT, true);
        //scene.setFill(Color.SILVER);
        scene.setCamera(camera);

//        rc = new Scene(group, WIDTH, HEIGHT, true);
//        rc.setFill(Color.SILVER);




        initMouseControl(group, scene, primaryStage);



        primaryStage.setTitle("Big Golf");
        //primaryStage.setScene(buttonscene);
        primaryStage.setScene(scene);
//        Scene scene2 = new Scene(pane, 200, 100);
//        primaryStage.setScene(scene2);
        primaryStage.show();

    }



    private Slider prepareSlider(){
        Slider slider = new Slider();
        slider.setMax(800);
        slider.setMin(-400);
        slider.setPrefWidth(300);
        slider.setLayoutX(-150);
        slider.setLayoutY(200);
        slider.setShowTickLabels(true);
        slider.setTranslateZ(5);
        slider.setStyle("-fx-base: black");
        return slider;

    }

    private void initMouseControl(Group group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS));
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double movement = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - movement);
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void run() {
        try {
            start(RemoteControl.newStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class SmartGroup extends Group {

        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);

        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);

        }
    }
}
