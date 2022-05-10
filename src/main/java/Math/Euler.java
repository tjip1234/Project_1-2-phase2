package Math;


import static Math.Main.friction;
import static Math.Main.gravity;

public class Euler implements OdeSolver{
    public State state = null;
    public double h;
    public Euler(State state, double h) {
        this.state = state;
        this.h = h;
    }
    @Override
    public State solver() {
            HelperFunctions helper = new HelperFunctions(gravity, friction);
            state.x += h * state.vx;
            state.y += h * state.vy;
            state.vx += h * helper.beginXCalculator(state.x,state.y,state.vx,state.vy);
            state.vy += h * helper.beginYCalculator(state.x,state.y,state.vx,state.vy);
        return state;
    }
}
