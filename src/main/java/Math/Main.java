package Math;

import Bots.*;
import Gui.GolfBall;
import Gui.GolfMap;
import Gui.RemoteControl;
import javafx.application.Application;

import java.io.FileNotFoundException;


public class Main implements Runnable{
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static double h = 0.01;
    public static Bots usedBot = new HillClimbImproved();
    public static boolean usebot = false;

    public static void main(String[] args) throws FileNotFoundException {
        Bots bot[] = new Bots[6];
        bot[0] = new HillClimbImproved();
        bot[1] = new HillClimb();
        bot[2] = new CuckooSearch();
        bot[3] = new RuleBOt();
        bot[4] = new ParticleSwarm();
        bot[5] = new RandomBot();
        String result = "";

        for (int i = 0; i < bot.length; i++) {
            double begin = System.nanoTime();
            bot[i].botrun();
            double end = System.nanoTime();
            double time = end - begin;
            result += ", " +PhysicsEngine.HitCounter +" time: "+ time;
            PhysicsEngine.HitCounter = 0;
        }

        System.out.println(result);

        //State state = new State( -3,0,3.320626288191713, 1.3073992549590585);
        //OdeSolver solver = new RungeKutta2(state,0.001);
       // PhysicsEngine hf = new PhysicsEngine(0.001);
        //System.out.println(hf.run(solver,state));
    }

    @Override
    public void run() {
        if (usebot) {
            State result = usedBot.botrun();
            PhysicsEngine.GUI = true;
            PhysicsEngine engine = new PhysicsEngine(h);
            OdeSolver RungeKutta4 = new RungeKutta4(result, h);
            engine.run(RungeKutta4, result);
            System.out.println(" X:" + result.x + " Y:" + result.y + " VX:" + result.vx + " VY:"+ result.vy);
            System.out.println(PhysicsEngine.HitCounter);
            System.out.println(usedBot.getClass());

        }
        else {
            State hit = new State(GolfBall.ball.translateXProperty().getValue(),GolfBall.ball.translateYProperty().getValue(),Math.cos(Math.toRadians(RemoteControl.dislider.getValue()))*(RemoteControl.slider.getValue()/20),Math.sin(Math.toRadians(RemoteControl.dislider.getValue()))*(RemoteControl.slider.getValue()/20));
            System.out.println(hit.vx+" "+hit.vy);
            PhysicsEngine.GUI = true;
            PhysicsEngine engine = new PhysicsEngine(h);
            OdeSolver RungeKutta4 = new RungeKutta4(hit, h);
            engine.run(RungeKutta4, hit);

            System.out.println(" X:" + GolfBall.X + " Y:" + GolfBall.Y);



        }
    }
}
