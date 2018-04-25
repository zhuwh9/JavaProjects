import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;

public final class BlusterCritterRunner
{
    /* some constants */
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    /* empty private constructor */
    private BlusterCritterRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, EIGHT), new Critter());
        world.add(new Location(THREE, THREE), new Critter());
        world.add(new Location(TWO, EIGHT), new Critter());
        world.add(new Location(FIVE, FIVE), new Critter());
        world.add(new Location(ONE, FIVE), new Critter());
        world.add(new Location(SEVEN, TWO), new Critter());
        world.add(new Location(FOUR, FOUR), new BlusterCritter(THREE));
        world.add(new Location(FIVE, EIGHT), new BlusterCritter(TWO));
        world.show();
    }
}