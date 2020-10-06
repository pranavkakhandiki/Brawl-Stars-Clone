package finalProject;

import apcs.Window;

public class Gem
{
    int x, y;
    int c;


    public void draw()
    {
        if (c%48 < 24)
            Window.out.image( "gem6.png", x, y );
        else
            Window.out.image( "gem7.png", x, y );
        c++;
    }
    public Gem(int x, int y)
    {

        this.x = x;
        this.y = y;
        this.c = 0;
    }

    public boolean checkCollision(Colt c)
    {
        return((Math.abs(x-c.x)<50) && (Math.abs(y-c.y)<50));
    }
    public boolean checkCollision(Pam p)
    {
        return((Math.abs(x-p.x)<50) && (Math.abs(y-p.y)<50));
    }
}
