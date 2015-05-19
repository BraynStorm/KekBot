package braynstorm.kekbot.navigator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import braynstorm.kekbot.core.Main;
import braynstorm.kekbot.navigator.navmesh.Node;

public class HuntingArea {
	public static Point center = new Point(0, 0);
	public static int radius = 0;
	
	public static boolean isPositionInsideArea(Point p){
		return (p.x - center.x) * (p.x - center.x) + (p.y - center.y) * (p.y - center.y) < radius * radius;
	}

	public static void setX(int x) {
		center.x = x;
	}

	public static void setY(int y) {
		center.y = y;
	}

	public static void setRadius(int r) {
		radius = r;
	}

	public static void navigate() {
		try {
			BufferedWriter f = new BufferedWriter(new FileWriter(Main.MAIN_FOLDER + "\\astar.txt"));
			ArrayList<Node> result = AStar(Navigator.navEurope.getNode(Navigator.curX, Navigator.curY), center.toNode());
			
			for(Node n : result){
				f.write(n.toString() + "\n");
			}
			
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Node> AStar(Node start, Node goal){
		ArrayList<Node> closedSet = new ArrayList<Node>();
		ArrayList<Node> openSet = new ArrayList<Node>();
		start.g = 0;
		start.h = distance(start,goal);
		start.f = start.h;
		openSet.add(start);
		
		
		while(true){
			Node current = null;
			
			if (openSet.size() == 0) {
	            throw new RuntimeException("no route");
	        }

	        for (Node node : openSet) {
	            if (current == null || node.f < current.f) {
	                current = node;
	            }
	        }

	        if (current == goal) {
	            break;
	        }

	        openSet.remove(current);
	        closedSet.add(current);

	        for (Node neighbor : current.neighbors) {
	            if (neighbor == null) {
	                continue;
	            }

	            int nextG = current.g + neighbor.cost;

	            if (nextG < neighbor.g) {
	                openSet.remove(neighbor);
	                closedSet.remove(neighbor);
	            }

	            if (!openSet.contains(neighbor) && !closedSet.contains(neighbor)) {
	                neighbor.g = nextG;
	                neighbor.h = distance(neighbor, goal);
	                neighbor.f = neighbor.g + neighbor.h;
	                neighbor.parent = current;
	                openSet.add(neighbor);
	            }
	        }
		}
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node current = goal;
		while (current.parent != null) {
			nodes.add(current);
			current = current.parent;
		}
		nodes.add(start);
		
		return nodes;
	}

	private static int distance(Node start, Node goal) {
		return 10*(Math.abs(start.x - goal.x) + Math.abs(start.y - goal.y));
	}
	
}
