import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.*;

public class UnboundedGrid2<E> extends AbstractGrid<E>
{
	private Object[][] arr;
	private int length;

	public UnboundedGrid2()
	{
		length = 16;
		arr = new Object[length][length];
	}

	public int getNumCols()
	{
		return -1;
	}

	public int getNumRows()
	{
		return -1;
	}

	public boolean isValid(Location loc)
	{
		return loc.getRow() >= 0 && loc.getCol() >= 0;
	}

	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		for (int i = 0; i < length; i++)
		{
			for (int j = 0; j < length; j++)
			{
				Location loc = new Location(i, j);
				if (get(loc) != null)
				{
					locs.add(loc);
				}
			}
		}
		return locs;
	}

	public E get(Location loc)
	{
		if (!isValid(loc))
		{
			throw new IllegalArgumentException("Location "+loc+" is not valid");
		}
		if (loc.getRow() >= length || loc.getCol() >= length)
		{
			return null;
		}
		return (E)arr[loc.getRow()][loc.getCol()];
	}

	public E put(Location loc, E obj)
	{
		if (loc == null)
		{
			throw new NullPointerException("Location is null");
		}
		if (obj == null)
		{
			throw new NullPointerException("Object is null");
		}
		if (loc.getRow() >= length || loc.getCol() >= length)
		{
			resize(loc);
		}
		E oldOccupant = get(loc);
		arr[loc.getRow()][loc.getCol()] = obj;
		return oldOccupant;
	}

	public E remove(Location loc)
	{
		if (!isValid(loc))
		{
			throw new IllegalArgumentException("Location "+loc+" is not valid");
		}
		if (loc.getRow() >= length || loc.getCol() >= length)
		{
			return null;
		}
		E obj = get(loc);
		arr[loc.getRow()][loc.getCol()] = null;
		return obj;
	}

	private void resize(Location loc)
	{
		int num = length;
		while (loc.getRow() >= num || loc.getCol() >= num)
		{
			num *= 2;
		}
		Object[][] temp = new Object[num][num];
		for (int i = 0; i < length; i++)
		{
			for (int j = 0; j < length; j++)
			{
				temp[i][j] = arr[i][j];
			}
		}
		arr = temp;
		length = num;
	}
}