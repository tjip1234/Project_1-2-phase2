package Math;

import static Math.Main.friction;
import static Math.Main.gravity;

public class semImplicitEuler implements OdeSolver{
    public State state = null;
    public double h;
    public semImplicitEuler(State state, double h) {
        this.state = state;
        this.h = h;
    }
    @Override
    public State solver() {
        HelperFunctions helper = new HelperFunctions(gravity, friction);
        state.vx += h * helper.beginXCalculator(state.x,state.y,state.vx,state.vy);
        state.vy += h * helper.beginYCalculator(state.x,state.y,state.vx,state.vy);
        state.x += h * state.vx;
        state.y += h * state.vy;
        return state;
    }
}
