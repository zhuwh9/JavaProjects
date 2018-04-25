import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.util.ArrayList;
/* KingCrab is a subclass of CrabCritter */
public class KingCrab extends CrabCritter
{
    /* some constants */
    public static final int HALF = 0.5;
    /* compute the distance between two locations */
    public int getDistance(Location loc1, Location loc2)
    {
        int x1 = loc1.getRow();
        int y1 = loc1.getCol();
        int x2 = loc2.getRow();
        int y2 = loc2.getCol();
        /* use Math.sqrt to compute and try to get its floor value */
        double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))+HALF;
        return (int)Math.floor(distance);
    }
    /* test if the actor can move away */
    private boolean canMoveAway(Actor a)
    {
        /* get empty locations around the actor */
        ArrayList<Location> locations = getGrid().getEmptyAdjacentLocations(a.getLocation());
        for (Location loc : locations)
        {
            /* if the distance between them is more than 1 
            then move the actor which is far away from kingcrab */
            if (getDistance(getLocation(), loc) > 1)
            {
                /* move to a new location */
                a.moveTo(loc);
                return true;
            }
        }
        /* no such a location to move to */
        return false;
    }
    public void processActors(ArrayList<Actor> actors)
    {
        /* In search of every actor in the list */
        for (Actor a : actors)
        {
            /* If the actor can not move away, */
            if (!canMoveAway(a))
            {
                /* Remove the actor */
                a.removeSelfFromGrid();
            }
        }
    }
}
