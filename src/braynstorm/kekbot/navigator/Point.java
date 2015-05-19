package braynstorm.kekbot.navigator;

import braynstorm.kekbot.navigator.navmesh.Node;

public class Point {
	public int x;
	public int y;
	public int z;


	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		z = 0;
	}
	
	public Point(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(){
		x = 0;
		y = 0;
		z = 0;
	}

	public void setCoords(int X, int Y, int Z){
		x = X;
		y = Y;
		z = Z;
	}
	
	public short[] toShortArray() {
		return new short[] {
			(short) x, (short) y, (short) z	
		};
	}
	
	public int[] toIntArray() {
		return new int[] {
			x,  y,  z	
		};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
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
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public Node toNode() {
		return Navigator.getNodeFromPoint(this);
	}
}
