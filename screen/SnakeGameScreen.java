package screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import world.Creature;
import world.CreatureFactory;
import world.Fruit;
import world.World;
import world.WorldBuilder;

/**
 * Created by evanputnam on 6/25/17.
 */
public class SnakeGameScreen implements Screen {

    /* Width in charachters of the window */
    private static final int WIDTH = 60;

    /* Height in charachters of the window */
    private static final int HEIGHT = 30;

    /* Left key code */
    private static final int LEFT_KEY = 37;

    /* Right key code */
    private static final int RIGHT_KEY = 39;

    /* Up key code */
    private static final int UP_KEY = 38;

    /* Down key code */
    private static final int DOWN_KEY = 40;

    /* Delay time for the timer to update screen */
    private static final int DELAY_TIME = 200;

    /* Variable to represent the direction of the board at any given time */
    private int direction = 1;

    /* Snake data structure with an array list of other smaller snake pieces */
    private Creature snake;

    /* Fruit object for the snake to eat */
    private Fruit fruit;

    private List<String> messages;
    private List<String> oldMessages;

    private World world;

    public SnakeGameScreen() {
        super();

        createWorld();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();

        CreatureFactory creatureFactory = new CreatureFactory(this.world);
        createCreatures(creatureFactory);
    }

    private void createCreatures(CreatureFactory creatureFactory) {
        this.snake = creatureFactory.newSnake(this.messages);

        for (int i = 0; i < 8; i++) {
            creatureFactory.newBean();
        }
    }

    private void createWorld() {
        world = new WorldBuilder(WIDTH, HEIGHT).makeCaves().build();
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Terrain and creatures
        displayTiles(terminal, getScrollX(), getScrollY());
        // Player
        terminal.write(snake.glyph(), snake.x() - getScrollX(), snake.y() - getScrollY(), snake.color());
        // Stats
        String stats = String.format("%3d/%3d hp", snake.hp(), snake.maxHP());
        terminal.write(stats, 1, 23);
        // Messages
        displayMessages(terminal, this.messages);
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        // Show terrain
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int wx = x + left;
                int wy = y + top;

                if (snake.canSee(wx, wy)) {
                    terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
                } else {
                    terminal.write(world.glyph(wx, wy), x, y, Color.DARK_GRAY);
                }
            }
        }
        // Show creatures
        for (Creature creature : world.getCreatures()) {
            if (creature.x() >= left && creature.x() < left + WIDTH && creature.y() >= top
                    && creature.y() < top + HEIGHT) {
                if (snake.canSee(creature.x(), creature.y())) {
                    terminal.write(creature.glyph(), creature.x() - left, creature.y() - top, creature.color());
                }
            }
        }
        // Creatures can choose their next action now
        world.update();
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = HEIGHT - messages.size();
        for (int i = 0; i < messages.size(); i++) {
            terminal.write(messages.get(i), 1, top + i + 1);
        }
        this.oldMessages.addAll(messages);
        messages.clear();
    }

    public int getScrollX() {
        return Math.max(0, Math.min(snake.x() - WIDTH / 2, world.width() - WIDTH));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(snake.y() - HEIGHT / 2, world.height() - HEIGHT));
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        // switch (key.getKeyCode()) {
        // case LEFT_KEY:
        // direction = snake.MOVE_LEFT;
        // break;
        // case RIGHT_KEY:
        // direction = snake.MOVE_RIGHT;
        // break;
        // case UP_KEY:
        // direction = snake.MOVE_UP;
        // break;
        // case DOWN_KEY:
        // direction = snake.MOVE_DOWN;
        // break;
        // }

        switch (key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                snake.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                snake.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
                snake.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                snake.moveBy(0, 1);
                break;
        }
        return this;
    }

}
