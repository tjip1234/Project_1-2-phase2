package Math;

import static Math.Main.friction;
import static Math.Main.gravity;

public class RungeKutta4 implements OdeSolver{
    State state;

    double h;
    public RungeKutta4(State state, double h){
        this.state = state;
        this.h = h;
    }

    @Override

    public State solver() {
        HelperFunctions helper = new HelperFunctions(gravity, friction);
        Derivative a, b, c, d = new Derivative(0, 0, 0, 0);
        a = helper.evaluate(state, h, new Derivative(0, 0, 0, 0)); // TODO do i do deriv 0
        b = helper.evaluate(state, h * 0.5, a);
        c = helper.evaluate(state, h * 0.5, b);
        d = helper.evaluate(state, h, c);

        double dxdt = 1.0 / 6.0 * (a.dx + 2.0 * (b.dx + c.dx) + d.dx);
        double dydt = 1.0 / 6.0 * (a.dy + 2.0 * (b.dy + c.dy) + d.dy);

        double dvXdt = 1.0 / 6.0 * (a.dvx + 2.0 * (b.dvx + c.dvx) + d.dvx);
        double dvYdt = 1.0 / 6.0 * (a.dvy + 2.0 * (b.dvy + c.dvy) + d.dvy);

        state.x = state.x + dxdt * h;
        state.y = state.y + dydt * h;

        state.vx = state.vx + dvXdt * h;
        state.vy = state.vy + dvYdt * h;
        return state;
    }
}
