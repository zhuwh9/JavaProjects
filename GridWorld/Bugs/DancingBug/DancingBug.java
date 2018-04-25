import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
public class DancingBug extends Bug
{
    private int[] array;
    private int loop;
    public DancingBug(int[] arr)
    {
        loop = 0;
        array = new int[arr.length];
        System.arraycopy(arr,0,array,0,arr.length);
    }
    public void act()
    {
        setDirection((array[loop] * Location.HALF_RIGHT) % Location.FULL_CIRCLE);
        move();
        loop = (loop + 1) % array.length;
    }
}