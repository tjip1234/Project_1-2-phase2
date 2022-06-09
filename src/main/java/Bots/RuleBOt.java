package Bots;
import Math.*;
public class RuleBOt implements Bots {
    public static double increment = 0.1;
    @Override
    public State botrun() {
         double min = 1000000000;
         double minvx = 0;
         double minvy = 0;

        for (double vx = 0; vx < 5; vx += increment) {
            for (double vy = 0; vy < 5; vy += increment) {
                if(checkBoundary(vx, vy)){break;}
                PhysicsEngine a = new PhysicsEngine(Main.h);
                OdeSolver b = new RungeKutta4(new State(PhysicsEngine.x0, PhysicsEngine.y0, vx, vy), Main.h);
                double distance = a.run(b, new State(PhysicsEngine.x0, PhysicsEngine.y0, vx, vy));
                if(distance<min){
                    min=distance;
                    minvx = vx;
                    minvy = vy;
                    if (PhysicsEngine.observe3) {
                        PhysicsEngine.distances.add(min);
                    }
                }

            }
            
        }
        System.out.println(min +" ," + minvx + " ," + minvy);

        return new State(PhysicsEngine.x0,PhysicsEngine.y0,minvx,minvy);
    }

    public boolean checkBoundary(double vx, double vy){
       return (Math.sqrt(vx * vx+ vy * vy) > 5.0);   
    }




    @Override
    public void increaseAccuracy(int step) {
        increment = increment * 1/step;
    }
}
