import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
/* QuickCrab is a subclass of CrabCritter */
public class QuickCrab extends CrabCritter
{
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locations = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        /* The left direction */
        int left = (int)(getDirection() + Location.LEFT);
        /* The right direction */
        int right = (int)(getDirection() + Location.RIGHT);
        /* get Location which is one step away in left direction */
        Location locLeft = loc.getAdjacentLocation(left);
        /* get Location which is one step away in right direction */
        Location locRight = loc.getAdjacentLocation(right);
        /* must be valid and no actor in it */
        if (gr.isValid(locLeft) && gr.get(locLeft) == null)
        {
            /* get Location which is two step away in left direction */
            Location locLeft2 = locLeft.getAdjacentLocation(left);
            /* must be valid and no actor in it */
            if (gr.isValid(locLeft2) && gr.get(locLeft2) == null)
            {
                locations.add(locLeft2);
            }
        }
        /* must be valid and no actor in it */
        if (gr.isValid(locRight) && gr.get(locRight) == null)
        {
            /* get Location which is two step away in right direction */
            Location locRight2 = locRight.getAdjacentLocation(right);
            /* must be valid and no actor in it */
            if (gr.isValid(locRight2) && gr.get(locRight2) == null)
            {
                locations.add(locRight2);
            }
        }
        /* no such location exists */
        if (locations.size() == 0)
        {
            return super.getMoveLocations();
        }
        return locations;
    }
}