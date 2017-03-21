/**
 * Created by danielle_kahan on 3/16/17.
 */
public class NewBall extends Ball {

    private int frameCount;
    private int diam;


    public NewBall(int x, int y) {
        super(x, y, 0, 0);
        diam = 1;
        setDiameter(diam);
    }

    @Override
    public void move(int w, int h) {
        //super.move(w, h);
        setDiameter(diam += 1);
        frameCount ++;
        if (frameCount > 300)
            setDiameter(0);


    }
}