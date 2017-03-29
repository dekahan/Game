import java.awt.*;
import java.util.ArrayList;

public class SmallBalls extends Ball{
    public boolean novelocity;

    public SmallBalls(int x, int y){
        super(x, y, (int)(Math.random()*22-10), (int)(Math.random()*22-10));
        setDiameter(10);
        if(getVx() == 0 || getVy() == 0){
            novelocity = true;
        }
        if(novelocity == true){
            setVx((int)(Math.random()*22-10));
            setVy((int)(Math.random()*22-10));

        }
        else
            novelocity = false;
    }

}