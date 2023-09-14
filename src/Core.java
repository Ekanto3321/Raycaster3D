import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Core extends JFrame {


    int height = 675;
    int width = 640;
    int unitWidth = 40;
    int fov = 90;
    double frameRate = 60.0;
    double timePerFrame = 1000000000.0/frameRate;
    double lastFrame;
    double playerX=45;
    double playerY=45;
    int speed = 5;

    public int getPlayerX() {
        return (int) playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return (int) playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    int i=0;

    Core(){


        Gfx g = new Gfx(90);
        add(g);
        g.setX(getPlayerX());
        g.setY(getPlayerY());
        g.setUnitWidth(unitWidth);
        g.getW().readMap();
        g.getW().intMap();
        g.setEndA();
        g.setHeight(height);
        g.setWidth(width);


        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                setPlayerX(e.getX());
                g.setX(getPlayerX());
                setPlayerY(e.getY());
                g.setY(getPlayerY());
            }
        });


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyChar()){
                    case'w':
                        playerY+=speed*(Math.cos(Math.toRadians(g.startA)));
                        g.setY(getPlayerY());
                        playerX+=speed*(Math.sin(Math.toRadians(g.startA)));
                        g.setX(getPlayerX());
                        break;
                    case's':
                        playerY-=speed*(Math.cos(Math.toRadians(g.startA)));
                        g.setY(getPlayerY());
                        playerX-=speed*(Math.sin(Math.toRadians(g.startA)));
                        g.setX(getPlayerX());
                        break;

                    case'a':
                        g.startA=(g.startA+speed)%360;
                        g.endA = g.startA+(fov/2);
                        break;
                    case'd':
                        g.startA=(360+g.startA-speed)%360;
                        g.endA = g.startA+(fov/2);
                        break;
                    default:
                        break;

                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


//        setBackground(Color.black);
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Raycaster3D");
    }

    public static void main(String[] args) {

        new Core().loop();

    }


    public void loop(){
        while(true){
            if(System.nanoTime()-lastFrame>=timePerFrame){
                lastFrame = System.nanoTime();
                repaint();
                i++;
            }
            if(i>=frameRate){
                System.out.println("FPS: " + i);
                i = 0;
            }
        }
    }

}