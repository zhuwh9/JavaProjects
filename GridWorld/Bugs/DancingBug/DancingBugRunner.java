import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public final class DancingBugRunner
{
    private static final int ROW = 8;
    private static final int COL = 8;
    private DancingBugRunner()
    {
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] arr = {1,2,2,1,1};
        DancingBug alice = new DancingBug(arr);
        alice.setColor(Color.ORANGE);
        world.add(new Location(ROW,COL), alice);
        world.show();
    }
}