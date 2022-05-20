package Math;

import Gui.GolfBall;
import Gui.GolfMap;

public class ConnectionGUI {
    public static void updateBall(double x, double y){
        GolfBall.X = x;
        GolfBall.Y = y;
        GolfBall.Z = mathFunction.Function(x,y);
        GolfBall.ball.translateXProperty().set(GolfBall.X);
        GolfBall.ball.translateYProperty().set(GolfBall.Y);
        GolfBall.ball.translateZProperty().set(GolfBall.Z + 0.45);
    }
}
