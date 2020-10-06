package finalProject;
import apcs.Window;
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author pranavkakhandiki
 *  @version May 20, 2019
 */
public class PamSuper
{
    int x, y;
    int health = 3000;



    public PamSuper(int x, int y)
    {
        this.x = x;
        this.y = y;
        health = 3000;
    }

    public void draw(int h)
    {
        Window.out.image( "pamSuper.png", x, y );
        if (h <800)
        {
            Window.out.color("red");
        }
        else if (h <1500)
        {
            Window.out.color("orange");

        }
        else
        {
            Window.out.color("green");
        }
        double fraction = h/3000.0;
        double propHealth = fraction * 25.0;
        int intHealth = (int)propHealth;
        Window.out.rectangle(x+25, y-5, intHealth, 8);
        Window.out.color("black");
        Window.out.font("Times New Roman", 10);
        Window.out.print(h, x+15, y-13);

    }


    public boolean checkCollision(ColtBullet c)
    {
        return((Math.abs(x-c.x)<50) && (Math.abs(y-c.y)<50));
    }
    public boolean checkCollision(ColtSuper c)
    {
        return((Math.abs(x-c.x)<50) && (Math.abs(y-c.y)<50));
    }


}
