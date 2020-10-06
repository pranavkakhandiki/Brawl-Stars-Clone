package finalProject;
import apcs.Window;

public class ElixirBox
{
    int x, y;
    int health = 6000;


    public ElixirBox(int[] blocksX, int[] blocksY)
    {

        boolean cont = true;
        x = ( (int)( Math.random() * 28 ) * 50 ) + 25;
        y = ( (int)( (Math.random() * 16 )+1) * 50 ) + 25;
        boolean collision = true;
        boolean hit = false;

        while ( collision )
        {

            for ( int i = 0; i < blocksX.length; i++ )
            {
                if ( ( ( x >= ( blocksX[i] - 30 ) ) && ( x <= ( blocksX[i] + 30 ) )
                    && ( ( y >= ( blocksY[i] - 30 ) ) && ( y <= ( blocksY[i] + 30 ) ) ) ) )
                {

                    x = ( (int)( Math.random() * 28 ) * 50 ) + 25;
                    y = ( (int)( Math.random() * 16 ) * 50 ) + 25;
                    hit = true;
                    break;
                }

            }
            if ( !hit )
            {
                collision = false;

            }
            hit = false;
        }




    }
    public ElixirBox(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw()
    {
        Window.out.image( "box2.png", x, y );
        Window.out.color("black");
        Window.out.font("Times New Roman", 12);
        Window.out.print(health, x+12, y-5);


    }


    public boolean checkCollision(ColtBullet c)
    {
        return((Math.abs(x-c.x)<50) && (Math.abs(y-c.y)<50));
    }
    public boolean checkCollision(ColtSuper c)
    {
        return((Math.abs(x-c.x)<50) && (Math.abs(y-c.y)<50));
    }
    public boolean checkCollision(PamBullet p)
    {
        return((Math.abs(x-p.x)<50) && (Math.abs(y-p.y)<50));
    }
    public void decreaseHealth(int i)
    {
        health -= i;
    }
    public boolean contains(int bx, int by)
    {
        if (((x+12) >= (bx)) && ((x+12) <= (bx + 100)) && ((y+12) >= (by)) && ((y+12) <= (by + 100)))
            return true;
        return false;
    }
}
