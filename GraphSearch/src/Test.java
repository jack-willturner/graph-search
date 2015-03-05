import ilist.Function2;

import java.util.LinkedHashMap;
import java.util.Scanner;

import maybe.Predicate;

public class Test<A> {

	public static void main(String[] args) {
		
		LinkedHashMap<Integer, Node<Coordinate>> mapOfGraph = new LinkedHashMap<Integer, Node<Coordinate>>();
		
		Search<Coordinate> graphSearch = new Search<Coordinate>();
		
		mapOfGraph = parseGraph();
		
		
		System.out.println("--------------------------------------------------");
		
		System.out.println();
		System.out.println();
		
		boolean searchAgain = true;
		
		while(searchAgain) {
			Scanner in = new Scanner(System.in);
			
			System.out.println("Enter X coordinate to start from: " );
			int x1 = in.nextInt(); in.nextLine();
			
			System.out.println("Enter Y coordinate to start from: " );
			int y1 = in.nextInt(); in.nextLine();
			
			System.out.println();
			
			System.out.println("Enter X coordinate to search for: " );
			int x2 = in.nextInt(); in.nextLine();
			
			System.out.println("Enter Y coordinate to search for: " );
			int y2 = in.nextInt(); in.nextLine();
			
			int startPoint = (x1 * 7) + y1;
			final int goalPoint  = (x2 * 7) + y2;
			
			
			Node<Coordinate> startNode = mapOfGraph.get(startPoint);	
			Node<Coordinate> goalNode  = mapOfGraph.get(goalPoint);
			
			Predicate<Node<Coordinate>> predicate = new Predicate<Node<Coordinate>>() {
				public boolean holds(Node<Coordinate> x) {
					if(x.getNodeNum() == goalPoint) {
						return true;
					}
					return false;
				}
			};
			
			System.out.println("Depth first:   " + graphSearch.findPathFromDFS(mapOfGraph, startNode, predicate)); 
			System.out.println("Breadth first: " + startNode + " " + graphSearch.findPathFromBFS(mapOfGraph, startNode, predicate)); 
		
			Function2<Node<Coordinate>, Node<Coordinate>, Integer> distance = new Function2<Node<Coordinate>, Node<Coordinate>, Integer>() {
				public Integer apply(Node<Coordinate> origin, Node<Coordinate> destination) {
					
					Coordinate a = origin.getNodeContent();
					Coordinate b = destination.getNodeContent();
					
					int x1 = a.getX();
					int x2 = b.getX();
					
					int y1 = a.getY();
					int y2 = b.getY();
					
					double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
					
					return (int) distance;
				}
			};
			
			Function2<Node<Coordinate>, Node<Coordinate>, Integer> h = new Function2<Node<Coordinate>, Node<Coordinate>, Integer>() {
				public Integer apply(Node<Coordinate> origin, Node<Coordinate> goalNode) {
					
					Coordinate a = origin.getNodeContent();
					Coordinate b = goalNode.getNodeContent();
					
					int x1 = a.getX();
					int x2 = b.getX();
					
					int y1 = a.getY();
					int y2 = b.getY();
					
					double goalD = Math.abs(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
					
					return (int) goalD;

				}
			};
			
			System.out.println("A star search: " + startNode + " " + graphSearch.findPathFromAStar(mapOfGraph, startNode, goalNode, h));
		
			System.out.println();
			System.out.println("Would you like to search again? [Y/N] ");
			String opt = in.nextLine();
			if(opt.toUpperCase().equals("Y")) {
				searchAgain = true;
			} else {
				searchAgain = false;
			}
		}		
	}

	
	public static LinkedHashMap<Integer, Node<Coordinate>> parseGraph() {
		
		int [] [] nicksGraph = {
				{0,0,1,0,0,1},
				{0,1,0,0,1,1,0,2}, 
				{0,2,0,3,0,1}, 
				{0,3,0,2,0,4}, 
				{0,4,0,3,0,5}, 
				{0,5,0,6,1,5,0,4}, 
				{0,6,1,6,0,5}, 
				{1,0,0,0,1,1,2,0}, 
				{1,1,1,2,2,1,1,0,0,1}, 
				{1,2,2,2,1,1,1,3}, 
				{1,3,1,2,1,4,2,3}, 
				{1,4,2,4,1,5,1,3}, 
				{1,5,1,4,2,5,1,6,0,5}, 
				{1,6,0,6,1,5,2,6}, 
				{2,0,3,0,2,1,1,0}, 
				{2,1,2,2,1,1,2,0,3,1}, 
				{2,2,1,2,2,1,2,3,3,2}, 
				{2,3,2,2,2,4,3,3,1,3}, 
				{2,4,1,4,2,5,2,3,3,4}, 
				{2,5,2,4,1,5,2,6,3,5}, 
				{2,6,3,6,2,5,1,6}, 
				{3,0,2,0,3,1}, 
				{3,1,3,0,4,1,2,1,3,2}, 
				{3,2,2,2,4,2,3,1}, 
				{3,3,2,3,3,4}, 
				{3,4,2,4,3,3}, 
				{3,5,3,6,2,5,4,5}, 
				{3,6,2,6,3,5}, 
				{4,0}, 
				{4,1,4,2,5,1,3,1}, 
				{4,2,4,1,5,2,3,2}, 
				{4,3}, 
				{4,4}, 
				{4,5,5,5,3,5}, 
				{4,6}, 
				{5,0}, 
				{5,1,4,1,5,2,6,1}, 
				{5,2,4,2,5,1,6,2}, 
				{5,3}, 
				{5,4}, 
				{5,5,4,5,6,5}, 
				{5,6}, 
				{6,0,7,0,6,1}, 
				{6,1,6,0,5,1,6,2,7,1}, 
				{6,2,5,2,6,1,7,2}, 
				{6,3,7,3,6,4}, 
				{6,4,6,3,7,4}, 
				{6,5,5,5,6,6,7,5}, 
				{6,6,7,6,6,5}, 
				{7,0,6,0,7,1,8,0}, 
				{7,1,8,1,7,0,6,1,7,2}, 
				{7,2,7,3,8,2,6,2,7,1}, 
				{7,3,6,3,7,2,7,4,8,3}, 
				{7,4,7,3,8,4,6,4,7,5}, 
				{7,5,8,5,7,6,7,4,6,5}, 
				{7,6,6,6,7,5,8,6}, 
				{8,0,8,1,7,0,9,0}, 
				{8,1,8,2,9,1,7,1,8,0}, 
				{8,2,8,1,7,2,8,3}, 
				{8,3,8,2,7,3,8,4}, 
				{8,4,8,5,8,3,7,4}, 
				{8,5,9,5,8,4,7,5,8,6}, 
				{8,6,8,5,7,6,9,6}, 
				{9,0,9,1,8,0}, 
				{9,1,8,1,9,2,9,0}, 
				{9,2,9,1,9,3}, 
				{9,3,9,2,9,4}, 
				{9,4,9,5,9,3}, 
				{9,5,8,5,9,4,9,6}, 
				{9,6,9,5,8,6} 
			};
		LinkedHashMap<Integer, Node<Coordinate>> map = new LinkedHashMap<Integer, Node<Coordinate>>();
		
		for(int i = 0; i < nicksGraph.length; i++) {
			
			Coordinate coord = new Coordinate(nicksGraph[i][0], nicksGraph[i][1]);
			Node<Coordinate> nodeToPush = new Node<Coordinate>(coord, i);
			System.out.print("(" + nicksGraph[i][0] + ", " + nicksGraph[i][1] + "): ");
			
			for(int j = 2; j < (nicksGraph[i].length); j=j+2) {
				
				System.out.print("(" + nicksGraph[i][j] + ", " + nicksGraph[i][j+1] + ") ");
				Coordinate others = new Coordinate(nicksGraph[i][j], nicksGraph[i][j+1]);
				
				int nodeNum = (nicksGraph[i][j] * 7) + (nicksGraph[i][j+1]);
				
				Node<Coordinate> successor = new Node<Coordinate>(others, nodeNum);
				nodeToPush.addSuccessor(successor);
			}
			System.out.println();
			
			if(map.containsKey(i)) {
				map.remove(i);
			}
			map.put(i, nodeToPush);
		}
		return map;	
	}
	
}