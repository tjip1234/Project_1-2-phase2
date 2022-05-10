import Math.State;
public class Ball {
    State state = null;
    double Distance;
    public Ball(double x, double y, double xv, double yv){
        this.state = new State(x,y,xv,yv);
    }
    public State getState(){
        return state;
    }
    public void updateState(State state){
        this.state = state;
    }
    public void calculateDistance(){

    }

}
