import ilist.Function2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearch<A> {
	
	Map<Node<A>, Node<A>> route = new LinkedHashMap<Node<A>, Node<A>>();
	
	public Stack<A> findNodeFrom(LinkedHashMap<Integer, Node<A>> map, Node<A> startNode, Node<A> goalNode, 
			Function2<Node<A>, Node<A>, Integer> distance, 
			Function2<Node<A>, Node<A>, Integer> h) {
	
		Stack<A> path = new Stack<A>();

		PriorityQueue<Node<A>> pending = new PriorityQueue<Node<A>>(
				15, new Comparator<Node<A>> () {
					public int compare(Node<A> node1, Node<A> node2) {
						if(node1.fVal > node2.fVal) {
							return 1;
						} else if (node1.fVal < node2.fVal) {
							return -1;
						}
						return 0;
					}
				});
		
		pending.add(startNode);
		
		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
			
		while(! pending.isEmpty()) {
			Node<A> n = pending.poll();
			
			if(n.toString().equals(goalNode.toString())) {
				System.out.println(route);
				int backtrack = n.getNodeNum();
				while(backtrack != startNode.getNodeNum()) {
					path.push(map.get(backtrack));
					backtrack = route.get(backtrack).getNodeNum();
				}
				path.push(n);
				return path;
				
			} else {
				if(! explored.contains(n)) {
					for(Node<A> s : map.get(n.getNodeNum()).getSuccessors()) {
						if(!(explored.contains(map.get(s.getNodeNum())))) {
							s.increasefVal();
							pending.add(s);
							route.put(n, s);
						}
					}
					explored.add(map.get(n.getNodeNum()));
				}
			}
			
			
		}
		
		throw new RuntimeException("No route found");
	}

	
	public void gethVals (LinkedHashMap<Integer, Node<A>> map, Function2<Node<A>, Node<A>, 
							Integer> h, Node<A> destination) {
		for(int i = 0; i < map.size(); i++) {
			int hv = h.apply(map.get(i), destination);
			map.get(i).setHVal(hv);
		}
		
	}
	
	
}