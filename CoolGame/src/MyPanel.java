import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyPanel extends JPanel{

    private Timer timer;
    private ArrayList<Ball> theBalls;
    private ArrayList<Ball> newBalls;
    private int level;
    private int reqhits;
    private boolean levelup;
    private int numhits;
    private int s;
    private boolean gameover;

    public MyPanel(int w0, int h0) {
        setSize(w0, h0);
        level = 1;
        reqhits = 3;
        levelup = false;
        gameover = false;
        numhits = 0;
        s = 0;


        newBalls = new ArrayList<Ball>();


        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                s++;

                if (s <= 1) {
                    int x = mouseEvent.getX();
                    int y = mouseEvent.getY();
                    newBalls.add(new NewBall(x, y));
                }
                if (levelup == true && mouseEvent.getX() > 400 && mouseEvent.getX()< 500 && mouseEvent.getY() > 405 && mouseEvent.getY() < 455 ){
                   timer.start();
                    level++;
                    numhits = 0;
                    reqhits += level*2;
                    for (int i = 0; i < newBalls.size() ; i++) {
                        newBalls.remove(i);
                        i--;
                    }
                    s = 0;
                    levelup = false;


                }
                if(s >= 2 && newBalls.size() == 0) {
                    gameover = true;
                }
                if (gameover == true && mouseEvent.getX() >400 && mouseEvent.getX()< 500 && mouseEvent.getY() > 405 && mouseEvent.getY() < 455 ){
                    timer.start();
                    level = 0;
                    numhits = 0;
                    reqhits = 3;
                    for (int i = 0; i < newBalls.size() ; i++) {
                        newBalls.remove(i);
                        i--;
                    }
                    for (int i = 0; i < level-1; i++) {
                        newBalls.remove(i);

                    }
                }
                gameover = false;

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
        for (int i = 0; i < 12; i++) {

//            theBalls.add(makeRandomBall());
            theBalls.add(new SmallBalls((int)(Math.random()*850), (int)(Math.random()*850)));
        }

        timer = new Timer(30, new ActionListener(){


            @Override

            public void actionPerformed(ActionEvent e) {
                for (int j = 0; j < newBalls.size(); j++) {
                    Ball n = newBalls.get(j);
                    if (newBalls != null) {
                        n.move(getWidth(), getHeight());
                        if (n.getDiameter() == 0) {
                            newBalls.remove(n);
                            j--;
                        }
                    }
                }

                for (int i = 0; i < theBalls.size(); i++) {
                    Ball b = theBalls.get(i);
                    b.move(getWidth(), getHeight());
                    for (int j = 0; j < newBalls.size(); j++) {
                        Ball n = newBalls.get(j);

                        if (n != null && n.intersects(b) && !(b instanceof NewBall)) {
                            b.setDiameter(0);
                            NewBall replacement = new NewBall((int) b.getX(), (int) b.getY());

                            newBalls.add(replacement);
                            theBalls.remove(i);
                            numhits++;
                            int m = (int) (Math.random() * 750);
                            if (m != b.getX())
                                m = (int) (Math.random() * 750);
                            int p = (int) (Math.random() * 750);
                            if (p != b.getX())
                                p = (int) (Math.random() * 750);

                            theBalls.add(new SmallBalls(m, p));
                            if (numhits == reqhits) {
                                levelup = true;
                                theBalls.add(new SmallBalls((int)(Math.random()*850), (int)(Math.random()*850)));
                            }


                        }


                    }
                    if(levelup)
                        timer.stop();


                    repaint();
                }
//                if (levelup = true){
//                    level++;
//                    numhits = 0;
//                    reqhits += level*2;
//                    for (int i = 0; i < newBalls.size() ; i++) {
//                        newBalls.remove(i);
//
//                    }
//                    s = 0;
//
//
//
//                }
            }

        });
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);  //gets rid of that trail effect.
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setStroke(new BasicStroke(3));

        for(Ball b: theBalls)
            b.draw(g2);
        for(Ball n: newBalls)
            n.draw(g2);
        g2.setColor(Color.RED);
        g2.drawString("Level: " + level, 10, 12);

        if (levelup == true){
            g2.setColor(Color.RED);
            g2.drawRect(400, 405, 100, 50);
            g2.drawString("Next Level", 415, 435);

            if(gameover == true){
                g2.setColor(Color.RED);
                g2.drawString("Level: " + level, 10, 20);
                g2.drawString("Target Hits: " + reqhits, 750, 790);
                g2.drawString("Hits left: " +  (reqhits - numhits), 750, 775);
                g2.drawRect(400, 405, 100, 50);
                g2.drawString("Press to Restart", 435, 390);

                g2.drawString("GAME OVER", 415, 435);
            }


            g2.setStroke(new BasicStroke(1));

        }

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
        Ball temp = new SmallBalls(x, y);
        return temp;
    }
}