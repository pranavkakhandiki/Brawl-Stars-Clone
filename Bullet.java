package finalProject;

import apcs.Window;

public abstract class Bullet
{
    int x,y;
    int speed;
    int radius = 3;
    String color;
    /**
     *
     * @param x
     * @param y
     */
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;

    }
    /**
     *
     * draw abstract class
     * @param angle
     */
    public abstract void draw(double angle);
    /**
     *
     * move abstract class
     * @param blocksX
     * @param blocksY
     * @param angle
     */
    public void move (int blocksX[], int blocksY[], double angle) {
    }
}
