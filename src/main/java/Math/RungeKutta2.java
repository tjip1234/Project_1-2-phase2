package Math;

import static Math.Main.friction;
import static Math.Main.gravity;

public class RungeKutta2 implements OdeSolver{
    State state;

    double h;
    public RungeKutta2(State state, double h){
        this.state = state;
        this.h = h;
    }
    @Override
    public State solver() {
        HelperFunctions helper = new HelperFunctions(gravity, friction);
        Derivative a,b,c,d = new Derivative(0, 0, 0, 0);
        a = helper.evaluate( state, 0.0, new Derivative(0, 0, 0, 0) ); // TODO do i do deriv 0
        b = helper.evaluate( state, h*0.5, a );
        double dxdt = b.dx;
        double dydt = b.dy;

        double dvXdt = b.dvx;
        double dvYdt = b.dvy;

        state.x = state.x + dxdt * h;
        state.y = state.y + dydt * h;

        state.vx = state.vx + dvXdt * h;
        state.vy = state.vy + dvYdt * h;
        return state;
    }
}
