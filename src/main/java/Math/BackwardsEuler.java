package Math;

import static Math.Main.friction;
import static Math.Main.gravity;

public class BackwardsEuler implements OdeSolver {
    public State state = null;
    public double h;
    public BackwardsEuler(State state, double h){
        this.state = state;
        this.h = h;
    }
    @Override
    public State solver() {
        State estimateState = new Euler(state, h).solver();
        HelperFunctions helper = new HelperFunctions(gravity, friction);
        double vxn = state.vx + h * helper.beginXCalculator(estimateState.x, estimateState.y, estimateState.vx, estimateState.vy);
        double vyn = state.vy + h * helper.beginYCalculator(estimateState.x, estimateState.y, estimateState.vx, estimateState.vy);
        double xn = h * state.vx + state.vx;
        double yn = h * state.vy + state.vy;
        state = new State(xn,yn,vxn,vyn);
        return state;
    }
}
