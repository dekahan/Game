import java.awt.*;

public class Ball  {

    private int x, y, vx, vy, diameter;
    private Color color;

    public Ball(int x, int y, int vx, int vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        diameter = 50;
        randomColor();
    }
    public Ball(int x, int y, int vx, int vy, int diam){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        diameter = diam;
        randomColor();
    }


    public void randomColor(){
        int r = (int)(Math.random()*256); //[0,255]
        int g = (int)(Math.random()*256); //[0,255]
        int b = (int)(Math.random()*256); //[0,255]
        color = new Color(r, g, b);
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillOval(x, y, diameter, diameter);
    }

    public void move(int w, int h){

        if( x <= 0 ) {  //left side bounce
            vx = -vx;
            x = 0;
            randomColor();
        }
        if( y <= 0) {  //top side bounce
            vy = -vy;
            y = 0;
            randomColor();
        }
        if( x + diameter >= w) {  //right side bounce
            vx = -vx;
            x = w - diameter;
            randomColor();
        }
        if( y + diameter >= h) {   //bottom side bounce
            vy = -vy;
            y = h - diameter;
            randomColor();
        }
        x += vx;
        y += vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

    public static int getRandFrom(int min, int max){

        int num = max - min + 1;
        return (int)(Math.random() * num + min);

    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public boolean intersects(Ball other){
        int d = (getX() - other.x)*(getX() - other.x) + (getY() - other.y)*(getY() - other.y);
        if (d < (diameter/2 + other.getDiameter()/2)*(diameter/2 + other.getDiameter()/2))
            return true;
        else
            return false;


    }

}