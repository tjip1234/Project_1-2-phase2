package Gui;

import java.util.ArrayList;

public class Flag {
    public static ArrayList<Flag> flagList = new ArrayList<>();
    public static double radius;
    public static double height;
    public static double x;
    public static double y;

    public Flag(double x, double y, double radius, double height){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.height = height;
        flagList.add(this);
    }
}
