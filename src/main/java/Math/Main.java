package Math;

import java.io.FileNotFoundException;


public class Main {
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static void main(String[] args) throws FileNotFoundException {
        State state = new State(0,0,4,1);
        OdeSolver solver = new Euler(state,0.001);
        PhysicsEngine h = new PhysicsEngine(0.001);
        System.out.println(h.run(solver,state));
    }  
    
}
