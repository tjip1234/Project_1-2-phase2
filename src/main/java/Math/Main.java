package Math;

import Bots.*;
import Gui.GolfBall;

import java.io.FileNotFoundException;


public class Main implements Runnable{
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static double h = 0.001;
    public static Bots usedBot = new HillClimbImproved();

    public static void main(String[] args) throws FileNotFoundException {
        Bots h = new HillClimbImproved();
        h.increaseAccuracy(1);
        h.botrun();
        System.out.println(PhysicsEngine.HitCounter);

        //State state = new State( -3,0,3.320626288191713, 1.3073992549590585);
        //OdeSolver solver = new RungeKutta2(state,0.001);
       // PhysicsEngine hf = new PhysicsEngine(0.001);
        //System.out.println(hf.run(solver,state));
    }

    @Override
    public void run() {
        State result = usedBot.botrun();
        PhysicsEngine.GUI = true;
        PhysicsEngine engine = new PhysicsEngine(h);
        OdeSolver RungeKutta4 = new RungeKutta4(result, h);
        engine.run(RungeKutta4, result);
        System.out.println(" X:"+ GolfBall.X + " Y:" + GolfBall.Y);
        System.out.println(usedBot.getClass());

    }
}
