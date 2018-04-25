import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;

public class JumperTest
{
	public static ActorWorld world = new ActorWorld();
	public static Jumper jumper1 = new Jumper();
	public static Jumper jumper2 = new Jumper();
	public static Jumper jumper3 = new Jumper();
	public static Jumper jumper4 = new Jumper();
	public static Jumper jumper5 = new Jumper();
	public static Jumper jumper6 = new Jumper();
	public static Jumper jumper7 = new Jumper();
	public static Jumper jumper8 = new Jumper();
	public static Jumper jumper9 = new Jumper();
	public static Jumper jumper10 = new Jumper();
	public static Rock rock = new Rock();
	public static Flower flower = new Flower();
	public static Actor actor = new Actor();
	public static Bug bug = new Bug();
	@BeforeClass
	public static void setUp()throws Exception
	{
		/* case #1
			jumper jumps to location with rock */
		world.add(new Location(6,2), jumper1);
		world.add(new Location(4,2), rock);
		/* case #2
			jumper jumps to location with flower*/
		world.add(new Location(6,3), jumper2);
		world.add(new Location(4,3), flower);
		/* case #3
			jumper face the edge of grid */
		world.add(new Location(0,2), jumper3);
		/* case #4
			jumper jump out of grid */
		world.add(new Location(1,2), jumper4);
		/* case #5
			jumper jump to location with actor */
		world.add(new Location(6,4), jumper5);
		world.add(new Location(4,4), actor);
		/* case #6
			jumper jump to location with bug */
		world.add(new Location(6,5), jumper6);
		world.add(new Location(4,5), bug);
		/* case #7
			jumper jump to location with bug */
		world.add(new Location(6,6), jumper7);
		world.add(new Location(4,8), jumper8);
		/* case #8
			jumper jump to location with bug */
		world.add(new Location(8,6), jumper9);
		world.add(new Location(8,8), jumper10);
		world.show();
	}
	
	@Test
	public void testOnJumpingToLocationWithRock()
	{
		assertEquals(false, jumper1.canMove());
	}

	@Test
	public void testOnJumpingToLocationWithFlower()
	{
		assertEquals(true, jumper2.canMove());
	}

	@Test
	public void testOnFacingEdgeOfGrid()
	{
		assertEquals(false, jumper3.canMove());
	}

	@Test
	public void testOnJumpingOutOfGrid()
	{
		assertEquals(false, jumper4.canMove());
	}

	@Test
	public void testOnJumpingToLocationWithActor()
	{
		assertEquals(false, jumper5.canMove());
	}

	@Test
	public void testOnJumpingToLocationWithBug()
	{
		bug.act();
		assertEquals(true, jumper6.canMove());
	}

	@Test
	public void testOnTwoJumpersJumpingToSameLocation()
	{
		jumper8.setDirection(270);
		assertEquals(true, jumper7.canMove());
		jumper7.act();
		assertEquals(false, jumper8.canMove());
		jumper8.act();
		assertEquals(true, jumper8.canMove());
	}

	@Test
	public void testOnAnotherJumperOnPath()
	{
		jumper9.setDirection(90);
		jumper10.setDirection(0);
		assertEquals(false, jumper9.canMove());
		jumper10.act();
		assertEquals(true, jumper9.canMove());
	}
}