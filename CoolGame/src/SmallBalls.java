import java.awt.*;
import java.util.ArrayList;

public class SmallBalls extends Ball{

    public SmallBalls(int x, int y, int dir){
        super(x, y, (int)(Math.random()*21-10), (int)(Math.random()*21-10));
        setDiameter(10);
    }
}