import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
	private Map<Location, E> arrMap;
	private int col;
	private int row;

	public SparseBoundedGrid2(int r, int c)
	{
		arrMap = new HashMap<Location, E>();
		col = c;
		row = r;
	}

	public int getNumCols()
	{
		return col;
	}

	public int getNumRows()
	{
		return row;
	}

	public boolean isValid(Location loc)
	{
		return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
	}

	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		for (Location loc : arrMap.keySet())
		{
			locs.add(loc);
		}
		return locs;
	}

	public E get(Location loc)
	{
		if (loc == null)
		{
			throw new NullPointerException("Location "+loc+" is null");
		}
		return arrMap.get(loc);
	}

	public E put(Location loc, E obj)
	{
		if (loc == null)
		{
			throw new NullPointerException("Location "+loc+" is null");
		}
		if (obj == null)
		{
			throw new NullPointerException("Object obj is null");
		}
		return arrMap.put(loc, obj);
	}

	public E remove(Location loc)
	{
		if (loc == null)
		{
			throw new NullPointerException("Location "+loc+" is null");
		}
		return arrMap.remove(loc);
	}
}
