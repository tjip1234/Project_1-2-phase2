package Math;

public class HelperFunctions {
    double gravity = 0;
    double friction = 0;
    public HelperFunctions(double gravity,double friction){
        this.gravity = gravity;
        this.friction = friction;
    }
    public Derivative evaluate(State initial, double h, Derivative d){
        State state = new State(0, 0, 0, 0);
        state.x = initial.x + d.dx*h;
        state.vx = initial.vx + d.dvx*h;

        state.y = initial.y + d.dy*h;
        state.vy = initial.vy + d.dvy*h;

        Derivative output = new Derivative(0, 0, 0, 0);
        output.dx = state.vx;
        output.dvx = accelerationX(state);

        output.dy = state.vy;
        output.dvy = accelerationY(state);
        return output;
    }

    public double accelerationX(State state){
        double x = state.x;
        double y = state.y;
        double vx = state.vx;
        double vy = state.vy;

        double xThing = (-gravity*derivativeOf(x,y,'x')) - (friction*gravity*vx)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        return xThing;
    }
    public double accelerationY(State state){
        double x = state.x;
        double y = state.y;
        double vx = state.vx;
        double vy = state.vy;

        double yThing = (-gravity*derivativeOf(x,y,'y')) - (friction*gravity*vy)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        return yThing;
    }
    public double derivativeOf(double x, double y, char withrespectto){
        double derivativestep = 1E-6;
        if (withrespectto == 'x'){
            return (mathFunction.Function(x+derivativestep, y)-mathFunction.Function(x, y)) / derivativestep;
        }else{
            return (mathFunction.Function(x, y+derivativestep)-mathFunction.Function(x, y)) / derivativestep;
        }
    }
    public double beginXCalculator(double x, double y, double vx, double vy){
        double xThing = (-gravity*derivativeOf(x,y,'x')) - (friction*gravity*vx)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        return xThing;
    }

    public double beginYCalculator(double positionX, double positionY, double velocityX,  double velocityY){
        double yThing = (-gravity*derivativeOf(positionX,positionY,'y')) - (friction*gravity*velocityY)/(Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
        return yThing;
    }
}
