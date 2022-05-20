package Bots;

public class RandomThread extends Thread{
        volatile int value;
        public static double xv;
        public static double yv;
        private Thread t;
        private String threadName;
        RandomThread( String name) {
            threadName = name;
        }
        @Override
        public void run() {
            RNG generator = new RNG();
            double[] rs =generator.run();
            xv = rs[0];
            yv = rs[1];
        }
        public void start () {
            if (t == null) {
               t = new Thread (this, threadName);
               t.start();
            }
         }
        
    }
    

