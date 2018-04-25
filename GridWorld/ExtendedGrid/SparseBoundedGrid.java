import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] arr;
    private int cols;
    private int rows;

    public SparseBoundedGrid(int r, int c)
    {
        if (r <= 0) throw new IllegalArgumentException("Row should be larger than 0");
        if (c <= 0) throw new IllegalArgumentException("Colomn should be larger than 0");
        rows = r;
        cols = c;
        arr = new SparseGridNode[r];
        for (int i = 0; i < r; i++)
        {
            arr[i] = null;
        }
    }

    public int getNumCols()
    {
        return cols;
    }

    public int getNumRows()
    {
        return rows;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int i = 0; i < getNumRows(); i++)
        {
            SparseGridNode pointer = arr[i];
            while (pointer != null)
            {
                Location loc = new Location(i, pointer.getCol());
                locs.add(loc);
                pointer = pointer.getNext();
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
        SparseGridNode pointer = arr[loc.getRow()];
        while (pointer != null)
        {
            if (loc.getCol() == pointer.getCol())
            {
                return (E)pointer.getOccupant();
            }
            pointer = pointer.getNext();
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location "+loc+" is not valid");
        }
        if (obj == null)
        {
            throw new NullPointerException("Object is null");
        }
        E oldOne = remove(loc);
        SparseGridNode pointer = arr[loc.getRow()];
        arr[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), pointer);
        return oldOne;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location "+loc+"is not valid");
        }
        E obj = get(loc);
        if (obj == null)
        {
            return null;
        }
        SparseGridNode pointer = arr[loc.getRow()];
        if (pointer != null)
        {
            if (pointer.getCol() == loc.getCol())
            {
                arr[loc.getRow()] = pointer.getNext();
            }
            else
            {
                SparseGridNode inspector = pointer.getNext();
                while (inspector != null && inspector.getCol() != loc.getCol())
                {
                    pointer = pointer.getNext();
                    inspector = inspector.getNext();
                }

                if (inspector != null)
                {
                    pointer.setNext(inspector.getNext());
                }
            }
        }
        return obj;
    }
}