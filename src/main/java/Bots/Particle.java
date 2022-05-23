package Bots;

import java.util.Arrays;
import Math.*;

/**
 * This class is used to create an object of type Particle which will
 * be used to run the Particle Swarm optimization algorithm
 */
public class Particle {
    public static final int dimensionality = 2;
    double[] location;
    double[] currentDirection;
    double[] individualBest;
    public static double[] groupBest;
    public static double bestFitness;
    public double individualBestFitness;
    public static final double upperBound = 5;
    public static final double lowerBound = -5;
    public static final double step =  1;

    public double fitness;
    // search calibrators
    //double inertiaWeight = 0.5;
    //double indComp = 0.2;
    //double socComp = 0.3;
     public static  double inertiaWeight = 0.3925;
    public static final double indComp = 2.5586;
     public static final double socComp = 1.3358;

    //public static  double inertiaWeight = 0.5;
    //public static  double indComp = 1.55;
    //public static final double socComp = 1.55; //1.55 together maybe? the sum
    public static int fitnessEvaluations;
    // decreasing number through iterations
        
        public Particle() {
            this.currentDirection = randomArray();
            this.location = randomArray();
            this.fitness = calcFitness(location);
            individualBestFitness = this.fitness;
            individualBest = Arrays.copyOf(location, location.length);
            
        }


        /**
         * Creates a random array within the boundaries
         * @return the newly created array
         */
        public double[] randomArray(){
            double[] array = new double[dimensionality];
            for(int i = 0; i < array.length; i++){
                array[i] = Math.random()*(upperBound-lowerBound)+lowerBound;
            }
            return array;
        }
    public double calcFitness(double[] location){
        double vx = location[0];
        double vy = location[1];
        PhysicsEngine engine = new PhysicsEngine(Main.h);
        OdeSolver solver = new RungeKutta2(new State(PhysicsEngine.x0, PhysicsEngine.y0,vx,vy) , Main.h);
        double fitness = engine.run(solver,new State(PhysicsEngine.x0, PhysicsEngine.y0,vx,vy));
        return fitness;
    }

        /**
         * Performs vector addition between two vectors
         * @param vector1
         * @param vector2
         * @return
         */
        public double[] add2Vectors(double[] vector1, double[] vector2){
            double[] sumVector = new double[dimensionality];
            for( int i = 0; i < sumVector.length; i++){
                sumVector[i] = vector1[i] + vector2[i];
            }
            return sumVector;
        }

        /**
         * Multiplies a vector by a constant 
         * @param constant
         * @param vector
         * @return
         */
        public double[] constantTimesVector(double constant, double[] vector){
            double[] resultVector = new double[dimensionality];
            for( int i = 0; i < resultVector.length; i++){
                resultVector[i] = constant * vector[i];
            }
            return resultVector; 
        }

        /**
         * Calculates new location vector for a particle
         * @param location
         * @param newVelocity
         * @return the new location vector
         */
        public double[] calcNewLocation(double[] location, double[] newVelocity){
            double[] newLocation = new double[dimensionality];
            newLocation = add2Vectors(location, newVelocity);
            return newLocation;
        }

        /**
         * Adds all vectors up and computes the new velocity
         * @param indVector individual component vector
         * @param socVector social component vector
         * @return the new velocity vector
         */
        public double[] calcNewVelocity(double[] indVector, double[] socVector){
            double[] newVelocity = new double[dimensionality];
            for( int i = 0; i < newVelocity.length; i++){
                newVelocity[i] = step * (inertiaWeight * currentDirection[i] + indVector[i] + socVector[i]);
            }
            currentDirection = Arrays.copyOf(newVelocity, newVelocity.length);
            return newVelocity;
        }

        /**
         * Calculates individual component of movement
         * @return the individual component vector
         */
        public double[] calcIndVector(){
            double constant = indComp * Math.random();
            double[] indVector = new double[dimensionality];
            double[] vector = new double[dimensionality];
            vector = subtract2Vectors(individualBest, location);
            for (int i = 0; i < dimensionality; i++) {
                indVector[i] = indComp * Math.random() * vector[i];
            }
            return indVector;
        }

        /**
         * Performs vector subtraction between two vectors
         * @param vector1
         * @param vector2
         * @return the resulting subtracted vector
         */
        public double[] subtract2Vectors(double[] vector1, double[] vector2){
            double[] sumVector = new double[dimensionality];
            for( int i = 0; i < sumVector.length; i++){
                sumVector[i] = vector1[i] - vector2[i];
            }
            return sumVector;
        }

