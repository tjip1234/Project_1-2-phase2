package Math;

import java.io.FileNotFoundException;


public class Main {
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static void main(String[] args) throws FileNotFoundException {
        
        
        //Math.PhysicsEngine.EulersMethod(0.01, 0, 0, 2, 0, 0.056, 0.05);
        //Math.PhysicsEngine.SemiImplicitEulerMethod(0.01, 0, 0, 2, 0, 0.056, 0.05);
        //PrintWriter writer = new PrintWriter("exp.csv");
        //Math.PhysicsEngine.rungeKutta4(0, 0, 2, 0);
        //p.EulersMethod(stepsize, 0, 0, 2, 0, 0.056, 0.05);
        // one more 0 after i += for 100k
        
        //l.rungeKutta2(0.000000001, 0, 0, 2, 0);

        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        PhysicsEngine d = new PhysicsEngine(0.001);
        System.out.println("Value, " + "Stepsize, " + "Odesolver");
        long time = System.currentTimeMillis();
        //System.out.println(d.rungeKutta4(d.x0, d.y0, 3.3768969127158854, 1.3135526464700689));
        //double i = 0.0177827941; i< 0.10000000; i+=0.00000822172059
        //System.out.println(d.NonOscilaiting(d.x0, d.y0,3.3768969127158854, 1.3135526464700689) );
        
        
        


    }  
    
}
