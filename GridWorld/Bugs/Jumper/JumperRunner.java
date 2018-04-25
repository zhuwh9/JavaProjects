import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class JumperRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		Jumper alice = new Jumper();
		world.add(new Location(4,4), alice);
		world.add(new Rock());
		world.add(new Flower());
		world.show();
	}
}