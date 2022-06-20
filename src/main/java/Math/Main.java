package Math;

import Bots.*;
import Gui.GolfBall;
import Gui.RemoteControl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main implements Runnable{
    public static double gravity = 9.81;

    public static double mass = 0.056;
    public static double friction = 0.08;
    public static double h = 0.01;
    public static Bots usedBot = new HillClimbImproved();
    public static boolean usebot = false;

    public static void main(String[] args) throws FileNotFoundException {
        Bots[] bot = new Bots[3];
        bot[0] = new HillClimbImproved();
        bot[1] = new ParticleSwarm();
        bot[2] = new RuleBOt();
        List<String> strings = new ArrayList<>();
        List<String> stringsTime = new ArrayList<>();
        for (int i = 0; i < bot.length; i++) {
            for (int j = 0; j < 25; j++) {
                int average = 10;
                double avgTime = 0d;
                int avgHits = 0;
                for (int k = 0; k < average; k++) {
                    bot[0] = new HillClimbImproved();
                    bot[1] = new ParticleSwarm();
                    bot[2] = new RuleBOt();
                    double begin = System.nanoTime();
                    bot[i].botrun();
                    double end = System.nanoTime();
                    avgTime += end - begin;
                    avgHits += PhysicsEngine.HitCounter;
                    PhysicsEngine.HitCounter = 0;
                }
                System.out.println(avgHits/average);
                System.out.println(PhysicsEngine.r);
                strings.add(""+(avgHits/average));
                stringsTime.add(""+(avgTime/average));
                PhysicsEngine.HitCounter = 0;
                PhysicsEngine.r /=1.2;
            }
            try {
                System.out.println(Arrays.toString(strings.toArray()));
                strings.add("s");
                stringsTime.add("s");
                Collections.reverse(strings);
                Collections.reverse(stringsTime);
                Files.write(Paths.get(System.getProperty("user.dir"), bot[i].getClass() + ".csv"), strings, StandardOpenOption.CREATE);
                Files.write(Paths.get(System.getProperty("user.dir"), bot[i].getClass() + "Time.csv"), stringsTime, StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            strings = new ArrayList<>();

            stringsTime = new ArrayList<>();

        }

        }


        //State state = new State( -3,0,3.320626288191713, 1.3073992549590585);
        //OdeSolver solver = new RungeKutta2(state,0.001);
       // PhysicsEngine hf = new PhysicsEngine(0.001);
        //System.out.println(hf.run(solver,state));


    @Override
    public void run() {
        if (usebot) {
            State result = usedBot.botrun();
            System.out.println("vx:" + result.vx +" vy :" + result.vy);
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
