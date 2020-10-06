package finalProject;

import java.awt.Color;
import java.lang.Math;
import apcs.Window;
import java.util.Arrays;

public class Block
{
    int x,y;
    int side = 45;
    public Block()
    {

        this.x = ((int)(Math.random() * 28) * 50) + 50;
        this.y = ((int)(Math.random() * 16) * 50) + 50;
    }

    public Block(int x, int y)
    {
        this.x = x;
        this.y = y;
        draw();
    }

    public void draw()
    {
        //outer boundary
        Window.out.color(136, 64, 26);
        Window.out.rectangle(x, y + side*7/24, side*7/6, side*7/4);
        //pink square
        Window.out.color(238, 133, 103);
        Window.out.square(x, y, side);
        //gray rectangle under square
        Window.out.color(166, 106, 92);
        Window.out.rectangle(x, y + side * 5/12, side, side*1/5);
        //lighter brown under gray rectangle
        Window.out.color(151, 75, 41);
        Window.out.rectangle(x, y + side*38/48, side, side*9/16);
        //light highlight under dark rectangle
        Window.out.color(166, 96, 60);
        Window.out.rectangle(x, y + side*43/48, side, side*1/5);
        //dark rectangle in light brown
        Window.out.color(118, 62, 32);
        Window.out.rectangle(x, y + side*40/48, side, side*1/5);


        Window.out.color(136, 64, 26);
        Window.out.rectangle(x, y+15, side , 3);

        Window.out.color(159, 145, 127);
        Window.out.rectangle(x, y+62, side+5, 20);

    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}
