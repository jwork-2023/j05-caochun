package world;

/**
 * Created by evanputnam on 6/25/17.
 */
public class Fruit {

    /*X position of the fruit*/
    private int x;

    /*Y position of the fruit*/
    private int y;


    /**
     * Fruit constructor with an x and y coordinate
     * @param x
     * @param y
     */
    public Fruit(int x, int y){
        this.x = x;
        this.y = y;
    }


    /**
     * Get the x coordinate
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
     * Get the y coordinate
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
}
