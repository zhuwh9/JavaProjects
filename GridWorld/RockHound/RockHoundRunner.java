import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class RockHoundRunner
{
    /* Some constants */
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    /* Empty Constructor */
    private RockHoundRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, EIGHT), new Rock());
        world.add(new Location(THREE, THREE), new Rock());
        world.add(new Location(TWO, EIGHT), new Flower(Color.BLUE));
        world.add(new Location(FIVE, FIVE), new Flower(Color.PINK));
        world.add(new Location(ONE, FIVE), new Flower(Color.RED));
        world.add(new Location(SEVEN, TWO), new Flower(Color.YELLOW));
        world.add(new Location(FOUR, FOUR), new RockHound());
        world.add(new Location(FIVE, EIGHT), new RockHound());
        world.show();
    }
}