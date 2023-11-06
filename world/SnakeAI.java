package world;

import java.util.ArrayList;
import java.util.List;

public class SnakeAI extends CreatureAI {

    /* Holds the snake pieces. Acts like a linked list */
    ArrayList<SnakePiece> pieces;

    private List<String> messages;

    public SnakeAI(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
        this.pieces = new ArrayList<>();
        pieces.add(new SnakePiece(creature.x(), creature.y()));
    }

    public ArrayList<SnakePiece> getPieces() {
        return pieces;
    }

    @Override
    public void onEnter(int x, int y, Tile tile) {
        
        if (tile.isGround()) {
            creature.setX(x);
            creature.setY(y);
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        }

        if(pieces.size() > 1) {
            for (int i = 1; i < pieces.size(); i++) {
                int tempX = pieces.get(i).getX();
                int tempY = pieces.get(i).getY();
                pieces.get(i).setX(xPrev);
                pieces.get(i).setY(yPrev);
                xPrev = tempX;
                yPrev = tempY;
            }
        }
    }

    int xPrev, yPrev;

    @Override
    public void onUpdate() {
        xPrev= pieces.get(0).getX();
        yPrev = pieces.get(0).getY();
    }

    @Override
    public void onNotify(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'onNotify'");
    }

    @Override
    public void attack(Creature another) {
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

}
