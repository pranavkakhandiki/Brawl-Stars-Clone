package finalProject;

import apcs.Window;
import java.math.*;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author pkakhandiki277
 *  @version May 7, 2019
 */
public class ColtBullet extends Bullet
{
    int x;
    int y;
    int speed;
    String color;
    double direction;
    public ColtBullet(int x, int y, double direction){
        super(x, y);
        this.x=x+50;
        this.y=y+50;
        this.direction = direction;
        speed=25;
        color="blue";
    }


    public void draw(double angle)
    {
        // border for bullet
        Window.out.color("white");
        Window.out.rectangle((int)(x + ((50 * Math.cos( 2 * Math.PI * (angle/360.0) )))), (int)(y - (50 * Math.sin( 2 * Math.PI * (angle/360.0) ))), 20, 12, 360.0-angle);
        Window.out.circle((int)(x + (((60 * Math.cos( 2 * Math.PI * (angle/360.0) ))))), (int)(y - (60 * Math.sin( 2 * Math.PI * (angle/360.0) ))), 6);
        // smaller blue bullet
        Window.out.color("blue");
        Window.out.rectangle((int)(x + ((50 * Math.cos( 2 * Math.PI * (angle/360.0) )))), (int)(y - (50 * Math.sin( 2 * Math.PI * (angle/360.0) ))), 20, 10, 360.0-angle);
        Window.out.circle((int)(x + (((60 * Math.cos( 2 * Math.PI * (angle/360.0) ))))), (int)(y - (60 * Math.sin( 2 * Math.PI * (angle/360.0) ))), 5);
        Window.out.color(30, 144, 255);
        Window.out.circle((int)(x + (((40 * Math.cos( 2 * Math.PI * (angle/360.0) ))))), (int)(y - (40 * Math.sin( 2 * Math.PI * (angle/360.0) ))), 5);
        Window.out.color("tan");
        Window.out.rectangle((int)(x + ((35 * Math.cos( 2 * Math.PI * (angle/360.0) )))), (int)(y - (35 * Math.sin( 2 * Math.PI * (angle/360.0) ))), 10, 10, 360.0-angle);

        }

    public boolean checkMove(int blocksX[], int blocksY[])
    {
        boolean cont = true;
        for (int i=0; i<60; i++)
        {
            if (checkCollisionBlock( blocksX[i], blocksY[i] ))
            {
                cont = false;
            }

        }
        return cont;

    }
    public void move(int blocksX[], int blocksY[], double angle)
    {
        x += Math.cos( 2 * Math.PI * (angle/360.0) ) * speed;
        y -= Math.sin( 2 * Math.PI * (angle/360.0) ) * speed;

    }
    public boolean checkCollisionPam(Pam p)
    {
        if (((x >= (p.x)) && (x <= (p.x + 100)) && ((y >= ( p.y)) && (y <= (p.y + 100)))))
        {
            return true;
        }

        return false;
    }

    public boolean checkCollisionBlock(int bX, int bY)
    {
        if (((x >= (bX - 30)) && (x <= (bX + 30)) && ((y >= (bY - 30)) && (y <= (bY + 30)))))
        {
            return true;
        }

        return false;
    }
    public boolean checkCollisionBox(int bX, int bY)
    {
        if (((x >= (bX)) && (x <= (bX + 50)) && ((y >= (bY)) && (y <= (bY + 50)))))
        {
            return true;
        }

        return false;
    }
}
