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
        frameCount ++;
        if (frameCount < 100) {
            setDiameter(getDiameter() + 1);
            setX(getX() - 0.5);
            setY(getY() - 0.5);
        }
        if (frameCount >= 100 && frameCount < 150 ) {
            setDiameter(getDiameter());
        }
        if (frameCount >= 150)
            setDiameter(0);
    }
}