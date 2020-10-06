package finalProject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import apcs.Window;

/**
 *  poison cloud methods
 *  draws the poison and has a counter to flicker between images
 *
 *  @author  Chinmay Lalgudi, Rithwik Kerur, Pranav Kakhandiki
 *  @version May 27, 2019
 *  @author  Period: 5
 *  @author  Assignment: finalProject
 *
 *  @author  Sources:
 */
public class Poison
{
    int x, y;
    int mod = 7;
    int poison = 0;

    /**
     *
     * @param x
     * @param y
     * @param poison
     */
    public Poison(int x, int y, int poison)
    {
        this.x = x;
        this.y = y;
        this.poison = poison;
    }
    /**
     *
     * draws a gif of a poison cloud by flickering between screens
     */
    public void draw()
    {

        if (poison%(mod * 7) < 7)
            Window.out.image( "Poison.png", x, y );
        else if (poison%(mod*7) < mod*2)
            Window.out.image( "Poison3.png", x-2, y-2 );
        else if (poison%(mod*7) < mod*3)
            Window.out.image( "Poison.png", x, y );
        else if (poison%(mod*7) < mod*4)
        {
            Window.out.image( "Poison2.png", x+8, y );
            Window.out.image( "Poison2.png", x, y+10 );
            Window.out.image( "Poison2.png", x+16, y+10 );
        }
        else if (poison%(mod*7) < mod*5)
        {
            Window.out.image( "Poison5.png", x+18, y );
            Window.out.image( "Poison5.png", x, y+25 );
            Window.out.image( "Poison5.png", x+36, y+25 );
        }
        else if (poison%(mod*7) < mod*6)
        {
            Window.out.image( "Poison6.png", x+28, y );
            Window.out.image( "Poison6.png", x, y+40 );
            Window.out.image( "Poison6.png", x+56, y+40 );
        }


    }
    /**
     *
     * increases the poison counter for drawing it
     */
    public void incPoison()
    {
        poison++;
    }
}
