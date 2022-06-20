package Bots;
import Math.*;
public class HillClimbImproved implements Bots{
    public State currentState = new State(PhysicsEngine.x0,PhysicsEngine.y0,0,0);
    public State nextState = null;
    public double increase = 1;
    public  double ratio;
    @Override
    public State botrun() {
        ratio = 2.0;
        PhysicsEngine engine = new PhysicsEngine(Main.h);

        double nextDistance = 999999;
        double currentDistance = engine.distanceHole(currentState);

        while (currentDistance > PhysicsEngine.r){
            int direction = 0;
            while (currentDistance <= nextDistance){
                switch (direction) {
                    case 0 ->
                            nextState = new State(PhysicsEngine.x0, PhysicsEngine.y0, currentState.vx + increase, currentState.vy);
                    case 1 ->
                            nextState = new State(PhysicsEngine.x0, PhysicsEngine.y0, currentState.vx - increase, currentState.vy);
                    case 2 ->
                            nextState = new State(PhysicsEngine.x0, PhysicsEngine.y0, currentState.vx, currentState.vy + increase);
                    case 3 ->
                            nextState = new State(PhysicsEngine.x0, PhysicsEngine.y0, currentState.vx, currentState.vy - increase);
                    default -> {
                        if (increase < Double.MIN_VALUE * 2) {
                            System.out.println(increase);
                            double check = 5 - currentState.vx - currentState.vy;
                            if (check < 0) {
                                ratio = ((ratio - 1) / 2) + 1;
                                increase = 1;
                                System.out.println(ratio);
                                if (ratio <= 1.001) {
                                    System.out.println(increase);
                                    System.out.println(PhysicsEngine.r);
                                    throw new RuntimeException("incrase smaller then 0");
                                }
                            }
                            break;
                        }
                        increase /= ratio;
                        direction = 0;
                    }
                }
                direction++;
                nextDistance = calculateFitness(new State(nextState.x,nextState.y, nextState.vx, nextState.vy));
            }

            currentState = nextState;
            currentDistance = nextDistance;
        }
        return currentState;
    }
    public double calculateFitness(State fitState){
        PhysicsEngine engine = new PhysicsEngine(Main.h);
        OdeSolver solver = new RungeKutta2(fitState, Main.h);
        return engine.run(solver,fitState);
    }
    @Override
    public void increaseAccuracy(int step) {

    }
}
