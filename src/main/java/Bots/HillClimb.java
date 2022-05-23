package Bots;

import java.util.Arrays;
import Math.*;
public class HillClimb implements Bots{
    int dimension = 2;
    double x = PhysicsEngine.x0; //TODO specific case here
    double y = PhysicsEngine.y0;
    double h = Main.h;
    double step = 0.1; // TODO change step here
    static double numberOfEval;
    static boolean done = false;
    static double[] hitPosition = new double[2];
    static double hitFitness;
    
    
    // public HillClimb(double[] bestPosition){
    //     this.bestPosition = bestPosition;
    // }
    // public HillClimb(){
    //     bestPosition = randomArray(this.bestPosition.length);
    // }

    public double[] run(){
        numberOfEval = 1;
        boolean terminate = false;
        double bestFitness = 99999999;
        double[] bestPosition = new double[dimension];
        double lol = 0;
        while (!done) { // TODO !done
            boolean converged = false;
            double[] randomPosition = randomArray(dimension);
            double fitness = calcFitness(randomPosition);
            while (!converged) {
                double[][] moves = new double[4][2]; //TODO dimensionality change here left right up down // also possibly increase numb of moves?
                moves[0] = makeMove(randomPosition,1.0, 0.0);
                moves[1] = makeMove(randomPosition, -1.0, 0.0);
                moves[2] = makeMove(randomPosition, 0.0, 1.0);
                moves[3] = makeMove(randomPosition, 0.0, -1.0);
                converged = true;
                for (int i = 0; i < moves.length; i++) {
                    if (!withinConstraint(moves[i])) {
                        break;                       
                    }
                    double[] move = moves[i];
                    double moveFitness = calcFitness(move);
                    numberOfEval++;
                    //System.out.println("move fitness: " + moveFitness);
                    if (moveFitness <= 0.15) {
                        done = true;
                        hitFitness = moveFitness;
                        hitPosition = Arrays.copyOf(move, move.length);                      
                        // System.out.println("hit");
                        System.out.println("Location of hit: " + Arrays.toString(randomPosition) + " Fitness: " + hitFitness);
                        System.out.println("Number of evaluations: " + numberOfEval);
                         return move;                                                
                    }
                    if (moveFitness < fitness) {
                        fitness = moveFitness;
                        randomPosition = Arrays.copyOf(move, move.length);                        
                    }

                }
                if (fitness < bestFitness) {
                    converged = false;
                    System.out.println(fitness);
                    bestFitness = fitness;
                    bestPosition = Arrays.copyOf(randomPosition, randomPosition.length);                    
                }
                else{
                }
                                              
            }
            lol++;
            
        }
        System.out.println("Number of evaluations: " + numberOfEval);
        System.out.println("Best fitness: " + bestFitness);
        System.out.println("Best position: " + Arrays.toString(bestPosition));
        return bestPosition;


    }

    // HELPER METHODS
    // xStep and yStep should be 1 / -1 / 0
    public double[] makeMove(double[] position, double xStep, double yStep){
        position[0] += xStep * step;
        position[1] += yStep * step;
        return position;
    }

    public double calcFitness(double[] position){
        PhysicsEngine engine = new PhysicsEngine(h);
        double vx = position[0];
        double vy = position[1];
        OdeSolver solver = new RungeKutta4(new State(x,y,vx,vy), h);
        return engine.run(solver, new State(x,y,vx,vy));
    }

    public double[] randomArray(int length){
        double[] array = new double[length];
        for(int i = 0; i < length; i++){
            array[i] = Math.random()*10-5; //TODO CONSTRAINT
        }
        if (withinConstraint(array)) {
            return array;           
        } else {
            return randomArray(length);            
        }
    }
    public boolean withinConstraint(double[] position){
        double a = position[0];
        double b = position[1];
        return Math.sqrt(a*a + b*b) <= 5.0; //TODO CONSTRAINT
    }

    @Override
    public State botrun() {
        double[] n = run();
        return new State(x,y,n[0],n[1]);
    }

    @Override
    public void increaseAccuracy(int step) {

    }
}