        /**
         * Computes the social component of movement
         * @return the social component vector
         */
        public double[] calcSocVector(){
            double constant = socComp * Math.random();
            double[] socVector = new double[dimensionality];
            double[] vector = new double[dimensionality];
            vector = subtract2Vectors(groupBest, location);
            for (int i = 0; i < dimensionality; i++) {
                socVector[i] = socComp * Math.random() * vector[i];
            }
            return socVector;
        }
        public static double crossInTray(double[] location){
            double x = location[0];
            double y = location[1];
            double fact1 = Math.sin(x)*Math.sin(y);
            double fact2 = Math.exp(Math.abs(100 - Math.sqrt(x*x+y*y)/Math.PI));
    
            return -0.0001 * Math.pow((Math.abs(fact1*fact2)+1),0.1);
        }

        /**
         * Computes the fitness based on 100 runs of a game
         * @return average number of eliminated rows 
         */
       
        public double dropWave(double[] location){
            double x = location[0];
            double y = location[1];
            double upper = 1 + Math.cos(12 * Math.sqrt(x*x + y*y));
            double lower = 0.5 * (x*x + y*y) + 2;
            return -upper / lower;
        }
        // taken from rosetta stone
        private static double mccormick(double[] x) {
            double a = x[0];
            double b = x[1];
            return Math.sin(a + b) + (a - b) * (a - b) + 1.0 + 2.5 * b - 1.5 * a;
        }
        // taken from rosetta stone
     
        public static double michalewicz(double[] position) {
            int m = 10;
            double sum = 0.0;
            for (int i = 1; i <= dimensionality; i++) {
                double xi = position[(i - 1)];
                double pow = 1.0;
                double xiPow = Math.sin(i * (xi * xi) / Math.PI);
                for (int j = 1; j <= (2 * m); j++) {
                    pow *= xiPow;
                }
                sum += Math.sin(xi) * pow;
            }
            return -sum;
        }

        /**
         * Checks if every parameter of the vector is in the boundary and if not 
         * it sets it randomly within the boundary
         */
        public void checkBoundary(){
            while (!valid(location)) {
                for (int i = 0; i < location.length; i++) {
                    if (!validParameter(location[i])) {
                        location[i] = Math.random()*(upperBound-lowerBound)+lowerBound;                        
                    }
                }                
            }       
        }

        public void updateParticle(){
            this.location = this.calcNewLocation(this.location, this.calcNewVelocity(this.calcIndVector(), this.calcSocVector()));
            checkBoundary();
            fitness = calcFitness(location);
            if (fitness < individualBestFitness){
                individualBestFitness = fitness;
                individualBest = location;
            }
        }

        public boolean valid(double[] position){
            double a = position[0];
            double b = position[1];
            return a <= upperBound && b <= upperBound && a >= lowerBound && b >= lowerBound; //TODO CONSTRAINT
        }
        public boolean validParameter(double a){
            return a <= upperBound && a >= lowerBound; //TODO CONSTRAINT
        }
        public double eggHolder(double[] location){
            double x = location[0];
            double y = location[1];
            return -(y + 47) * Math.sin(Math.sqrt(Math.abs(y + x / 2 + 47))) - x * Math.sin(Math.sqrt(Math.abs(x - (y + 47))));
        }
        public static double bukin(double[] location){
            double x = location[0];
            double y = location[1];
            return 100 * Math.sqrt(Math.abs(y - 0.01 * x * x)) + 0.01 * Math.abs(x + 10);
        }
        public static double holder(double[] location){
            double x = location[0];
            double y = location[1];
            double fact1 = Math.sin(x)*Math.cos(y);
            double fact2 = Math.exp(Math.abs(1 - Math.sqrt(x*x+y*y)/Math.PI));
    
            return -Math.abs(fact1*fact2);
        }
        public static double schaffer(double[] location){
            double x = location[0];
            double y = location[1];
            double fact1 = Math.pow((Math.sin(x*x-y*y)),2) - 0.5;
            double fact2 = Math.pow((1 + 0.001*(x*x+y*y)),2);
    
            return 0.5 + fact1/fact2;
            
        }
        public static double easom(double[] location){
            double x = location[0];
            double y = location[1];
            double fact1 = -Math.cos(x)*Math.cos(y);
            double fact2 = Math.exp(-(x-Math.PI)*(x-Math.PI)-(y-Math.PI) * (y-Math.PI));
    
            return fact1*fact2;
        }
        public static double levy(double[] location){
            double x = location[0];
            double y = location[1]; 
            double term1 = (Math.sin(3*Math.PI*x))*(Math.sin(3*Math.PI*x));
            double term2 = ((x-1)*(x-1)) * ((1+(Math.sin(3*Math.PI*y)))*(1+(Math.sin(3*Math.PI*y))));
            double term3 = ((y-1)*(y-1)) * ((1+(Math.sin(2*Math.PI*y)))*(1+(Math.sin(2*Math.PI*y))));
    
            return term1 + term2 + term3;
        }
}

