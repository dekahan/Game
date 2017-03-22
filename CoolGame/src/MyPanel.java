import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyPanel extends JPanel{

    private Timer timer;
    private Ball nBall;
    private ArrayList<Ball> theBalls;

    public MyPanel(int w0, int h0){
        setSize(w0,h0);;


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
//                theBalls.add(new NewBall(x, y));
                nBall = new NewBall(x,y);


            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        theBalls = new ArrayList<Ball>();
        for (int i = 0; i < 10; i++) {

//            theBalls.add(makeRandomBall());
            theBalls.add(new SmallBalls(375, 375, (int)(Math.random()*15), (int)(Math.random()*15)));
        }
        timer = new Timer(20, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {


                if(nBall != null) {
                    nBall.move(getWidth(), getHeight());
                }
                for (int i = 0; i < theBalls.size(); i++) {
                    Ball b = theBalls.get(i);
                    b.move(getWidth(), getHeight());
                    if (nBall != null && nBall.intersects(b)) {
                        b.setDiameter(0);
                        NewBall replacement = new NewBall(b.getX(), b.getY());
                        theBalls.set(i, replacement);
                    }


                }


                repaint();
            }
        });
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);  //gets rid of that trail effect.
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(0, 0, getWidth(), getHeight());
        for(Ball b: theBalls)
            b.draw(g2);
        nBall.draw(g2);
    }

    public Ball makeRandomBall(){
//        int x = (int) (Math.random() * getWidth() - 50);
//        int y = (int) (Math.random() * getHeight() - 50);

        int x= getWidth()/2-25;
        int y= getHeight()/2-25;
        int vx = (int) (Math.random() * 21 - 10);
//        if(vx == 0){
//            vx = (int) (Math.random() * 21 - 10);
//        }
        int vy = (int) (Math.random() * 21 - 10);
//        if(vy == 0){
//            vy = (int) (Math.random() * 21 - 10);
//        }
        Ball temp = new SmallBalls(x, y, vx, vy);
        return temp;
    }
}