import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug
{
    private int steps;
    private int totalSteps;
    private int sideLength;
    private int state;
    private static final int THREE = 3;
    public ZBug(int length)
    {
        steps = 0;
        totalSteps = 0;
        sideLength = length;
        state = 0;
        setDirection(Location.EAST);
    }

    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
            totalSteps++;
        }
        else if (steps < sideLength && !canMove())
        {
            return;
        }
        else if (whichDirection(state) == 1)
        {
            setDirection(Location.EAST);
            steps = 0;
            state = 2;
        }
        else if (whichDirection(state) == 2)
        {
            setDirection(Location.SOUTHWEST);
            steps = 0;
            state = 1;
        }
        if (isFinished(totalSteps))
        {
            return;
        }
    }
    public boolean isFinished(int totalSteps)
    {
        return totalSteps == THREE * sideLength;
    }
    public int whichDirection(int state)
    {
        if (steps == sideLength)
        {
            if (state == 1)
            {
                return 1;
            }
            else if (state == 0)
            {
                return 2;
            }
        }
        else
        {
            return 0;
        }
    }
}