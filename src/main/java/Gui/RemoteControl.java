package Gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import Math.Main;
import static Gui.GolfMap.coordinatex;
import static Gui.GolfMap.coordinatey;


public class RemoteControl extends Application
{

    public Label label = new Label("Choose the Way:");
    public RadioButton r1 = new RadioButton("ME        ");
    public RadioButton r2 = new RadioButton("BOT");
    public static Stage newStage;
    GolfMap m;

    @Override
    public void start(final Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Big Golf Remote Control (B. G. R. C.)");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 800);
        scene.setFill(Color.rgb(127, 126, 124));

        //BUTTON OPEN
        Button open = new Button();
        Font font = new Font(25);
        open.setLayoutX(100);
        open.setLayoutY(15);
        open.setMinWidth(200);
        open.setMinHeight(50);
        open.setStyle("-fx-background-color: #57BA39; ");
        open.setFont(font);
        open.setText("Open the Field");
        open.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                newStage = new Stage();
                newStage.setTitle("Big Golf Field (B. G. F.");
                Group fieldroot = new Group();
                Scene fieldscene = new Scene(fieldroot, 800, 600);
                fieldscene.setFill(Color.DARKGREEN);
                System.out.println("test");
                m = new GolfMap();
                try {
                    m.start(newStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                newStage.show();
            }
        });
        root.getChildren().add(open);

        //BUTTON HIT
        Button hit = new Button();
        hit.setLayoutX(100);
        hit.setLayoutY(77);
        hit.setMinWidth(200);
        hit.setMinHeight(50);
        hit.setFont(font);
        hit.setStyle("-fx-background-color: #FFD00E; ");
        hit.setText("Hit the Ball");
        hit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                boolean switchdd = true;
                Main runMain = new Main();
                Thread n = new Thread(runMain);
                n.start();

                primaryStage.show();

            }
        });
        root.getChildren().add(hit);

        //BUTTON STOP
        Button stop = new Button();
        stop.setLayoutX(100);
        stop.setLayoutY(140);
        stop.setMinWidth(200);
        stop.setMinHeight(50);
        stop.setFont(font);
        stop.setStyle("-fx-background-color: #FF891D; ");
        //stop.setStyle("-fx-font-weight: bold; ");
        stop.setText("Stop the Game");
        stop.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        root.getChildren().add(stop);

        //LABEL CHOICE 1
        label.setFont(font);
        label.setTextFill(Color.BLACK);
        label.setLayoutX(110);
        label.setLayoutY(245);
        root.getChildren().add(label);

//        //RADIO BUTTONS 1
//        TilePane r = new TilePane();
//        r1.setTextFill(Color.WHITE);
//        r2.setTextFill(Color.WHITE);
//        r.setLayoutX(340);
//        r.setLayoutY(280);
//        r.getChildren().add(r1);
//        r.getChildren().add(r2);
//        root.getChildren().add(r);

        InputStream is = new FileInputStream("src/main/resources/ar.png");
        Image image = new Image(is);
        InputStream is2 = new FileInputStream("src/main/resources/ar2.png");
        Image image2 = new Image(is2);
        InputStream is3 = new FileInputStream("src/main/resources/ar3.png");
        Image image3 = new Image(is3);
        InputStream is4 = new FileInputStream("src/main/resources/ar4.png");
        Image image4 = new Image(is4);
        InputStream is5 = new FileInputStream("src/main/resources/ar5.png");
        Image image5 = new Image(is5);
        InputStream is6 = new FileInputStream("src/main/resources/ar6.png");
        Image image6 = new Image(is6);
        InputStream is7 = new FileInputStream("src/main/resources/ar7.png");
        Image image7 = new Image(is7);
        InputStream is8 = new FileInputStream("src/main/resources/ar8.png");
        Image image8 = new Image(is8);


        //RADIO BUTTONS 1
        ToggleGroup r = new ToggleGroup();
        r1.setTextFill(Color.BLACK);
        r2.setTextFill(Color.BLACK);
        r1.setToggleGroup(r);
        r2.setToggleGroup(r);
        r1.setLayoutX(140);
        r1.setLayoutY(290);
        r2.setLayoutX(200);
        r2.setLayoutY(290);
        r1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    // ...
                } else {
                    // ...
                }
            }
        });
        root.getChildren().addAll(r1, r2);

        Font font2 = new Font(20);

        //LABEL X
        Label labelx = new Label("X:");
        labelx.setFont(font2);
        labelx.setTextFill(Color.BLACK);
        labelx.setLayoutX(80);
        labelx.setLayoutY(210);
        root.getChildren().add(labelx);

        //LABEL X COORDINATE
        Label xcoor = new Label(String.valueOf(GolfBall.X)); //
        xcoor.setFont(font2);
        xcoor.setTextFill(Color.BLACK);
        xcoor.setLayoutX(110);
        xcoor.setLayoutY(210);
        root.getChildren().add(xcoor);

        //LABEL X
        Label labely = new Label("Y:");
        labely.setFont(font2);
        labely.setTextFill(Color.BLACK);
        labely.setLayoutX(170);
        labely.setLayoutY(210);
        root.getChildren().add(labely);

        //LABEL X COORDINATE
        Label ycoor = new Label(String.valueOf(GolfBall.Y)); //
        ycoor.setFont(font2);
        ycoor.setTextFill(Color.BLACK);
        ycoor.setLayoutX(200);
        ycoor.setLayoutY(210);
        root.getChildren().add(ycoor);

        //LABEL Z
        Label labelz = new Label("Z:");
        labelz.setFont(font2);
        labelz.setTextFill(Color.BLACK);
        labelz.setLayoutX(260);
        labelz.setLayoutY(210);
        root.getChildren().add(labelz);

        //LABEL X COORDINATE
        Label zcoor = new Label(String.valueOf(GolfBall.Z)); //
        zcoor.setFont(font2);
        zcoor.setTextFill(Color.BLACK);
        zcoor.setLayoutX(290);
        zcoor.setLayoutY(210);
        root.getChildren().add(zcoor);


        //LABEL CHOICE 2
        Label label2 = new Label("Choose the Method:");
        label2.setFont(font);
        label2.setTextFill(Color.BLACK);
        label2.setLayoutX(95);
        label2.setLayoutY(320);
        root.getChildren().add(label2);

        //RADIO BUTTONS 2
