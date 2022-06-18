package Bots;

import java.util.Arrays;
import Math.*;

import static Bots.Particle.groupBest;

/**
 * ParticleSwarm
 */
public class ParticleSwarm implements Bots{
   // public static UI ui = new UI(30, 30, 30);
    // 0.3925 2.5586 1.3358 best suited for 2 dimensional low fitness eval functions
    
    public static final int population = 500;
    public static final int maxGeneration = 70;
    public static final int dimensionality = 2;
    //System.out.println(bestEgg.fitness + ", " + t + ", cuckoo"); use this for research
    public static void main(String[] args) {
       // System.out.println(levy(new double[]{1, 1}));
        ParticleSwarm p = new ParticleSwarm();
        
        p.botrun();
        //System.out.println(Particle.fitnessEvaluations); 
        // CuckooSearch cuckoo = new CuckooSearch();
        // cuckoo.run();
        // DifferentialEvolution evo = new DifferentialEvolution();
        // evo.run();
        
    }


    @Override
    public void increaseAccuracy(int step) {

    }
    @Override
    public State botrun(){
        int t = 0;
        //initializing population of particles
        Particle[] particleArray = generatePartArray();
        // finding best particle
        groupBest = findBestParticle(particleArray);
        Particle.bestFitness = calcFitness(groupBest);
        double[][] coords = new double[particleArray.length][particleArray[0].location.length];
        //System.out.println(Arrays.toString(Particle.groupBest) + " fitness: " + Particle.bestFitness);
        
        while (t < maxGeneration) {
            
            for (int i = 0; i < coords.length; i++) {
                for (int j = 0; j < coords[i].length; j++) {
                    coords[i][j] = particleArray[i].location[j];
                }
            }

            //ui.setState(coords, groupBest);
            //sleep(50);

            particleArray = updateParticleArray(particleArray);
            double[] currentBest = findBestParticle(particleArray);
            if (calcFitness(currentBest) < Particle.bestFitness) {
                Particle.bestFitness = calcFitness(currentBest);   //TODO optimize this, don't measure it twice
                groupBest = currentBest;
            }

            if (calcFitness(groupBest) < PhysicsEngine.r){
            //System.out.println(Arrays.toString(Particle.groupBest) + " fitness: " + Particle.bestFitness + " particleSwarm");
                return new State(PhysicsEngine.x0,PhysicsEngine.y0, groupBest[0],groupBest[1]);
            }
            //TODO STOP SEARCHING FOR BEST GROUP BEST LIKE IT'S AN EGG U HAVE TO FIND GROUP BEST AND THEN COMPARE IT TO OLD GROUP BEST YOU DONKEY
            //System.out.println(Particle.bestFitness + ", " + t + ", ParticleSwarm");
            Particle.bestFitness = calcFitness(groupBest);
            //System.out.println(Arrays.toString(groupBest) + " fitness: " + Particle.bestFitness);
            t++;

                                                  
        }
        //System.out.println(Particle.fitnessEvaluations + " swarmy");
        //System.out.println(Particle.bestFitness + ", " + t + ", ParticleSwarm");
        //System.out.println(Arrays.toString(Particle.groupBest) + " fitness: " + Particle.bestFitness + " particleSwarm");
        return new State(PhysicsEngine.x0, PhysicsEngine.y0,Particle.groupBest[0],Particle.groupBest[1]); //TODO ASk tin if this is okay?
        
        
    }
    public Particle[] generatePartArray(){
        Particle[] particleArray = new Particle[population];
        for (int i = 0; i < particleArray.length; i++) {
            particleArray[i] = new Particle();
            particleArray[i].checkBoundary();            
        }
        return particleArray;                    
    }

    public double[] findBestParticle(Particle[] particleArray){
        Particle bestParticle = particleArray[0];
        for (int i = 1; i < particleArray.length; i++) {
            if (particleArray[i].fitness < bestParticle.fitness) { //TODO minimization problem!
                bestParticle = particleArray[i];                
            }           
        }
        return bestParticle.location;
    }

    public Particle[] updateParticleArray(Particle[] particleArray){
        for (int i = 0; i < particleArray.length; i++) {
            particleArray[i].updateParticle();            
        }
        return particleArray;
    }
    public double calcFitness(double[] location){
        double vx = location[0];
        double vy = location[1];
        PhysicsEngine engine = new PhysicsEngine(Main.h);
        OdeSolver solver = new RungeKutta2(new State(PhysicsEngine.x0,PhysicsEngine.y0,vx,vy) , Main.h);
        double fitness = engine.run(solver,new State(PhysicsEngine.x0,PhysicsEngine.y0,vx,vy));
        return fitness;
    }
    private static double mccormick(double[] x) {
        double a = x[0];
        double b = x[1];
        return Math.sin(a + b) + (a - b) * (a - b) + 1.0 + 2.5 * b - 1.5 * a;
    }
    // taken from rosetta stone
 
    private static double michalewicz2(double[] x) {
        int m = 10;
        int d = x.length;
        double sum = 0.0;
        for (int i = 1; i < d; ++i) {
            double j = x[i - 1];
            double k = Math.sin(i * j * j / Math.PI);
            sum += Math.sin(j) * Math.pow(k, 2.0 * m);
        }
        return -sum;
    }

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
    public void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
    }
    public double dropWave(double[] location){
        double x = location[0];
        double y = location[1];
        double upper = 1 + Math.cos(12 * Math.sqrt(x*x + y*y));
        double lower = 0.5 * (x*x + y*y) + 2;
        return -upper / lower;
    }

    public static double eggHolder(double[] location){
        double x = location[0];
        double y = location[1];
        return -(y + 47) * Math.sin(Math.sqrt(Math.abs(y + x / 2 + 47))) - x * Math.sin(Math.sqrt(Math.abs(x - (y + 47))));
    }
    public static double bukin(double[] location){
        double x = location[0];
        double y = location[1];
        return 100 * Math.sqrt(Math.abs(y - 0.01 * x * x)) + 0.01 * Math.abs(x + 10);
    }
    public static double crossInTray(double[] location){
        double x = location[0];
        double y = location[1];
        double fact1 = Math.sin(x)*Math.sin(y);
        double fact2 = Math.exp(Math.abs(100 - Math.sqrt(x*x+y*y)/Math.PI));

        return -0.0001 * Math.pow((Math.abs(fact1*fact2)+1),0.1);
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

