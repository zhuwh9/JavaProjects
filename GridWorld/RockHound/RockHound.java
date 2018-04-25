import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

public class RockHound extends Critter
{
    public void processActors(ArrayList<Actor> actors)
    {
        /* In search of every actor in the list */
        for (Actor a : actors)
        {
            /* If actor around the rock hound is an instance of Rock
            , then remove it from the grid */
            if (a instanceof Rock)
            {
                a.removeSelfFromGrid();
            }
        }
    }
}