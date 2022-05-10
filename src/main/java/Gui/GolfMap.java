package Gui;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class GolfMap extends Application implements Runnable{

    private static final float WIDTH = 800;
    private static final float HEIGHT = 500;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public static Scene scene;
    public static Stage stage;

    public double function(double x, double y){
        return 0.4*(0.9 - Math.exp( - (x * x + y * y) / 8.0) );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SmartGroup group = new SmartGroup();

        Group root = new Group(GolfBall.ball);

        Scene ballik = new Scene (root, 300, 100);

        primaryStage.setScene(ballik);

        PhongMaterial blue = new PhongMaterial();
        blue.setDiffuseColor(Color.BLUE);

        PhongMaterial lightsaberGreen = new PhongMaterial();        //https://colorcodes.io/green/
        lightsaberGreen.setDiffuseColor(Color.rgb(47, 180, 36));

        PhongMaterial irishGreen = new PhongMaterial();
        irishGreen.setDiffuseColor(Color.rgb(0, 154, 68));

        PhongMaterial christmasGreen = new PhongMaterial();
        christmasGreen.setDiffuseColor(Color.rgb(0, 135, 62));

        PhongMaterial aquaGreen = new PhongMaterial();
        aquaGreen.setDiffuseColor(Color.rgb(0, 154, 23));

        double maxHeight = -99999999;
        double minHeight = 999999999;
        double multiplier = 10;
        for (double i = -5; i < 5; i=i+0.1) {
            for (double j = -5; j < 5; j= j+0.1) {
                maxHeight = Math.max(maxHeight, function(i, j));
                minHeight = Math.min(minHeight, function(i, j));
            }
        }

        double heightDiff = Math.abs(maxHeight - minHeight);

        for (double i = -5; i < 5; i=i+0.1) {
            for (double j = -5; j < 5; j= j+0.1) {

                Box box = new Box(0.1, 0.1, 0.1);
                box.translateXProperty().set(i);
                box.translateYProperty().set(j);
                box.translateZProperty().set(function(i, j)*multiplier);

                if(box.getTranslateZ() <= 0){
                    box.setMaterial(blue);

                }else if(box.getTranslateZ() > 0 && box.getTranslateZ() <= heightDiff/4){
                    box.setMaterial(christmasGreen);
                }
                else if(box.getTranslateZ() > heightDiff/4 && box.getTranslateZ() <= 2*heightDiff/4){
                    box.setMaterial(irishGreen);
                }
                else if(box.getTranslateZ() > 2*heightDiff/4 && box.getTranslateZ() <= 3*heightDiff/4){
                    box.setMaterial(aquaGreen);
                }
                else{
                    box.setMaterial(lightsaberGreen);
                }

                group.getChildren().add(box);
            }

        }
//        Sphere sphere = new Sphere(0.1, 1);
          GolfBall.ball.setRadius(0.4);
          GolfBall.ball.translateXProperty().set(5);
          GolfBall.ball.translateYProperty().set(5);
          GolfBall.ball.translateZProperty().set(function(5, 5)+0.45);
          group.getChildren().add(GolfBall.ball);


        Camera camera = new PerspectiveCamera();
        camera.setNearClip(0.000001);

        scene = new Scene(group, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-600);

        initMouseControl(group, scene, primaryStage);

        primaryStage.setTitle("Big Golf");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private ImageView prepareImageView()
    {
        Image flag = new Image(GolfMap.class.getResourceAsStream("/Users/viktorvantsev/um_project/src/main/resources/bandeira-red-flag"));
        ImageView imageView = new ImageView(flag);
        return imageView;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void run() {
        launch();
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
