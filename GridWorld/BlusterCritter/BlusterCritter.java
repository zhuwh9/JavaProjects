import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;
/* BlusterCritter is a subclass of Critter */
public class BlusterCritter extends Critter
{
    // Color max range
    public static final int MAX = 255;
    // Courage is used to define actions next
    private int courage;
    // constructor
    public BlusterCritter(int c)
    {
        super();
        courage = c;
    }
    // get courage since it is private
    public int getCourage()
    {
        return courage;
    }
    // get Actors around due to requirements
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location loc = getLocation();
        // 24 cells from upper 2 to lowerer 2 rows
        for (int r = loc.getRow()-2; r <= loc.getRow()+2; r++)
        {
            //, from left 2 colomns to right 2 colomuns
            for (int c = loc.getCol()-2; c <= loc.getCol()+2; c++)
            {
                Location temp = new Location(r,c);
                // check if valid
                if (getGrid().isValid(temp))
                {
                    Actor a = getGrid().get(temp);
                    // not null and not itself
                    if (a != null && a != this)
                    {
                        // then add
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }
    // count the numbers of critters except itself
    public void processActors(ArrayList<Actor> actors)
    {
        int count = 0;
        for (Actor a : actors)
        {
            // if a is an instance of critter, add count by 1
            if (a instanceof Critter)
            {
                count++;
            }
        }
        // if count is less than courage, than it got brighter
        if (count < courage)
        {
            Color c = getColor();
            int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            // avoid add to a number that is out of range 255
            if (red < MAX)
            {
                red++;
            }
            // avoid add to a number that is out of range 255
            if (green < MAX)
            {
                green++;
            }
            // avoid add to a number that is out of range 255
            if (blue < MAX)
            {
                blue++;
            }
            setColor(new Color(red, green, blue));
        }
        // else it got darker
        else
        {
            Color c = getColor();
            int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            // avoid substract to a number that is out of range 0
            if (red > 0)
            {
                red--;
            }
            // avoid substract to a number that is out of range 0
            if (green > 0)
            {
                green--;
            }
            // avoid substract to a number that is out of range 0
            if (blue > 0)
            {
                blue--;
            }
            setColor(new Color(red, green, blue));
        }
    }
}
