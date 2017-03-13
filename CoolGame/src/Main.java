import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int w = 750;
        int h = 750;
        JFrame window = new JFrame("Aniimation!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(500, 0, w, h); //(x, y, w, h)

        MyPanel panel = new MyPanel(w,h);
        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);

    }
}