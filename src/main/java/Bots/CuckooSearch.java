package Bots;

import java.util.Arrays;
import java.util.Random;
import Math.*;
import org.apache.commons.math3.special.Gamma;
public class CuckooSearch implements Bots{
    public static final int dimensionality = 2;
    public static int N = 5; // number of nests hyperparameter 15-40 generally
    public static final double pa = 0.25; // switching probability hyperparameter
    public static double maxGeneration = 100; // max gen parameter
    public static final double alpha = 0.01; // step size scaling hyperparameter / could be 1?
    public static final double lambda = 1.5;// levy exponent hyperparameter some weird stuff herejk
    public static double fitness;
    public Egg bestEgg = new Egg();
    
    //TODO  store fitness of each somehow so u dont have to recalculate like an idiot

    @Override
    public State botrun(){
        boolean stop = false;
        double t = 0;
        // generating nests and cuckoo eggs
        Egg[] nestArray = generateNests();
        while (t < maxGeneration || stop) {
            bestEgg = findBestEgg(nestArray); //TODO optimize finding best at some point
            System.out.println(bestEgg.fitness + ", " + t + ", cuckoo");
            Egg[] cuckooArray = generateCuckoos(nestArray);
            nestArray = generateNewNests(cuckooArray);
            //prints
        
            t++;
        }
        bestEgg = findBestEgg(nestArray);
        System.out.println(Arrays.toString(bestEgg.location));
        System.out.println("fitness: " + bestEgg.fitness);
        return new State(PhysicsEngine.x0,PhysicsEngine.y0,bestEgg.location[0],bestEgg.location[1]);
    }
    public void increaseAccuracy(int step){
        maxGeneration = maxGeneration * step;
        N = N * step;
    }
    public static void main(String[] args) {
        CuckooSearch c = new CuckooSearch();
        c.botrun();
    }
    // HELPER METHODS
    public double[] gaussianArray(int dimensionality){
        Random random = new Random();
        double[] gaussArray = new double[dimensionality];
        for (int i = 0; i < gaussArray.length; i++) {
            gaussArray[i] = random.nextGaussian();          
        }
        return gaussArray;
    }
    
    public Egg findBestEgg(Egg[] nestArray){
        Egg bestEgg = nestArray[0];
        for (int i = 1; i < nestArray.length; i++) {
            if (nestArray[i].fitness < bestEgg.fitness) { //TODO minimization problem!
                bestEgg = nestArray[i];                
            }           
        }
        return bestEgg;
    }
    public Egg[] generateNewNests(Egg[] cuckooArray){
        for (int i = 0; i < cuckooArray.length; i++) {
            double[] newArray = new double[dimensionality];
            int randInt1 = (int) (Math.random() * N);
            int randInt2 = (int) (Math.random() * N);
            for (int j = 0; j < dimensionality; j++) {
                if (Math.random() < 0.25) {
                    double newX = cuckooArray[i].location[j] + Math.random() * (cuckooArray[randInt1].location[j] - cuckooArray[randInt2].location[j]);
                    newArray[j] = newX;                    
                }
                else{
                    newArray[j] = cuckooArray[i].location[j];
                }             
            }
            Egg newEgg = new Egg(newArray);
            if (newEgg.fitness < cuckooArray[i].fitness) {
                cuckooArray[i] = newEgg;                
            }            
        }
        return cuckooArray;
    }






    public Egg[] generateCuckoos(Egg[] nestArray) {
        double sigma = computeSigma();
        Random random = new Random();
        
        Egg[] cuckooArray = new Egg[nestArray.length];
        for (int i = 0; i < nestArray.length; i++) {
            Egg subtract = eggSubtraction(nestArray[i], bestEgg);
            double[] u = constantTimesVector(sigma, gaussianArray(dimensionality));
            double[] v = gaussianArray(dimensionality);
            double[] sLocation = constantTimesVector(1.0 / (Math.pow(absVector(v), 1 / lambda)), u);
            Egg s = new Egg(sLocation);
            Egg sTimesSubtract = eggMultiplication(subtract, s);
            Egg product = scalarTimesEgg(random.nextGaussian() * alpha, sTimesSubtract); //TODO is alpha here?
            cuckooArray[i] = eggAddition(nestArray[i], product);                     
        }
        //TODO optimize this part
        for (int i = 0; i < cuckooArray.length; i++) {
            if (nestArray[i].fitness < cuckooArray[i].fitness) {
                cuckooArray[i] = nestArray[i];               
            }            
        }
        return cuckooArray;
    }




    // HELPER MATH
    public double computeSigma(){
        double numerator = Gamma.gamma(1.0 + lambda) * Math.sin((Math.PI * lambda) / 2.0);
        double denominator = Gamma.gamma((1.0 + lambda) / 2.0) * lambda * Math.pow(2.0, (lambda-1.0)/2.0);
        double fraction = numerator / denominator;
        double sigma = Math.pow(fraction, 1.0 / lambda);
        return sigma;
    }

    public double absVector(double[] vector){
        double quadSum = 0.0;
        for (int i = 0; i < vector.length; i++) {
            quadSum += vector[i] * vector[i];            
        }
        return Math.sqrt(quadSum);
    }

    public Egg eggAddition(Egg egg1, Egg egg2){
        return new Egg(add2Vectors(egg1.location, egg2.location)); 
    }
    public Egg scalarTimesEgg(double scalar, Egg egg2){
        return new Egg(constantTimesVector(scalar, egg2.location)); 
    }
    public Egg eggSubtraction(Egg egg1, Egg egg2){
        return new Egg(subtract2Vectors(egg1.location, egg2.location));
    }
    private Egg eggMultiplication(Egg subtract, Egg s) {
        Egg resultEgg = new Egg();
        for (int i = 0; i < resultEgg.location.length; i++) {
            resultEgg.location[i] = subtract.location[i] * s.location[i];          
        }
        return resultEgg;
    }
    


    public Egg[] generateNests(){
        Egg[] nestArray = new Egg[N];
        for (int i = 0; i < N; i++) {
            nestArray[i] = new Egg();        
        }
        return nestArray;
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
    public double[] generateBaboon(double dimensionality){
        return null; //TODO generate with levy flights
    }
    // vector helpers
    public double[] subtract2Vectors(double[] vector1, double[] vector2){
        double[] sumVector = new double[2];
        for( int i = 0; i < sumVector.length; i++){
            sumVector[i] = vector1[i] - vector2[i];
        }
        return sumVector;
    }
    public double[] add2Vectors(double[] vector1, double[] vector2){
        double[] sumVector = new double[2];
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
        double[] resultVector = new double[2];
        for( int i = 0; i < resultVector.length; i++){
            resultVector[i] = constant * vector[i];
        }
        return resultVector; 
    }



}
