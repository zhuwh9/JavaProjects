import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
public class Jumper extends Bug
{
	public Jumper()
	{
		;
	}

	public void act()
	{
		if (this.canMove())
		{
			this.move();
		}
		else
			turn();
	}

	// rewrite this function so that noting left in the old cell
	public void move()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location nextNext = next.getAdjacentLocation(getDirection());
		if (gr.isValid(nextNext))
			moveTo(nextNext);
		else
			removeSelfFromGrid();
	}
	// rewrite this function so that jumper can judge whether next two grid can be jumped to
	public boolean canMove()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location nextNext = next.getAdjacentLocation(getDirection());
		if (!gr.isValid(nextNext))
			return false;
		Actor neighbor = gr.get(nextNext);
		return (neighbor == null) || (neighbor instanceof Flower);
	}
}