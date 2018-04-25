import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains crab critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class KingCrabRunner
{
    /* some constants */
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    /* Empty constructor */
    private KingCrabRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, FIVE), new Rock());
        world.add(new Location(FIVE, FOUR), new Rock());
        world.add(new Location(FIVE, SEVEN), new Rock());
        world.add(new Location(SEVEN, THREE), new Rock());
        world.add(new Location(SEVEN, EIGHT), new Flower());
        world.add(new Location(TWO, TWO), new Flower());
        world.add(new Location(THREE, FIVE), new Flower());
        world.add(new Location(THREE, EIGHT), new Flower());
        world.add(new Location(SIX, FIVE), new Bug());
        world.add(new Location(FIVE, THREE), new Bug());
        world.add(new Location(FOUR, FIVE), new KingCrab());
        world.add(new Location(SIX, ONE), new KingCrab());
        world.add(new Location(SEVEN, FOUR), new KingCrab());
        world.show();
    }
}