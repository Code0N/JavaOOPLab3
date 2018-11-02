import java.applet.*;
import java.awt.*;
import java.lang.Math;

public class ManyRects extends Applet implements Runnable {
    int x = 150, y = 50, r = 50;
    int dx = 11, dy = 7;
    Thread animator;
    ColoredRect[] rectangles;
    volatile boolean pleaseStop;
    public void paint(Graphics g) {
        /*g.setColor(Color.red);
        g.fillRect(x-r, y-r, r*2, r*2);*/
        g.fillRect(x-r - 20, y-r - 20, r*2 - 20, r*2 - 20);
//        g.setColor(Color.blue);
//        g.drawRect(x-r, y-r, r*2, r*2);
        /*crect.move(x-r, y-r, r*2, r*2);
        crect.Draw(g);*/
        for(int i = 0; i < 10; ++i) {
            rectangles[i].move(x-r - (i - 10), y-r - (i - 10), r*2 - (i - 10), r*2 - (i - 10));
            rectangles[i].Draw(g);
        }
        for(int i = 10; i < 20; ++i) {
            rectangles[i].move(x-r - (i - 25), y-r - (i - 25), r*2 - (i - 25), r*2 - (i - 25));
            rectangles[i].Draw(g);
        }
        for(int i = 20; i < 30; ++i) {
            rectangles[i].move(x-r - (i - 50), y-r - (i - 50), r*2 - (i - 50), r*2 - (i - 50));
            rectangles[i].Draw(g);
        }
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
        rectangles = new ColoredRect[30];
        for(int i = 0; i < 10; ++i) {
            rectangles[i] = new ColoredRect();
        }
        for(int i = 10; i < 20; ++i) {
            rectangles[i] = new ColoredRect();
        }
        for(int i = 20; i < 30; ++i) {
            rectangles[i] = new ColoredRect();
        }
        rectangles[29].outColor = Color.darkGray;
        rectangles[29].inColor = Color.red;
        animator = new Thread(this);
        pleaseStop = false;
        animator.start();
    }

    public void stop() {
        pleaseStop = true;
    }
}
