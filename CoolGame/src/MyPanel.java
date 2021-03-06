import javax.swing.*;
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
                if (levelup == true && mouseEvent.getX() > 345 && mouseEvent.getX() < 465 && mouseEvent.getY() > 365 && mouseEvent.getY() < 405 ){
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

                if (gameover == true && mouseEvent.getX() > 332 && mouseEvent.getX() < 482 && mouseEvent.getY() > 365 && mouseEvent.getY() < 405 ){
                    timer.start();
                    level = 1;
                    numhits = 0;
                    reqhits = 3;
                    for (int i = 0; i < level-1; i++) {
                        theBalls.remove(i);

                    }
                    s = 0;
                    gameover = false;
                }
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
        for (int i = 0; i < 14; i++) {

//            theBalls.add(makeRandomBall());
            theBalls.add(new SmallBalls((int)(Math.random()*800), (int)(Math.random()*800)));
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
                            }
                        }
                    }
                    if(s >= 1 && newBalls.size() == 0) {
                        gameover = true;
                    }

                    if(levelup)
                        timer.stop();
                    if (gameover)
                        timer.stop();


                    repaint();
                }
            }

        });
        timer.start();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(44, 44, 47));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setStroke(new BasicStroke(3));

        for (Ball b : theBalls)
            b.draw(g2);
        for (Ball n : newBalls)
            n.draw(g2);
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));

        g2.drawString("Level: " + level, 10, 25);
        g2.drawString((reqhits - numhits) + " more balls...", 665, 763);

        if (levelup == true) {
            g2.setColor(Color.RED);
            g2.drawRect(345, 365, 120, 50);
            g2.drawString("Next Level", 358, 397);
        }

        if (gameover == true) {
            g2.setColor(Color.RED);
            g2.drawRect(332, 365, 150, 50);
            g2.drawString("Press to Restart", 335, 354);
            g2.drawString("GAME OVER", 345, 398);
        }
        g2.setStroke(new BasicStroke(1));
    }
}