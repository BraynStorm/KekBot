package braynstorm.kekbot.navigator.navmesh;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import braynstorm.kekbot.core.Main;
import braynstorm.kekbot.navigator.Point;
import braynstorm.kekbot.navigator.Sector;


public class NavMeshRegion {
	
	private static final int FILE_ENTRY_SIZE = 2 * Short.SIZE;
	
	public byte linkID;
	
	public Sector startSector;
	public Sector endSector;
	
	public int regionStartX = 0;
	public int regionStartY = 0;
	public int regionEndX = 0;
	public int regionEndY = 0;
	
	public ArrayList<NavMeshTeleport> links;
	public ArrayList<Node> nodes;
	
	public Node[][] nodesArray = new Node[30000][2000];
	
	
	public void addLinkTo(byte id){
		//if(links.contains(arg0))
	}
	
	/**
	 * Create a new NavigationMesh Region (ex. Easter Europe, Minor Asia, etc)
	 * @param start starting region
	 * @param end end region (Like the 2 points that define a rectangle)
	 * @param linkID the ID that is used to determine where this region can take you to (different region).
	 */
	@Deprecated
	public NavMeshRegion(byte linkID, Sector start, Sector end) {
		startSector = start;
		endSector = end;
		
	}
	
	/**
	 * Create a new NavigationMesh Region from a file located at the specified path
	 * File name:
	 * 	[startSectorX]x[startSectorY]-[endSectorX]x[endSectorY].nvmr
	 * File structure (shorts):
	 * 	x,y 
	 * 	x,y
	 * 	x,y....
	 * @param name Path to the file
	 * @throws IOException 
	 */
	public NavMeshRegion(String name, byte linkID) throws IOException{
		if(name.endsWith(".nvmr")){
			nodes = new ArrayList<Node>();
			this.linkID = linkID;
			
			DataInputStream input = new DataInputStream(new FileInputStream(Main.MAIN_FOLDER + "\\nvms\\" + name));
			
			String filename = name.substring(0, name.lastIndexOf("."));
			
			String[] parts = filename.split("-");
			String[] sector = parts[0].split("x");
			
			startSector = new Sector(Integer.parseInt(sector[0]), Integer.parseInt(sector[1]));
			sector = parts[1].split("x");
			endSector = new Sector(Integer.parseInt(sector[0]), Integer.parseInt(sector[1]));
			int k = 0;
			while(input.available() >= FILE_ENTRY_SIZE){
				short x, y;
				x = input.readShort();
				y = input.readShort();
				nodes.add(new Node(x, y));
				System.out.println(k);
				k++;
			}
			
			input.close();
			populateNeighbors();
		}else{
			throw new IllegalArgumentException("Filename is wrong: " + name);
		}
	}
	
	private void populateNeighbors(){
		/*for(Node n : nodes){
			for(short i = -1; i < 1; i++)
				for(short j = -1; j < 1; j++)
					if(!(i == 0 && j == 0)){
						Node buddy = getNode(n.x - i, n.y - j);
						if(buddy != null)
							n.neighbors[((i+1) * 3) + j + 1] = buddy;
					}
			
			System.out.println(c);
			c++;
		}
		
		int nodespassed = 0;
		for(int i = 0; i < nodesArray.length; i++){
			for(int j = 0; j < nodesArray[i].length; j++){
				if(nodesArray[i][j] == null)
					continue;
				
				for(short k = -1; k < 2; k++){
					for(short l = -1; l < 2; l++){
						Node neighbor = nodesArray[i-k][j-l];
						if(neighbor != null)
							nodesArray[i][j].neighbors[((k+1) * 3) + l + 1] =  neighbor;
					}
				}
				System.out.println(nodespassed);
				nodespassed++;
			}
		}*/
		
	}
	
	public Node getNode(int x, int y){
		for(Node n : nodes){
			if(n.x == x && n.y == y)
				return n;
		}
		return null;
	}
	
	private void calculateStartAndEndXY(){
		
	}
}

class NavMeshTeleport{
	public byte linkSource;
	public byte linkDest;
	
	public Point sourceCoords;
	public Point destCoords;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destCoords == null) ? 0 : destCoords.hashCode());
		result = prime * result + linkDest;
		result = prime * result + linkSource;
		result = prime * result
				+ ((sourceCoords == null) ? 0 : sourceCoords.hashCode());
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
		NavMeshTeleport other = (NavMeshTeleport) obj;
		if (destCoords == null) {
			if (other.destCoords != null)
				return false;
		} else if (!destCoords.equals(other.destCoords))
			return false;
		if (linkDest != other.linkDest)
			return false;
		if (linkSource != other.linkSource)
			return false;
		if (sourceCoords == null) {
			if (other.sourceCoords != null)
				return false;
		} else if (!sourceCoords.equals(other.sourceCoords))
			return false;
		return true;
	}
}