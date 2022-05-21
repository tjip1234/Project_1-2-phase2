package Math;

import Bots.*;
import Bots.RuleBOt;
import Gui.GolfBall;

import java.io.FileNotFoundException;


public class Main implements Runnable{
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static double h = 0.0005;
    public static void main(String[] args) throws FileNotFoundException {
        Bots h = new ParticleSwarm();
        h.increaseAccuracy(1);
        h.botrun();
        //State state = new State( -3,0,3.320626288191713, 1.3073992549590585);
        //OdeSolver solver = new RungeKutta2(state,0.001);
       // PhysicsEngine h = new PhysicsEngine(0.001);
       // System.out.println(h.run(solver,state));
    }

    @Override
    public void run() {
        Bots p = new ParticleSwarm();
        p.increaseAccuracy(1);
        State result = p.botrun();
        PhysicsEngine.GUI = true;
        PhysicsEngine engine = new PhysicsEngine(h);
        OdeSolver RungeKutta4 = new RungeKutta4(result, h);
        engine.run(RungeKutta4, result);
        System.out.println(" X:"+ GolfBall.X + " Y:" + GolfBall.Y);

    }
}
