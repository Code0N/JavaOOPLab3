import java.applet.*;
import java.awt.*;

public class bcirc extends Applet implements Runnable {
    int x = 150, y = 50, r = 50;
    int dx = 11, dy = 7;
    Thread animator;
    ColoredRect crect = new ColoredRect(x-r, y-r, r*2, r*2,  Color.red, Color.blue);
    volatile boolean pleaseStop;
    public void paint(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect(x-r, y-r, r*2, r*2);
//        g.setColor(Color.blue);
//        g.drawRect(x-r, y-r, r*2, r*2);
        crect.move(x-r, y-r, r*2, r*2);
        crect.Draw(g);
    }
    public void animate() {
        java.awt.Rectangle bounds = getBounds();
        if ((x - r + dx < 0) || (x + r + dx > bounds.width)) dx = -dx; if ((y - r + dy < 0) || (y + r + dy > bounds.height)) dy = -dy;
        x += dx; y += dy;
        repaint();
    }
    public void run() {
        while (!pleaseStop) {
            animate();
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {

            }
        }
    }

    public void start() {
        animator = new Thread(this);
        pleaseStop = false;
        animator.start();
    }

    public void stop() {
        pleaseStop = true;
    }
}
