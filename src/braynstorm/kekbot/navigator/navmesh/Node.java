package braynstorm.kekbot.navigator.navmesh;

public class Node {
	public int x, y;
	public Node[] neighbors = new Node[9];
	/*
	 * A*
	 */
	public int f,g,h,cost = 5;
	public Node parent;
	

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Node(){
		
	}
	
	
	
	public short[] toShortArray() {
		return new short[]{
				(short) x, (short) y
		};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + f;
		result = prime * result + g;
		result = prime * result + h;
		result = prime * result
				+ ((neighbors == null) ? 0 : neighbors.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (cost != other.cost)
			return false;
		if (f != other.f)
			return false;
		if (g != other.g)
			return false;
		if (h != other.h)
			return false;
		if (neighbors == null) {
			if (other.neighbors != null)
				return false;
		} else if (!neighbors.equals(other.neighbors))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
