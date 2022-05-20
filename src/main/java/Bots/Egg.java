package Bots;
import Math.*;
public class Egg {
    public static final int dimensionality = 2;
    public double[] location;
    public double fitness = 696969;
    double x = PhysicsEngine.x0;
    double y = PhysicsEngine.y0;
    double h = Main.h;
    
    public Egg(){
        this.location = randomArray(dimensionality);
        this.fitness = calcFitness();
    }
    public Egg(double[] location){
        this.location = location;
        this.fitness = calcFitness();
    }



    public double calcFitness(){
        if (!withinConstraint(location)) {
           location = randomArray(dimensionality);                        
        }
       double vx = location[0];
       double vy = location[1];
       //double fitness = x1*x1 - x1*x2 + x2*x2 + 2*x1 + 4*x2 + 3;
       PhysicsEngine engine = new PhysicsEngine(0.01);
       OdeSolver solver = new RungeKutta2(new State(x,y,vx,vy) , 0.01);
       double fitness = engine.run(solver,new State(x,y,vx,vy));
       return fitness;

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
