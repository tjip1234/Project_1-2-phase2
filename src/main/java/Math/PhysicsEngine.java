package Math;

import Gui.GolfMap;

import java.util.LinkedList;

public class PhysicsEngine extends mathFunction{
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double friction = 0.08;
    public static final double xt = 4;
    public static final double yt = 4;
    public static double r = 0.15;
    //public double h = 0.45;
    public  static final double x0 = -3;
    public  static final double y0 = 0;
    public double h ;
    public boolean water = false;
    public static boolean observe = true;
    public static boolean GUI = false;
    public LinkedList<State> observed;
    public static int HitCounter = 0;

    public PhysicsEngine(double h){
    this.h = h;
    }
 public double run(OdeSolver solver, State state) {
        HitCounter++;
            while(true) {
                state = solver.solver();
                //
                if(GUI) {
                    //System.out.println(state.x + " " + state.y);
                    ConnectionGUI.updateBall(state.x, state.y);
                    try {
                        Thread.sleep((long) (1000*Main.h));
                    }
                    catch(Exception ignored){

                    }
                }

                if (GolfMap.inAnyTree(state.x, state.y))
                {
                    state.vx=0;
                    state.vy=0;
                    return distanceHole(state);
                }
                if (GolfMap.inSandpit(state.x, state.y))
                {
                    StaticFriction = 0.4;
                    friction = 0.2;
                    System.out.println("sand");
                }
                else {
                    StaticFriction = 0.2;
                    friction = 0.08;
                }



                if (mathFunction.Function(state.x, state.y) < 0) {
                    //System.out.println(state.x + " " + state.y);
                    water = true;
                    ConnectionGUI.updateBall(state.x, state.y);
                    return distanceHole(state);
                }
                if (inHole(state)) {
                    //System.out.println("hit");
                    return 0;
                }
                if (Stop(state, h)) {
                    if (StopComplete(state.x, state.y)) {
                        //System.out.println(state.x + " " + state.y);
                        return distanceHole(state);
                    }
                }
            }
    }

    public boolean StopComplete(double X, double Y){
        // if((derivativeOf(X,Y,'x') ==  0) && (derivativeOf(X,Y,'y') == 0) || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) && (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){ // range
        //     return true;
        // }
        //if(derivativeOf(X,Y,'x') == 0 || derivativeOf(X,Y,'y') == 0 || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) || (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){
         HelperFunctions helper = new HelperFunctions(gravity,friction);
            if(StaticFriction > Math.sqrt(Math.pow(helper.derivativeOf(X,Y,'x'), 2) + Math.pow(helper.derivativeOf(X,Y,'y'), 2))){
                return true;
            }
            if (Double.isNaN(X)&&Double.isNaN(Y))
                return true;
        //}
        return false;
    }
    public boolean Stop(State state,double h){
        if (Double.isNaN(state.vx)&&Double.isNaN(state.vy))
            return true;
        return Math.sqrt(state.vx*state.vx + state.vy * state.vy) < h;
    }

    public boolean inHole(State state){
        return Math.abs(distanceHole(state)) < r;
    }
    public double distanceHole(State state){
        double dxSq = (state.x - xt)*(state.x - xt);
        double dySq = (state.y - yt)*(state.y - yt);
        //double dzSq = (Function(state.x, state.y)-Function(xt, yt)) * (Function(state.x, state.y)-Function(xt, yt));
        return Math.sqrt(dxSq + dySq);

    }
}