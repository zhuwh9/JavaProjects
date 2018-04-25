public class SparseGridNode
{
	private Object obj;
	private int col;
	private SparseGridNode nextNode;

	public SparseGridNode(Object o, int c, SparseGridNode n) {
		obj = o;
		col = c;
		nextNode = n;
	}

	public Object getOccupant()
	{
		return obj;
	}

	public int getCol()
	{
		return col;
	}

	public SparseGridNode getNext()
	{
		return nextNode;
	}

	public void setOccupant(Object o)
	{
		obj = o;
	}

	public void setNext(SparseGridNode n)
	{
		nextNode = n;
	}
}