import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class Gfx extends JPanel{
    Gfx(int fov){
        this.fov = fov;
    }

    Wall w = new Wall();

    int fov,unitWidth;

    public void setUnitWidth(int unitWidth) {
        this.unitWidth = unitWidth;
    }

    public Wall getW() {
        return w;
    }

    int x,y;
    int startA,endA;

    public void setEndA() {
        endA = startA+(fov/2);
    }

    int height;
    int width;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    protected void paintComponent(Graphics g) {


        for (int i = 0; i < w.mapUnit; i++) {
            for (int j = 0; j < w.mapUnit; j++) {
                if(w.map[i][j]==1){
                    g.setColor(Color.gray);
                    g.fillRect((j*width/w.mapUnit),(i*width/w.mapUnit),39,39);
                }
                else{
                    g.setColor(Color.BLACK);
                    g.fillRect((j*width/w.mapUnit),(i*width/w.mapUnit),39,39);
                }
            }

        }

        //setting color and drawing character
        g.setColor(Color.lightGray);
        g.drawOval(x-10,y-10,20,20);
        //drawing rays
        for (int i = startA-fov/2; i < endA; i++){
            int x1 = x;
            int y1 = y;
            int x2 = x+(int)(100*Math.sin(Math.toRadians(i)));
            int y2 = y+(int)(100*Math.cos(Math.toRadians(i)));
            if(x1<width&&x1>(0)&&y1<width&&y1>(0)){
                for (int j = 0; j < w.mapUnit; j++) {
                    for (int k = 0; k < w.mapUnit ; k++) {
                        if(x2>=(unitWidth*(k+1))-unitWidth&&x2<=(unitWidth*(k+1))
                            &&y2>=(unitWidth*(j+1))-unitWidth&&y2<=(unitWidth*(j+1))
                            &&w.map[j][k]==0){
                            g.drawLine(x1,y1, x2, y2);
                        }
                    }
                }

            }
        }
        //drawing the direction vector
        g.setColor(Color.RED);
        g.drawLine(x,y,x+(int)(100*Math.sin(Math.toRadians(startA))), y+(int)(100*Math.cos(Math.toRadians(startA))));


        //backups
//        for (int i = startA-fov/2; i < endA; i+=5){
//            int x1 = x+10;
//            int y1 = y+10;
//            int x2 = x+(int)(100*Math.sin(Math.toRadians(i)));
//            int y2= y+(int)(100*Math.cos(Math.toRadians(i)));
//            g.drawLine(x1,y1, x2, y2);
//        }




    }
}

