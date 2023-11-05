package world;

import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by evanputnam on 6/25/17.
 */
public class SnakeGame extends JFrame implements KeyListener{

    /*Width in charachters of the window*/
    private static final int WIDTH = 60;

    /*Height in charachters of the window*/
    private static final int HEIGHT = 30;



    /*Left key code*/
    private static final int LEFT_KEY = 37;

    /*Right key code*/
    private static final int RIGHT_KEY = 39;

    /*Up key code*/
    private static final int UP_KEY = 38;

    /*Down key code*/
    private static final int DOWN_KEY = 40;




    /*Delay time for the timer to update screen*/
    private static final int DELAY_TIME = 200;



    /*Variable to represent the direction of the board at any given time*/
    private int direction = 1;

    /*Snake data structure with an array list of other smaller snake pieces*/
    private Snake snake;

    /*Fruit object for the snake to eat*/
    private Fruit fruit;


    public SnakeGame(){
        super();

        //Create the panel
        AsciiPanel panel = new AsciiPanel(WIDTH, HEIGHT);

        //Create snake data structure
        snake = new Snake(WIDTH/2, HEIGHT/2, WIDTH, HEIGHT);

        //Add listener add and pack
        addKeyListener(this);
        add(panel);
        pack();

        //Objects for random width and height
        Random widthR = new Random();
        Random heightR = new Random();

        //New fruit object
        fruit = new Fruit(Math.abs(widthR.nextInt()%WIDTH), Math.abs(heightR.nextInt()%HEIGHT));

        //Timer that runs every 200 seconds to update the game board.
        Timer timer = new Timer(DELAY_TIME, null);
        ActionListener listen = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Updates the snakes position based on the current direction from keyboard presses
                boolean b = snake.updatePosition(direction);

                //If something went wrong in update stop timer and return
                if(b == false){
                    timer.stop();
                    return;
                }

                //Clear the panel to then draw again
                panel.clear();

                //Iterate over the snake pieces to draw to screen
                for (SnakePiece p : snake.getPieces()) {
                    panel.write('@', p.getX(), p.getY());
                }

                //If the snake is on top of a fruit then grow and add new fruit
                if (snake.getPieces().contains(new SnakePiece(fruit.getX(), fruit.getY()))) {
                    snake.grow();
                    fruit.setX(Math.abs(widthR.nextInt() % WIDTH));
                    fruit.setY(Math.abs(heightR.nextInt() % HEIGHT));
                    panel.write('.', fruit.getX(), fruit.getY());
                    //Else write the fruit normally
                } else {
                    panel.write('.', fruit.getX(), fruit.getY());
                }

                //Repaint the screen
                panel.repaint();
            }
        };
        //Add the above action listener
        timer.addActionListener(listen);
        //Start the timer
        timer.start();

    }


    /**
     * Changes direction based on the key being pressed
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        //Given key is pressed then change the direction var.
        switch (e.getKeyCode()){
            case LEFT_KEY:
                direction = snake.MOVE_LEFT;
                break;
            case RIGHT_KEY:
                direction = snake.MOVE_RIGHT;
                break;
            case UP_KEY:
                direction = snake.MOVE_UP;
                break;
            case DOWN_KEY:
                direction = snake.MOVE_DOWN;
                break;
        }
    }

    /**
     * Not used but needed for interface
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }


    /**
     * Not used but needed for interface
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }


    /**
     * Main function to start the game
     * @param args
     */
    public static void main(String args[]){
        SnakeGame game = new SnakeGame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }

}
