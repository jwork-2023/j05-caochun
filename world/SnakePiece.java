package world;

public class SnakePiece {

    private int x;
    private int y;

    public SnakePiece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object other) {
        if (other instanceof SnakePiece) {
            return ((SnakePiece) other).x == this.x &&
                    ((SnakePiece) other).y == this.y;
        }
        return false;
    }
}
