import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

public final class CircleBugRunner
{
    private static final int SIDELENGTH1 = 6;
    private static final int SIDELENGTH2 = 3;
    private static final int ROW1 = 7;
    private static final int COL1 = 8;
    private static final int ROW2 = 5;
    private static final int COL2 = 5;
    private CircleBugRunner()
    {
    }
    
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(SIDELENGTH1);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(SIDELENGTH2);
        world.add(new Location(ROW1, COL1), alice);
        world.add(new Location(ROW2, COL2), bob);
        world.show();
    }
}