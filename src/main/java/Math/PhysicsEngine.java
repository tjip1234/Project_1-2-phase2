package Math;

import java.util.LinkedList;

public class PhysicsEngine extends mathFunction{
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    //public static final double real = 1.359157322;
    public static final double real = 0.817278119005946;
    public static final double xt = 4;
    public static final double yt = 1;
    public static final double r = 0.15;
    //public double h = 0.45;
    public  static final double x0 = -3;
    public  static final double y0 = 0;
    public double h = 0.001;
    public double goToEuler = 0.03;
    public boolean water = false;
    public static final double endTime = 0.5;
    public static boolean observe = false;
    public static boolean observe2 = false;
    public static boolean observe3 = false;
    public static boolean GUI = false;
    public LinkedList<State> observed;
    public static LinkedList<Double> distances;
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
                    catch(Exception e){

                    }
                }
                if (observe){
                    observed.add(new State(state.x,state.y,state.vx,state.vy));
                }
                if (mathFunction.Function(state.x, state.y) < 0) {
                    //System.out.println(state.x + " " + state.y);
                    water = true;
                    if (observe2){
                        distances.add(distanceHole(state));
                    }
                    return distanceHole(state);
                }
                if (inHole(state)) {
                    //System.out.println("hit");
                    if (observe2){
                        distances.add(distanceHole(state));
                    }
                    return 0;
                }
                if (Stop(state, h)) {
                    if (StopComplete(state.x, state.y, h)) {
                        //System.out.println(state.x + " " + state.y);
                        if (observe2){
                            distances.add(distanceHole(state));
                        }
                        return distanceHole(state);
                    }
                }
            }
    }

    public boolean StopComplete(double X, double Y, double h){
        // if((derivativeOf(X,Y,'x') ==  0) && (derivativeOf(X,Y,'y') == 0) || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) && (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){ // range
        //     return true;
        // }
        //if(derivativeOf(X,Y,'x') == 0 || derivativeOf(X,Y,'y') == 0 || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) || (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){
         HelperFunctions helper = new HelperFunctions(gravity,friction);
            if(StaticFriction > Math.sqrt(Math.pow(helper.derivativeOf(X,Y,'x'), 2) + Math.pow(helper.derivativeOf(X,Y,'y'), 2))){
                return true;
            }

        //}
        return false;
    }
    public boolean Stop(State state,double h){
        return Math.sqrt(state.vx*state.vx + state.vy * state.vy) < h;
    }

    public boolean inHole(State state){
        return Math.abs(distanceHole(state)) < r;
    }
    public double distanceHole(State state){
        double dxSq = (state.x - xt)*(state.x - xt);
        double dySq = (state.y - yt)*(state.y - yt);
        double dzSq = (Function(state.x, state.y)-Function(xt, yt)) * (Function(state.x, state.y)-Function(xt, yt));
        return Math.sqrt(dxSq + dySq);

    }
}