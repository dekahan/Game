import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel{

    private Timer timer;
    private ArrayList<Ball> theBalls;

    public MyPanel(int w0, int h0){
        setSize(w0,h0);

        theBalls = new ArrayList<Ball>();
        for (int i = 0; i < 8; i++)
        theBalls.add(new SmallBalls(300, 300, 0));

        timer = new Timer(20, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                for(Ball b: theBalls)
                    b.move(getWidth(), getHeight());
                repaint();
            }
        });
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);  //gets rid of that trail effect.
        Graphics2D g2 = (Graphics2D)g;

        for(Ball b: theBalls)
            b.draw(g2);
    }

    public Ball makeRandomBall(){
//        int x = (int) (Math.random() * getWidth() - 50);
//        int y = (int) (Math.random() * getHeight() - 50);

        int x= getWidth()/2-25;
        int y= getHeight()/2-25;
        int vx = (int) (Math.random() * 21 - 10);
        int vy = (int) (Math.random() * 21 - 10);
        Ball temp = new Ball(x, y, vx, vy);
        return temp;
    }
}