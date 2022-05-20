package Math;
import java.util.*;
public class mathFunction {
public static double Function(double x, double y){
 return 0.4*(0.9 - Math.exp(-(x*x + y*y)/8.0d));
 //return 0.1*x+1.0;
 //return 2*x+1.0;
}
}
