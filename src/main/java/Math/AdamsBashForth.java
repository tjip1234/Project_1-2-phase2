package Math;

import static Math.Main.friction;
import static Math.Main.gravity;

public class AdamsBashForth implements OdeSolver {
    State[] previousStates;
    double h;
    public AdamsBashForth(State[] previousStates, double h){
        this.previousStates = previousStates;
        this.h = h;
    }
    @Override
    public State solver() {
        HelperFunctions helper = new HelperFunctions(gravity, friction);
        previousStates[3].vx = previousStates[2].vx + (h / 12) * 23 * helper.beginXCalculator(previousStates[2].x, previousStates[2].y, previousStates[2].vx, previousStates[2].vy) - 16 * helper.beginXCalculator(previousStates[1].x, previousStates[1].y, previousStates[1].vx, previousStates[1].vy) + 5 * helper.beginXCalculator(previousStates[0].x, previousStates[0].y, previousStates[0].vx, previousStates[0].vy);
        previousStates[3].vy = previousStates[2].vy + (h / 12) * 23 * helper.beginYCalculator(previousStates[2].x, previousStates[2].y, previousStates[2].vx, previousStates[2].vy) - 16 * helper.beginYCalculator(previousStates[1].x, previousStates[1].y, previousStates[1].vx, previousStates[1].vy) + 5 * helper.beginYCalculator(previousStates[0].x, previousStates[0].y, previousStates[0].vx, previousStates[0].vy);
        previousStates[3].x = previousStates[2].x + h * previousStates[3].vx;
        previousStates[3].y = previousStates[2].y + h * previousStates[3].vy;
        State n[] = previousStates.clone();
        for (int i = 0; i < previousStates.length-1; i++) {
            previousStates[i] = n[i+1];
        }
        return previousStates[2];
    }
}
