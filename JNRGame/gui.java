package JNRGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;



public class gui extends JPanel implements ActionListener {

    Timer time;
    Image img;
    Image img2;
    int key;
    int nx, nx2;
    Block block1;
    int coin_score;

    int figur_y = 235;

    int left = 0;

    int X_Bild;
    int lauf;

    int anzahl = 0;
    int anzahl2 = 0;



    public gui() {

        nx = 0;
        nx2 = 690;

        key = 0;
        lauf = 0;

        setFocusable(true);


        ImageIcon u = new ImageIcon((getClass() .getResource("background.jpg")));
        img = u.getImage();

        ImageIcon s = new ImageIcon((getClass() .getResource("char.png")));
        img2 = s.getImage();

        addKeyListener(new AL());

        block1 = new Block(250, 110, 50, 50, Color.YELLOW);

        @SuppressWarnings("unused")
        Sprung sprung = new Sprung();
        

        time = new Timer(5, this);
        time.start();
    }



    public void actionPerformed(ActionEvent e) {

        bewegen();

        figur_y = Sprung.sprungpos;

        repaint();
    }



    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D f2 = (Graphics2D)g;

        if (getXBild() == 510+ (anzahl *2350)) {

            anzahl += 1;
            nx = 0;
        }

        if (getXBild() == 1690+ (anzahl2 *2350)) {

            anzahl2 += 1;
            nx2 = 0;
        }

        if (getXBild() >= 510) {

            f2.drawImage(img, 685-nx, 0, null);
        }

        f2.drawImage(img, 685-nx2, 0, null);

        f2.drawImage(img2, left, figur_y, null);

        f2.setColor(block1.getFarbe());
        f2.fillRect(block1.getX_Block() - getXBild(), block1.getY_Block(), block1.getWidth(), block1.getHeight());

        block1.Kollisionsabfrage(block1.getX_Block() - getXBild(), block1.getY_Block() + block1.getHeight(), left + (74-12), figur_y);

        if (block1.Coin() == true) {

            coin_score = 1;
        }

        f2.drawString("Score: " + coin_score, 10, 15);
    }

    private int getXBild() {
        return X_Bild;
    }



    public void bewegen() {

        if (lauf != -2) {

            if (left + lauf <= 75) {

                left += lauf;
            }
            else {

                X_Bild += lauf;
                nx += lauf;
                nx2 += lauf;
            }
        }
        else {

            if (left + lauf > 0) {

                left += lauf;
            }
        }
    }



    private class AL extends KeyAdapter {
    
        public AL() {

        }

        public void keyPressed(KeyEvent e) {

            key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {

                lauf = -2;
            }

            if (key == KeyEvent.VK_RIGHT) {

                lauf = 2;
            }

            if (key == KeyEvent.VK_ESCAPE) {

                System.exit(0);
            }

            if (key == KeyEvent.VK_SPACE) {

                Sprung();
            }
        }

        public void keyReleased(KeyEvent e) {

            key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {

                lauf = 0;
            }
        }
    }

    public void Sprung() {

        Sprung SprungAnimation = new Sprung();
        SprungAnimation.start();
    }
}
