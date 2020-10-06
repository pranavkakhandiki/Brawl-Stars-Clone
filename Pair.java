package finalProject;

/**
 *  pair
 *  generates a pair: x, y
 *
 *  @author  Chinmay Lalgudi, Rithwik Kerur, Pranav Kakhandiki
 *  @version May 27, 2019
 *  @author  Period: 5
 *  @author  Assignment: finalProject
 *
 *  @author  Sources:
 */
public class Pair<X,Y>
{
    private X x;
    private Y y;
    /**
     * creates a pair with parameters x and y
     * @param x
     * @param y
     */
    public Pair(X x, Y y){
        this.x = x;
        this.y = y;
    }
    /**
     *
     * returns x
     * @return x
     */
    public X getX(){ return x; }
    /**
     *
     * returns y
     * @return y
     */
    public Y getY(){ return y; }
    /**
     *
     * sets x to parameter
     * @param x
     */
    public void setX(X x){ this.x = x; }
    /**
     *
     * sets y to parameter
     * @param y
     */
    public void setY(Y y){ this.y = y; }
}