//        TilePane rr = new TilePane();
//        RadioButton rr1 = new RadioButton("EULER");
//        RadioButton rr2 = new RadioButton("SI EULER");
//        RadioButton rr3 = new RadioButton("RUNGEKUTTA  ");
//        RadioButton rr4 = new RadioButton("RUNGEKUTTA4  ");
//        rr1.setTextFill(Color.WHITE);
//        rr2.setTextFill(Color.WHITE);
//        rr3.setTextFill(Color.WHITE);
//        rr4.setTextFill(Color.WHITE);
//        rr.setLayoutX(150);
//        rr.setLayoutY(370);
//        rr.getChildren().add(rr1);
//        rr.getChildren().add(rr2);
//        rr.getChildren().add(rr3);
//        rr.getChildren().add(rr4);
//        root.getChildren().add(rr);

        ToggleGroup rr = new ToggleGroup();
        RadioButton rr1 = new RadioButton("EULER");
        RadioButton rr2 = new RadioButton("SI EULER");
        RadioButton rr3 = new RadioButton("RUNGEKUTTA  ");
        RadioButton rr4 = new RadioButton("RUNGEKUTTA4  ");
        rr1.setTextFill(Color.BLACK);
        rr2.setTextFill(Color.BLACK);
        rr3.setTextFill(Color.BLACK);
        rr4.setTextFill(Color.BLACK);
        rr1.setLayoutX(110);
        rr1.setLayoutY(370);
        rr2.setLayoutX(110);
        rr2.setLayoutY(400);
        rr3.setLayoutX(190);
        rr3.setLayoutY(370);
        rr4.setLayoutX(190);
        rr4.setLayoutY(400);
        rr1.setToggleGroup(rr);
        rr2.setToggleGroup(rr);
        rr3.setToggleGroup(rr);
        rr4.setToggleGroup(rr);
        root.getChildren().add(rr1);
        root.getChildren().add(rr2);
        root.getChildren().add(rr3);
        root.getChildren().add(rr4);



        //LABEL CHOICE 3
        Label label3 = new Label("Choose the Direction:");
        label3.setFont(font);
        label3.setTextFill(Color.BLACK);
        label3.setLayoutX(92);
        label3.setLayoutY(435);
        root.getChildren().add(label3);

        //RADIO BUTTONS 3
        ToggleGroup rrr = new ToggleGroup();
        RadioButton rrr1 = new RadioButton("    NORD        ");
        RadioButton rrr2 = new RadioButton("  NORDOST        ");
        RadioButton rrr3 = new RadioButton("OST        ");
        RadioButton rrr4 = new RadioButton("  ZUIDOST       ");
        RadioButton rrr5 = new RadioButton("    ZUID       ");
        RadioButton rrr6 = new RadioButton("ZUIDWEST        ");
        RadioButton rrr7 = new RadioButton("WEST       ");
        RadioButton rrr8 = new RadioButton("    NORDWEST        ");

        rrr1.setGraphic(new ImageView(image));
        rrr2.setGraphic(new ImageView(image2));
        rrr3.setGraphic(new ImageView(image3));
        rrr4.setGraphic(new ImageView(image4));
        rrr5.setGraphic(new ImageView(image5));
        rrr6.setGraphic(new ImageView(image6));
        rrr7.setGraphic(new ImageView(image7));
        rrr8.setGraphic(new ImageView(image8));

        rrr1.setTextFill(Color.BLACK);
        rrr2.setTextFill(Color.BLACK);
        rrr3.setTextFill(Color.BLACK);
        rrr4.setTextFill(Color.BLACK);
        rrr5.setTextFill(Color.BLACK);
        rrr6.setTextFill(Color.BLACK);
        rrr7.setTextFill(Color.BLACK);
        rrr8.setTextFill(Color.BLACK);
        rrr1.setLayoutX(95);   //NORD
        rrr1.setLayoutY(475);   //NORD

        rrr2.setLayoutX(200);   //NORDOST
        rrr2.setLayoutY(477);   //NORDOST

        rrr3.setLayoutX(95);   //OST
        rrr3.setLayoutY(515);   //OST

        rrr4.setLayoutX(200);   //ZUIDOST
        rrr4.setLayoutY(512);   //ZUIDOST

        rrr5.setLayoutX(95);   //ZUID
        rrr5.setLayoutY(555);   //ZUID

        rrr6.setLayoutX(200);   //ZUIDWEST
        rrr6.setLayoutY(555);   //ZUIDWEST

        rrr7.setLayoutX(95);   //WEST
        rrr7.setLayoutY(595);   //WEST

        rrr8.setLayoutX(200);   //NORDWEST
        rrr8.setLayoutY(593);   //NORDWEST

        rrr1.setToggleGroup(rrr);
        rrr2.setToggleGroup(rrr);
        rrr3.setToggleGroup(rrr);
        rrr4.setToggleGroup(rrr);
        rrr5.setToggleGroup(rrr);
        rrr6.setToggleGroup(rrr);
        rrr7.setToggleGroup(rrr);
        rrr8.setToggleGroup(rrr);
        root.getChildren().add(rrr1);
        root.getChildren().add(rrr2);
        root.getChildren().add(rrr3);
        root.getChildren().add(rrr4);
        root.getChildren().add(rrr5);
        root.getChildren().add(rrr6);
        root.getChildren().add(rrr7);
        root.getChildren().add(rrr8);

