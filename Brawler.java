package finalProject;
import java.util.ArrayList;

import apcs.Window;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author pkakhandiki277
 *  @version May 7, 2019
 */
public abstract class Brawler
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    public abstract boolean shoot();
    public abstract void draw(int dir, int counter, double angle, int csuper);
    public abstract ArrayList<String> move(int blocksX[], int blocksY[]);
    public abstract boolean isDead();
}
