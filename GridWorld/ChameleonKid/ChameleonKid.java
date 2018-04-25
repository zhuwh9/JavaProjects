import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
/* Chameleon is a subclass of ModifiedChameleonCritter */
public class ChameleonKid extends ModifiedChameleonCritter
{
    public ArrayList<Actor> getActors()
    {
        // for return
        ArrayList<Actor> list = new ArrayList<Actor>();
        // get grid that the actor stays
        Grid<Actor> gr = getGrid();
        // get location that the actor stays
        Location loc = getLocation();
        // get the front location
        Location front = loc.getAdjacentLocation(getDirection());
        // get the back location
        Location back = loc.getAdjacentLocation((getDirection()+Location.HALF_CIRCLE)%Location.FULL_CIRCLE);
        // if front locaion exist and with no actors
        if (gr.isValid(front))
        {
            // get front actor
            Actor neighborFront = gr.get(front);
            // not null, then add to the list
            if (neighborFront != null)
            {
                list.add(neighborFront);
            }           
        }
        // if back locaion exist and with no actors
        if (gr.isValid(back))
        {
            // get back actor
            Actor neighborBack = gr.get(back);
            // not null, then add to the list
            if (neighborBack != null)
            {
                list.add(neighborBack);
            }
        }
        return list;
    }
}
