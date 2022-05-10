package Math;
import java.util.*;
public class mathFunction {
public static double Function(double x, double y){
 //return 0.05*(x*x + y*y);
 //return 0.1*x+1.0;
 return 0.4d*(0.9d - Math.exp( - (x * x + y * y) / 8.0d) );
}
}
