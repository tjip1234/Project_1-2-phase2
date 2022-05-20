package Bots;
import Math.*;

public class RandomBot implements Bots {
    public static void main(String[] args) throws InterruptedException {
         //long time = System.currentTimeMillis();
        //  HillClimb climber = new HillClimb();
         //climber.run();
        //System.out.println(System.currentTimeMillis() - time + " ms");

//          for (int i = 0; i < 16; i++) {
       //      ThreadDemo thread = new ThreadDemo( "Thread-" + i);
         //    thread.start();

        //  }

        // RNG.time = System.currentTimeMillis();


        
        

        
        //System.out.println(System.currentTimeMillis() - time + " ms");

        // doesn't seem to matter how many threads i have but 16 should work best?
        // no thread does 16k evals per second
        // 16 thread does 160k evals per second
    }


    @Override
    public State botrun() {
        for (int i = 0; i < 16; i++) {
            RandomThread thread = new RandomThread( "Thread-" + i);
            thread.start();
        }

        return new State(PhysicsEngine.x0,PhysicsEngine.y0,RandomThread.xv,RandomThread.yv);
    }

    @Override
    public void increaseAccuracy(int step) {

    }
}


