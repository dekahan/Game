import java.awt.*;

public class Ball  {

    private int vx, vy, diameter;
    private double x, y;
    private Color color;

    public Ball(double x, double y, int vx, int vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        diameter = 50;
        randomColor();
    }

    public void randomColor(){
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        color = new Color(r, g, b);
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillOval((int)x, (int)y, diameter, diameter);
    }

    public void move(int w, int h){

        if( x <= 0 ) {
            vx = -vx;
            x = 0;
            randomColor();
        }
        if( y <= 0) {
            vy = -vy;
            y = 0;
            randomColor();
        }
        if( x + diameter >= w) {
            vx = -vx;
            x = w - diameter;
            randomColor();
        }
        if( y + diameter >= h) {
            vy = -vy;
            y = h - diameter;
            randomColor();
        }
        x += vx;
        y += vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setVx(int newVx){
        vx = newVx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public Point getPoint(){
        return new Point((int)(x+getDiameter()/2), (int)( y+getDiameter()/2));
    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public boolean intersects(Ball other){
        double d = getPoint().distance(other.getPoint());
        return d < (diameter / 2 + other.getDiameter() / 2);


    }

}