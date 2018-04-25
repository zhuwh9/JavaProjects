import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class ZBugRunner
{
    private static final int SIDELENGTH = 4;
    private ZBugRunner()
    {
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(SIDELENGTH);
        world.add(new Location(2, 2), alice);
        world.show();
    }
}