package world;

import java.util.List;

import asciiPanel.AsciiPanel;

public class CreatureFactory {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }


    public Creature newSnake(List<String> messages) {
        Creature snake = new Creature(this.world, (char) 2, AsciiPanel.brightWhite, 100, 20, 5, 9);
        world.addAtEmptyLocation(snake);
        new SnakeAI(snake, messages);
        return snake;
    }

    public Creature newBean() {
        Creature bean = new Creature(this.world, (char) 3, AsciiPanel.green, 10, 0, 0, 0);
        world.addAtEmptyLocation(bean);
        new BeanAI(bean, this);
        return bean;
    }
}
