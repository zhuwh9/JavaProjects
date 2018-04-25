import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;

public final class ChameleonKidRunner
{
    /* some constants */
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    // empty private constructor
    private ChameleonKidRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, EIGHT), new Rock());
        world.add(new Location(THREE, THREE), new Rock());
        world.add(new Location(TWO, EIGHT), new Rock(Color.BLUE));
        world.add(new Location(FIVE, FIVE), new Rock(Color.PINK));
        world.add(new Location(ONE, FIVE), new Rock(Color.RED));
        world.add(new Location(SEVEN, TWO), new Rock(Color.YELLOW));
        world.add(new Location(FOUR, FOUR), new ChameleonKid());
        world.add(new Location(FIVE, EIGHT), new ChameleonKid());
        world.show();
    }
}