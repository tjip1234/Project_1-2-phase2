import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Math.Derivative;
public class test_Math {
    @Test
    public void derivativetest(){
        Derivative n = new Derivative(3,4,5,6);
        n.divideDer(4.7);
        assertEquals(3.0/4.7,n.dx);
        assertEquals(4.0/4.7,n.dy);
        assertEquals(5.0/4.7,n.dvx);
        assertEquals(6.0/4.7,n.dvy);
    }


}
