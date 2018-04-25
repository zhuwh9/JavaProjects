import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public final class SpiralBugRunner
{
    private static final int SIDELENGTH = 4;
    private static final int ROW = 8;
    private static final int COL = 8;
    private SpiralBugRunner()
    {
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        SpiralBug alice = new SpiralBug(SIDELENGTH);
        alice.setColor(Color.ORANGE);
        world.add(new Location(ROW,COL), alice);
        world.show();
    }
}