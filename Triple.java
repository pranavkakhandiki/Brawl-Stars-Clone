package finalProject;

/**
 *  triple
 *  generates a triple: x, y, z
 *
 *  @author  Chinmay Lalgudi, Rithwik Kerur, Pranav Kakhandiki
 *  @version May 27, 2019
 *  @author  Period: 5
 *  @author  Assignment: finalProject
 *
 *  @author  Sources:
 */
public class Triple<X,Y,Z>
{
    private X x;
    private Y y;
    private Z z;
    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Triple(X x, Y y, Z z){
        this.x = x;
        this.y = y;
        this.z = z;
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
     * returns z
     * @return z
     */
    public Z getZ() { return z; }
    /**
     *
     * sets x to parameter
     * @return x
     */
    public void setX(X x){ this.x = x; }
    /**
     *
     * sets y to parameter
     * @return y
     */
    public void setY(Y y){ this.y = y; }
    /**
     *
     * sets z to parameter
     * @return z
     */
    public void setZ(Z z) { this.z = z; }
}
