package Math;

public class ImprovedEuler implements OdeSolver{
    public State state = null;
    public double h;
    public ImprovedEuler(State state, double h) {
        this.state = state;
        this.h = h;
    }
    @Override
    public State solver() {
        HelperFunctions helper = new HelperFunctions(Main.gravity, Main.friction);
        State currentState = new State(state.x, state.y, state.vx, state.vy);
        State nextState = newState(currentState, h);
        currentState.x = currentState.x + h*((currentState.vx + nextState.vx) / 2.0);
        currentState.y = currentState.y + h*((currentState.vy + nextState.vy) / 2.0);
        currentState.vx = currentState.vx + h*((helper.beginXCalculator(currentState.x, currentState.y, currentState.vx, currentState.vy) + helper.beginXCalculator(nextState.x, nextState.y, nextState.vx, nextState.vy)) / 2.0);
        currentState.vy = currentState.vy + h*((helper.beginYCalculator(currentState.x, currentState.y, currentState.vx, currentState.vy) + helper.beginYCalculator(nextState.x, nextState.y, nextState.vx, nextState.vy)) / 2.0);
        state = currentState;
        return currentState;
    }
    public  State newState(State currentStateVector, double h){
        HelperFunctions helper = new HelperFunctions(Main.gravity, Main.friction);
        double x = currentStateVector.x + h * currentStateVector.vx;
        double y = currentStateVector.y + h * currentStateVector.vy;
        double vx = currentStateVector.vx + h * helper.beginXCalculator(currentStateVector.x, currentStateVector.y, currentStateVector.vx, currentStateVector.vy);
        double vy = currentStateVector.vy + h * helper.beginYCalculator(currentStateVector.y, currentStateVector.y, currentStateVector.vx, currentStateVector.vy);
        State newState = new State(x, y, vx, vy);
        return newState;
    }
}
