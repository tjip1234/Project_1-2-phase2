package Gui;

import java.util.ArrayList;

public class Tree {
    public static ArrayList<Tree> treeList = new ArrayList<>();
    public static double radius;
    public static double height;
    public static double x;
    public static double y;

    public Tree(double x, double y, double radius, double height){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.height = height;
        treeList.add(this);
    }
}

//package Gui;
//
//import Maze.MazeCreator;
//import javafx.application.Application;
//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.geometry.Insets;
//import javafx.scene.*;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Slider;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.ScrollEvent;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
//import javafx.scene.shape.Box;
//import javafx.scene.shape.Cylinder;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Sphere;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.transform.Rotate;
//import javafx.scene.transform.Transform;
//import javafx.stage.Stage;
//import Math.PhysicsEngine;
//import Math.mathFunction;
//
//import static java.lang.Math.random;
////import static jdk.internal.org.jline.utils.Colors.C;
//
//public class Tree
//{
//    static GolfMap.SmartGroup group;
//    public static double x = random();
//    public static double y = random();
//    public static double z = random();
//    public static double xС = random();
//    public static double yС = random();
//    public static double zС = random();
//    public static double radian = 0.2;
//    private static double xC = random();
//    private static double zC = random();
//    private static double yC = random();
//    public static boolean kuku = false;
//
//    public static void prepareTree() {
//        //
//        Cylinder trunky = new Cylinder(radian, 3);
//        PhongMaterial material = new PhongMaterial();
//        material.setDiffuseColor(Color.rgb(105, 52, 23));
//        trunky.setMaterial(material);
//        trunky.setTranslateX(x);
//        trunky.setTranslateY(y);
//        trunky.setTranslateZ(z);
//        trunky.setRotationAxis(Rotate.X_AXIS);
//        trunky.setRotate(90);
//        group.getChildren().add(trunky);
//        //
//        Sphere foliage = new Sphere();
//        PhongMaterial material2 = new PhongMaterial();
//        material2.setDiffuseColor(Color.rgb(94, 183, 49));
//        foliage.setMaterial(material2);
//        foliage.setRadius(1);
//        if (x==xC)
//        {
//            foliage.setTranslateX(xС);
//        }
//        if (y==yC)
//        {
//            foliage.setTranslateY(yС);
//        }
//        if (y==zC)
//        {
//            foliage.setTranslateZ(zС);
//        }
//        group.getChildren().add(foliage);
//    }
//    public static void main()
//    {
//        if (MazeCreator.obstacle1==0)
//        {
////            for (double i = 0; i < GolfMap.FRAME_WIDTH-200; i++)
////            {
//                prepareTree();
//                kuku=true;
////            }
//        }
//        else if (MazeCreator.obstacle2==0)
//        {
////            for (double i = 0; i < GolfMap.FRAME_WIDTH-200; i++)
////            {
//                prepareTree();
//                kuku=true;
////            }
//        }
//        else if (MazeCreator.obstacle3==0)
//        {
////            for (double i = 0; i < GolfMap.FRAME_WIDTH-200; i++)
////            {
//                prepareTree();
//                kuku=true;
////            }
//        }
//    }
//}
