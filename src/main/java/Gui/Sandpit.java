package Gui;

import java.util.ArrayList;

public class Sandpit {
    public static ArrayList<Sandpit> sandList = new ArrayList<>();
    public static double length;
    public static double height;
    public static double x;
    public static double y;

    public Sandpit(double x, double y, double length, double height){
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        sandList.add(this);
    }
}