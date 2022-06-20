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
        double xThing;
        xThing=beginXCalculatorPhase3(x, y, vx, vy);

        /* changed equations no needfor that
        if (vx == 0 && vy == 0) {
            xThing = (-gravity*derivativeOf(x,y,'x')) - (friction*gravity*derivativeOf(x,y,'x'))/(Math.sqrt(Math.pow(derivativeOf(x,y,'x'), 2) + Math.pow(derivativeOf(x,y,'y'), 2)));

        }
        else{
            xThing = (-gravity*derivativeOf(x,y,'x')) - (friction*gravity*vx)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        }
        return xThing;
        */
        return xThing;
    }
    public double accelerationY(State state){
        double x = state.x;
        double y = state.y;
        double vx = state.vx;
        double vy = state.vy;
        double yThing;
        yThing=beginYCalculatorPhase3(x, y, vx, vy);
        /* changed equations no need for that
        if (vx == 0 && vy == 0) {
            yThing = (-gravity*derivativeOf(x,y,'y')) - (friction*gravity*derivativeOf(x,y,'y'))/(Math.sqrt(Math.pow(derivativeOf(x,y,'x'), 2) + Math.pow(derivativeOf(x,y,'y'), 2)));

        }
        else{
            yThing = (-gravity*derivativeOf(x,y,'y')) - (friction*gravity*vy)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        }
        return yThing;
        */
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

    public double beginXCalculatorPhase3(double x, double y, double vx, double vy){
        double divisionCommonPart=(1+Math.pow(derivativeOf(x,y,'x'), 2)+Math.pow(derivativeOf(x,y,'y'), 2));
        double Part1 = -(gravity*Main.mass*derivativeOf(x,y,'x'))/(divisionCommonPart);
        double dAndVelocityMult = derivativeOf(x, y, 'x')*vx + derivativeOf(x, y, 'y')*vy;
        double squarerootcomponent;
        double Part2;
        if(vx==0 && vy==0){
            Part2 = 0;
        }else{
            squarerootcomponent = Math.pow(vx, 2)+Math.pow(vy, 2)+Math.pow(dAndVelocityMult, 2);
            Part2 =(friction*gravity*Main.mass*vx)/(Math.sqrt(divisionCommonPart)*Math.sqrt(squarerootcomponent));
        }
        double XThing = (Part1 - Part2)/Main.mass;
        return XThing;
    }
    public double beginYCalculatorPhase3(double x, double y, double vx, double vy){
        double divisionCommonPart=(1+Math.pow(derivativeOf(x,y,'x'), 2)+Math.pow(derivativeOf(x,y,'y'), 2));
        double Part1 = -(gravity*Main.mass*derivativeOf(x,y,'y'))/(divisionCommonPart);
        double dAndVelocityMult = derivativeOf(x, y, 'x')*vx + derivativeOf(x, y, 'y')*vy;
        double squarerootcomponent;
        double Part2;
        if(vx==0 && vy==0){
            Part2 =0;
        }else{
            squarerootcomponent  = Math.pow(vx, 2)+Math.pow(vy, 2)+Math.pow(dAndVelocityMult, 2);
            Part2 = (friction*gravity*Main.mass*vy)/(Math.sqrt(divisionCommonPart)*Math.sqrt(squarerootcomponent));
        }
        double YThing = (Part1 - Part2)/Main.mass;
        return YThing;
    }
}
