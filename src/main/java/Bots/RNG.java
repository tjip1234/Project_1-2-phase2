package Bots;

import java.util.Arrays;
import Math.PhysicsEngine;
import Math.*;
public class RNG {
    

    int dimension = 2;
    static long time;
    double x = PhysicsEngine.x0; //TODO specific case here
    double y = PhysicsEngine.y0;
    double h = Main.h;
    double step = 0.0; // TODO change step here
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
            
            double[] randomPosition = randomArray(dimension);
            double fitness = calcFitness(randomPosition);
            
                    
            numberOfEval++;
                    
            if (fitness <= PhysicsEngine.r) {
                done = true;
                hitFitness = fitness;
                hitPosition = Arrays.copyOf(randomPosition, randomPosition.length);                      
                // System.out.println("hit");
                System.out.println("Location of hit: " + Arrays.toString(randomPosition) + " Fitness: " + hitFitness);
                System.out.println("Number of evaluations: " + numberOfEval);
                System.out.println(System.currentTimeMillis() - time + "ms");
                    return randomPosition;                                                
            }
                   

                
                if (fitness < bestFitness) {
                    bestFitness = fitness;
                    bestPosition = Arrays.copyOf(randomPosition, randomPosition.length);                    
                }
                
                                              
        }
        return bestPosition;  
    }
        
    
    
    public double calcFitness(double[] position){

        double vx = position[0];
        double vy = position[1];
        PhysicsEngine engine = new PhysicsEngine(h);
        OdeSolver solver = new RungeKutta2(new State(x,y,vx,vy) , h);
        double fitness = engine.run(solver,new State(x,y,vx,vy));
        return fitness; //TODO GOLF CASE
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


    
}


