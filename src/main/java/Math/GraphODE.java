package Math;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



public class GraphODE {
    public static void write() {

        int numberOdes = 5;
        double h = Main.h;
        State[][] arrays = new State[numberOdes][0];
        PhysicsEngine engine;
        State state = new State(0,0,4,2);
        OdeSolver[] odes = new OdeSolver[numberOdes];
        odes[0] = new Euler(new State(PhysicsEngine.x0,PhysicsEngine.y0,4 ,2),h);
        odes[1] = new BackwardsEuler(new State(PhysicsEngine.x0,PhysicsEngine.y0,4,2), h);
        odes[2] = new RungeKutta2(new State(PhysicsEngine.x0,PhysicsEngine.y0,4,2), h);
        odes[3] = new RungeKutta4(new State(PhysicsEngine.x0,PhysicsEngine.y0,4,2), h);
        odes[4] = new semImplicitEuler(new State(PhysicsEngine.x0,PhysicsEngine.y0,4,2), h);
        for (int i = 0; i < numberOdes; i++) {
            state = new State(PhysicsEngine.x0,PhysicsEngine.y0,4,2);
            engine = new PhysicsEngine(h);
            PhysicsEngine.observe = true;
            engine.observed = new LinkedList<State>();
            engine.run(odes[i], state);
            arrays[i] = engine.observed.toArray(new State[engine.observed.size()]);
        }
        System.out.println(Arrays.deepToString(arrays));
        List<String> list = new LinkedList<String>();
        double hk = 0;
        for (int k = 0; k < arrays[0].length; k++) {
            String rs = "";
            for (int i = 0; i < arrays.length; i++) {
                try {
                    rs = rs + " " + arrays[i][k].x +";"+arrays[i][k].y + ", ";
                }
                catch(Exception e){
                    rs = rs + "null" + ", ";
                }
            }
            rs = hk + " ," + rs;
            list.add(rs);
            hk += h;
        }

        try {
            Files.write(Paths.get(System.getProperty("user.dir"), "out.csv"), list, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        write();
    }
}
