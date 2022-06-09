package Math;

import Bots.*;
import Gui.GolfBall;
import Gui.GolfMap;
import Gui.RemoteControl;
import javafx.application.Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main implements Runnable{
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static double h = 0.000001;
    public static Bots usedBot = new HillClimbImproved();
    public static boolean usebot = false;

    public static void main(String[] args) throws FileNotFoundException {
        OdeSolver solvers[] = new OdeSolver[5];

        List<String> strings = new ArrayList<String>();
        OdeSolver bigsolve = new RungeKutta4(new State(2,0,2,2),h*0.1);
        State[] stateEnd = new State[5];


        State[][] state = new State[150][solvers.length];
        for (int i = 0; i < solvers.length; i++) {
            State start = new State(2, 0, 2, 2);
            solvers[3] = new BackwardsEuler(start, h*0.1);
            solvers[2] = new Euler(start, h*0.1);
            solvers[1] = new RungeKutta2(start, h*0.1);
            solvers[0] = new RungeKutta4(start, h*0.1);
            solvers[4] = new ImprovedEuler(start, h*0.1);
            for (int j = 0; j < 10 / (h*0.1); j++) {
                stateEnd[i] = solvers[i].solver();
            }
            for (int k = 0; k < 5; k++) {

                start = new State(2, 0, 2, 2);
                solvers[3] = new BackwardsEuler(start, h);
                solvers[2] = new Euler(start, h);
                solvers[1] = new RungeKutta2(start, h);
                solvers[0] = new RungeKutta4(start, h);
                solvers[4] = new ImprovedEuler(start, h);

                for (int j = 0; j < 100 / h; j++) {
                    state[k][i] = solvers[i].solver();
                }
                if(i == -1) {
                    strings.add(" " + Math.abs((state[k][0].vx - state[k][i].vx) + (state[k][0].vy - state[k][i].vy)));
                }
                else {
                    strings.add(" " + Math.abs((stateEnd[i].vx - state[k][i].vx) + (stateEnd[i].vy - state[k][i].vy)));
                }
                h *= 10;
            }
            h = 0.000001;
            try {
                Files.write(Paths.get(System.getProperty("user.dir"), solvers[i].getClass() + ".csv"), strings, StandardOpenOption.CREATE);

            } catch (IOException e) {
                e.printStackTrace();
            }
            strings = new ArrayList<String>();
        }


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
            PhysicsEngine.GUI = false;

        }
        else {
            State hit = new State(GolfBall.ball.translateXProperty().getValue(),GolfBall.ball.translateYProperty().getValue(),Math.cos(Math.toRadians(RemoteControl.dislider.getValue()))*(RemoteControl.slider.getValue()/20),Math.sin(Math.toRadians(RemoteControl.dislider.getValue()))*(RemoteControl.slider.getValue()/20));
            System.out.println(hit.vx+" "+hit.vy);
            PhysicsEngine.GUI = true;
            PhysicsEngine engine = new PhysicsEngine(h);
            OdeSolver RungeKutta4 = new RungeKutta4(hit, h);
            engine.run(RungeKutta4, hit);
            PhysicsEngine.GUI = false;
            System.out.println(" X:" + GolfBall.X + " Y:" + GolfBall.Y);
        }
    }
}
