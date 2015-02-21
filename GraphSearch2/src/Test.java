import java.util.LinkedHashMap;


public class Test<A> {

	public static void main(String[] args) {
		
		LinkedHashMap<Integer, Node<Coordinate>> mapOfGraph = new LinkedHashMap<Integer, Node<Coordinate>>();
		
		mapOfGraph = parseGraph();
		
		int startPoint    = 0;
		int goalPoint     = 60;
		
		Node<Coordinate> startNode = mapOfGraph.get(startPoint);
		Node<Coordinate> goalNode  = mapOfGraph.get(goalPoint);
		
		Search<Coordinate> graphSearch = new Search<Coordinate>();
		
		System.out.println();
		System.out.println();
		
		System.out.println(graphSearch.DFS(mapOfGraph, startNode, goalNode));
		System.out.println(graphSearch.BFS(mapOfGraph, startNode, goalNode));
		
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