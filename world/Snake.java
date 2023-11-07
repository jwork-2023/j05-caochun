package world;

import java.util.ArrayList;

/**
 * Created by evanputnam on 6/25/17.
 */
public class Snake {

    /*Holds the snake pieces.  Acts like a linked list*/
    ArrayList<SnakePiece> pieces;



    /*Width of board*/
    private int width;

    /*Height of board*/
    private int height;


    /**
     * Constructor to create a snake game.
     * @param x starting x loc
     * @param y starting y loc
     * @param width width of board
     * @param height height of board
     */
    public Snake(int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<>();
        pieces.add(new SnakePiece(x, y));
    }


    /**
     * Direction the snake goes.
     * @param direction 1,2,3,4 as specified by finals above
     */
    public boolean updatePosition(int direction){

        //Store the previous location of the first before changing it
        int xPrev = pieces.get(0).x();
        int yPrev = pieces.get(0).y();

        //Changes the location of the very first
        // switch (direction){
        //     case MOVE_UP:
        //         pieces.get(0).setY(pieces.get(0).getY()-1);
        //         break;
        //     case MOVE_DOWN:
        //         pieces.get(0).setY(pieces.get(0).getY()+1);
        //         break;
        //     case MOVE_LEFT:
        //         pieces.get(0).setX(pieces.get(0).getX()-1);
        //         break;
        //     case MOVE_RIGHT:
        //         pieces.get(0).setX(pieces.get(0).getX()+1);
        //         break;
        // }

        //Updates subsequent locations by going through linked list
        if(pieces.size() > 1) {
            for (int i = 1; i < pieces.size(); i++) {
                int tempX = pieces.get(i).x();
                int tempY = pieces.get(i).y();
                pieces.get(i).setX(xPrev);
                pieces.get(i).setY(yPrev);
                xPrev = tempX;
                yPrev = tempY;
            }
        }

        //If more than one tile exists in same position then this checks for it
        int count = 0;
        for (SnakePiece p: pieces) {
            if(p.equals(pieces.get(0))){
                count += 1;
            }
        }


        //Handle go too far to the left
        if(pieces.get(0).x() < 0){
            return false;
        }
        //Handle go to far right
        if(pieces.get(0).x() > width-1){
            return false;
        }
        //Handle go to far down
        if(pieces.get(0).y() < 0){
            return false;
        }
        //Handle go to far up
        if(pieces.get(0).y() > height-1){
            return false;
        }


        //Returns from checking if multiple snake pieces share same position
        return count == 1;

    }


    /**
     * Adds another onto the end of the snake
     * @return
     */
    public boolean grow(){
        //Get last element in list
        int x = pieces.get(pieces.size()-1).x();
        int y = pieces.get(pieces.size()-1).y();

        //Create new snake pieces and place them in a space that is not taken.
        SnakePiece piece = new SnakePiece(x-1, y);
        if(!pieces.contains(piece)){
            pieces.add(piece);
            return true;
        }

        SnakePiece piece2 = new SnakePiece(x+1, y);
        if(!pieces.contains(piece2)){
            pieces.add(piece);
            return true;
        }

        SnakePiece piece3 = new SnakePiece(x, y-1);
        if(!pieces.contains(piece3)){
            pieces.add(piece);
            return true;
        }

        SnakePiece piece4 = new SnakePiece(x, y+1);
        if(!pieces.contains(piece4)){
            pieces.add(piece);
            return true;
        }

        return false;

    }


    /**
     * Returns the array list of snake pieces
     * @return
     */
    public ArrayList<SnakePiece> getPieces() {
        return pieces;
    }



}
