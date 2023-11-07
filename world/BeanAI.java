package world;

public class BeanAI extends CreatureAI{


    private CreatureFactory factory;
    private int spreadcount = 0;

    public static int spores = 5;
    public static double spreadchance = 0.01;

    public BeanAI(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    public void onUpdate() {
        if (this.spreadcount < BeanAI.spores && Math.random() < BeanAI.spreadchance) {
            spread();
        }
    }

    private void spread() {
        int newx = creature.x() + (int) (Math.random() * 11) - 5;
        int newy = creature.y() + (int) (Math.random() * 11) - 5;

        if (!creature.canEnter(newx, newy)) {
            return;
        }

        Creature child = this.factory.newBean();
        child.setX(newx);
        child.setY(newy);
        spreadcount++;
    }

    @Override
    public void onEnter(int x, int y, Tile tile) {
        throw new UnsupportedOperationException("Unimplemented method 'onEnter'");
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
