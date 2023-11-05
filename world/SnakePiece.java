package world;

/**
 * Created by evanputnam on 6/25/17.
 */
public class SnakePiece {

    /*X position of snake piece*/
    private int x;

    /*Y position of snake piece*/
    private int y;


    /**
     * Constructor for snake piece with x and y location
     * @param x
     * @param y
     */
    public SnakePiece(int x, int y){
        this.x  = x;
        this.y = y;
    }


    /**
     * Return x coordinate
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x coordinate
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Return the y coordinate
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coordinate
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }




    /**
     * Equal if x and y are equivilant
     * @param other
     * @return
     */
    public boolean equals(Object other){
        if(other instanceof SnakePiece){
            return ((SnakePiece) other).x == this.x &&
                    ((SnakePiece) other).y == this.y;
        }
        return false;
    }

}
