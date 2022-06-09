package Math;

public class Derivative {
    public double dx;
    public double dvx;
    public double dy;
    public double dvy;
    public Derivative(double dx, double dy, double dvx, double dvy){
        this.dx = dx;
        this.dvx = dvx;
        this.dy = dy;
        this.dvy = dvy;
    }
    public Derivative getDerivative(){
        double h = 0.00001;
        double newdx = mathFunction.Function(dx+h,dy)/h;
        double newdy = mathFunction.Function(dx,dy+h)/h;
       return new Derivative(newdx,newdy,dvx,dvy);
    }
    public Derivative divideDer(double divider){
        dx = dx / divider;
        dy = dy / divider;
        dvx = dvx / divider;
        dvy = dvy / divider;
        return new Derivative(dx, dy, dvx, dvy);
    }    
}
