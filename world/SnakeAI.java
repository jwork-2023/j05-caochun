package world;

import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import screen.GlyphDelegate;

public class SnakeAI extends CreatureAI implements GlyphDelegate {

    ArrayList<SnakePiece> pieces;

    private List<String> messages;

    public SnakeAI(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
        this.pieces = new ArrayList<>();
        
        //the creature is virtually the first piece of the snake
        pieces.add(new SnakePiece(creature.x(), creature.y()));
    }

    public ArrayList<SnakePiece> getPieces() {
        return pieces;
    }

    @Override
    public void onEnter(int x, int y, Tile tile) {

        int xPrev = pieces.get(0).x();
        int yPrev = pieces.get(0).y();

        if (tile.isGround()) {
            pieces.get(0).setX(x);
            pieces.get(0).setY(y);

            creature.setX(x);
            creature.setY(y);
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        } else {
            return;
        }

        if (pieces.size() > 1) {
            for (int i = 1; i < pieces.size(); i++) {
                int tempX = pieces.get(i).x();
                int tempY = pieces.get(i).y();
                pieces.get(i).setX(xPrev);
                pieces.get(i).setY(yPrev);
                xPrev = tempX;
                yPrev = tempY;
            }
        }
    }

    @Override
    public void attack(Creature another) {
        another.modifyHP(-100);
        this.grow();
    }

    public boolean grow() {
        // Get last element in list
        int x = pieces.get(pieces.size() - 1).x();
        int y = pieces.get(pieces.size() - 1).y();

        // Create new snake pieces and place them in a space that is not taken.
        SnakePiece piece = new SnakePiece(x - 1, y);
        if (!pieces.contains(piece)) {
            pieces.add(piece);
            return true;
        }

        SnakePiece piece2 = new SnakePiece(x + 1, y);
        if (!pieces.contains(piece2)) {
            pieces.add(piece);
            return true;
        }

        SnakePiece piece3 = new SnakePiece(x, y - 1);
        if (!pieces.contains(piece3)) {
            pieces.add(piece);
            return true;
        }

        SnakePiece piece4 = new SnakePiece(x, y + 1);
        if (!pieces.contains(piece4)) {
            pieces.add(piece);
            return true;
        }

        return false;

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onNotify(String message) {
        this.messages.add(message);
    }

    @Override
    public void printGlyph(AsciiPanel terminal, int offsetX, int offsetY) {
        for (SnakePiece p : this.getPieces()) {
            terminal.write(creature.glyph(), p.x() - offsetX, p.y() - offsetY, creature.color());
        }
    }
}
