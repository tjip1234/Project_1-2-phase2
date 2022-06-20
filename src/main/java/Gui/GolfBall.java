package Gui;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.Group;




public class GolfBall
{

    public static double prevX;
    public static double prevY;
    public static double prevZ;

    public static double X;
    public static double Y;
    public static double Z;
public static double rAdius = 0.4;
    public static Sphere ball = new Sphere();

    private GolfBall(double x, double y, double z)
    {
        X = 0;
        Y = 0;
        Z = 0;

    }

    public static GolfBall createBall(double x, double y, double z)
    {
        return new GolfBall(20, 20, 20);
    }

}