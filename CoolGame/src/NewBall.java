/**
 * Created by danielle_kahan on 3/16/17.
 */
public class NewBall extends Ball {

    private int frameCount;


    public NewBall(int x, int y) {
        super(x, y, 0, 0);
        int diam = 1;
        setDiameter(diam);
    }

    @Override
    public void move(int w, int h) {
        //super.move(w, h);
        setDiameter(getDiameter() + 1);
        setX(getX() - 0.5);
        setY(getY() - 0.5);
        frameCount ++;
        if (frameCount > 125) {
            setDiameter(0);
        }





    }
}