package Gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class RemoteControl extends Application
{
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Big Golf Remote Control (B. G. R. C.)");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.DARKGREEN);

        //BUTTON OPEN
        Button open = new Button();
        Font font = new Font(25);
        open.setLayoutX(295);
        open.setLayoutY(20);
        open.setMinWidth(200);
        open.setMinHeight(50);
        open.setFont(font);
        open.setText("Open the Field");
        open.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                new GolfMap();
            }
        });
        root.getChildren().add(open);

        //BUTTON HIT
        Button hit = new Button();
        hit.setLayoutX(295);
        hit.setLayoutY(90);
        hit.setMinWidth(200);
        hit.setMinHeight(50);
        hit.setFont(font);
        hit.setText("Hit the Ball");
        hit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.show();
            }
        });
        root.getChildren().add(hit);

        //BUTTON STOP
        Button stop = new Button();
        stop.setLayoutX(295);
        stop.setLayoutY(160);
        stop.setMinWidth(200);
        stop.setMinHeight(50);
        stop.setFont(font);
        stop.setText("Stop the Game");
        stop.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("test");
                GolfMap m = new GolfMap();
                try {
                    m.start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                primaryStage.show();
            }
        });
        root.getChildren().add(stop);

        //LABEL CHOICE 1
        Label label = new Label("Choose the Way:");
        label.setFont(font);
        label.setTextFill(Color.WHITE);
        label.setLayoutX(305);
        label.setLayoutY(230);
        root.getChildren().add(label);

        //RADIO BUTTONS 1
        TilePane r = new TilePane();
        RadioButton r1 = new RadioButton("ME        ");
        RadioButton r2 = new RadioButton("BOT");
        r1.setTextFill(Color.WHITE);
        r2.setTextFill(Color.WHITE);
        r.setLayoutX(340);
        r.setLayoutY(280);
        r.getChildren().add(r1);
        r.getChildren().add(r2);
        root.getChildren().add(r);

        //LABEL CHOICE 2
        Label label2 = new Label("Choose the Method:");
        label2.setFont(font);
        label2.setTextFill(Color.WHITE);
        label2.setLayoutX(290);
        label2.setLayoutY(320);
        root.getChildren().add(label2);

        //RADIO BUTTONS 2
        TilePane rr = new TilePane();
        RadioButton rr1 = new RadioButton("EULER");
        RadioButton rr2 = new RadioButton("SI EULER");
        RadioButton rr3 = new RadioButton("RUNGEKUTTA  ");
        RadioButton rr4 = new RadioButton("RUNGEKUTTA4  ");
        rr1.setTextFill(Color.WHITE);
        rr2.setTextFill(Color.WHITE);
        rr3.setTextFill(Color.WHITE);
        rr4.setTextFill(Color.WHITE);
        rr.setLayoutX(150);
        rr.setLayoutY(370);
        rr.getChildren().add(rr1);
        rr.getChildren().add(rr2);
        rr.getChildren().add(rr3);
        rr.getChildren().add(rr4);
        root.getChildren().add(rr);

        //LABEL CHOICE 3
        Label label3 = new Label("Choose the Direction:");
        label3.setFont(font);
        label3.setTextFill(Color.WHITE);
        label3.setLayoutX(280);
        label3.setLayoutY(405);
        root.getChildren().add(label3);

        //RADIO BUTTONS 3
        HBox rrr = new HBox();
        RadioButton rrr1 = new RadioButton("   NORD        ");
        RadioButton rrr2 = new RadioButton("   NORDOST        ");
        RadioButton rrr3 = new RadioButton("   OST        ");
        RadioButton rrr4 = new RadioButton("   ZUIDOST       ");
        RadioButton rrr5 = new RadioButton("   ZUID       ");
        RadioButton rrr6 = new RadioButton("   ZUIDWEST        ");
        RadioButton rrr7 = new RadioButton("   WEST       ");
        RadioButton rrr8 = new RadioButton("   NORDWEST        ");
        rrr1.setTextFill(Color.WHITE);
        rrr2.setTextFill(Color.WHITE);
        rrr3.setTextFill(Color.WHITE);
        rrr4.setTextFill(Color.WHITE);
        rrr5.setTextFill(Color.WHITE);
        rrr6.setTextFill(Color.WHITE);
        rrr7.setTextFill(Color.WHITE);
        rrr8.setTextFill(Color.WHITE);
        rrr.setLayoutX(15);
        rrr.setLayoutY(460);
        rrr.getChildren().add(rrr1);
        rrr.getChildren().add(rrr2);
        rrr.getChildren().add(rrr3);
        rrr.getChildren().add(rrr4);
        rrr.getChildren().add(rrr5);
        rrr.getChildren().add(rrr6);
        rrr.getChildren().add(rrr7);
        rrr.getChildren().add(rrr8);
        root.getChildren().add(rrr);

        //LABEL
        Label label4 = new Label("Set the Strength:");
        label4.setFont(font);
        label4.setTextFill(Color.WHITE);
        label4.setLayoutX(310);
        label4.setLayoutY(500);
        root.getChildren().add(label4);

        //SLIDER
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setLayoutX(330);
        slider.setLayoutY(550);
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