//        //LABEL
//        Label label5 = new Label("NORD");
//        label5.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
//        label5.setTextFill(Color.rgb(191, 0, 0));
//        final double MAX_FONT_SIZE = 14; // define max font size you need
//        label5.setLayoutX(250);
//        label5.setLayoutY(650);
//        root.getChildren().add(label5);

        //SLIDER
        Slider dislider = new Slider();
        dislider.setMin(0);
        dislider.setMax(360);
        dislider.setValue(0);
        dislider.setLayoutX(140);
        dislider.setLayoutY(650);
        dislider.setShowTickLabels(true);
        dislider.setShowTickMarks(true);
        dislider.setMajorTickUnit(45);
        dislider.setMinorTickCount(5);
        dislider.setBlockIncrement(10);
        dislider.valueChangingProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue)
            {
                for (int i = 0; i<360; i++) {
                    if ((int)dislider.getValue() == 45) {
                        //label5.textProperty().setValue("NORDOST");
                    }
                    else if (dislider.getValue() == 90) {
                        //label5.textProperty().setValue("OST");
                    }
                    else if (dislider.getValue() == 135) {
                        //label5.textProperty().setValue("ZUIDOST");
                    }
                    else if (dislider.getValue() == 180) {
                        //label5.textProperty().setValue("ZUID");
                    }
                    else if (dislider.getValue() == 225) {
                        //label5.textProperty().setValue("ZUIDWEST");
                    }
                    else if (dislider.getValue() == 270) {
                        //label5.textProperty().setValue("WEST");
                    }
                    else if (dislider.getValue() == 315) {
                        //label5.textProperty().setValue("NORWEST");
                    }
                    else if (dislider.getValue() == 360) {
                        //label5.textProperty().setValue("NORD");
                    }

                }
            }
        });
        root.getChildren().add(dislider);


        //LABEL
        Label label4 = new Label("Set the Strength:");
        label4.setFont(font);
        label4.setTextFill(Color.BLACK);
        label4.setLayoutX(120);
        label4.setLayoutY(700);
        root.getChildren().add(label4);

        //SLIDER
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setLayoutX(140);
        slider.setLayoutY(750);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        root.getChildren().add(slider);


        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
