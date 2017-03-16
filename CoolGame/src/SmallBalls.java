import java.awt.*;
import java.util.ArrayList;

public class SmallBalls extends Ball{

    public SmallBalls(int x, int y, int vx, int vy){
        super(x, y, (int)(Math.random()*15-10), (int)(Math.random()*15-10));
        setDiameter(10);
    }
}