import java.awt.*;
import java.util.ArrayList;

public class SmallBalls extends Ball{
    public boolean novelocity;

    public SmallBalls(int x, int y, int vx, int vy){
        super(x, y, (int)(Math.random()*15-10), (int)(Math.random()*15-10));
        setDiameter(10);
        if(vx == 0 || vy == 0){
            novelocity = true;
        }
        if(novelocity == true){

        }
    }

}