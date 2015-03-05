import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import maybe.Just;
import maybe.Maybe;
import maybe.Nothing;
import maybe.Predicate;
import ilist.Function2;

public class Search<A> {
	
	public Maybe<Node<A>> DFS(LinkedHashMap<Integer, Node<A>> map, 
			Node<A> startNode, Predicate<A> p) {
		
		
		Stack<A> stack = new Stack<A>();
		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
		
		stack.push(startNode);
			
		while(!stack.isEmpty()) {
			Node<A> x     = stack.pop();
			
			if(!(alreadyExplored(explored, x))) {
				
				if(p.holds(x.getNodeContent())) {
					@SuppressWarnings("unchecked")
					Maybe<Node<A>> r = (Maybe<Node<A>>) new Just<A>(x.getNodeContent());
					
					return r;
				}
				
				explored.add(x);
				
				for(Node<A> succ: x.getSuccessors()) {
					if(!(alreadyExplored(explored, succ))) {
						stack.push(map.get(succ.getNodeNum()));
					}
				}

			}
		}
		@SuppressWarnings("unchecked")
		Maybe<Node<A>> r = (Maybe<Node<A>>) new Nothing<A>();
		return r;
	}
	
	
	public Maybe<Node<A>> BFS(LinkedHashMap<Integer, Node<A>> map, 
			Node<A> startNode, Predicate<A> p) {
		
		Queue<A> queue = new Queue<A>(startNode);
		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
		 
		while(!queue.isEmpty()) {
					
			Node<A> x = queue.dequeue();
			
			if(!(alreadyExplored(explored, x))) {
				if(p.holds(x.getNodeContent())) {
					
					@SuppressWarnings("unchecked")
					Maybe<Node<A>> r = (Maybe<Node<A>>) new Just<A>(x.getNodeContent());
					
					return r;
				}
				/*if(x.toString().equals(goalNode.toString())) {
					return x;
				}
				*/
				
				explored.add(x);
			}	
			
			for(Node<A> succ: x.getSuccessors()) {
				if(!(alreadyExplored(explored, succ))) {
					queue.enqueue(map.get(succ.getNodeNum()));
				}
			}
			
		}	
		@SuppressWarnings("unchecked")
		Maybe<Node<A>> r = (Maybe<Node<A>>) new Nothing<A>();
		return r;
	}
	
	public boolean alreadyExplored(ArrayList<Node<A>> explored, Node<A> searchNode) {
		//System.out.println("Visited: ");
		for(int i = 0; i < explored.size(); i++) {
			//System.out.println(explored.get(i).toString());
			if(explored.get(i).toString().equals(searchNode.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean successorsExplored(ArrayList<Node<A>> explored, Node<A> n) {
		
		for(Node<A> succ: n.getSuccessors()) {
			if(!(alreadyExplored(explored, succ))) {
				return false;
			}
		}
		
		return true;
	}
	
	public Stack<A> findPathFrom(LinkedHashMap<Integer, Node<A>> map, DataStructure<A> struct, Node<A> startNode, Predicate<Node<A>> p) {
		
		Stack<A> path  = new Stack<A>();
		struct.push(startNode);
	
		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
		
		LinkedHashMap<Integer, Integer> route = new LinkedHashMap<Integer, Integer>();
		
		 
		while(!struct.isEmpty()) {
					
			Node<A> x = struct.pop();
			
			if(!(alreadyExplored(explored, x))) {
				if(p.holds(x)) {
					int backtrack = x.getNodeNum();
					while(backtrack != startNode.getNodeNum()) {
						path.push(map.get(backtrack));
						backtrack = route.get(backtrack);
					}
					return path;
				}
				
				explored.add(x);
				
				if(x.isLeaf()) {
					path.pop();
					Node<A> y = path.pop();
					while(successorsExplored(explored, y)) {
						y = path.pop();
					}
					path.push(y);
				} else {
					for(Node<A> succ: x.getSuccessors()) {
						if(!(alreadyExplored(explored, succ))) {
							struct.push(map.get(succ.getNodeNum()));
							route.put(succ.getNodeNum(), x.getNodeNum());
						}
					}
				}
			}
		}	
		
		return new Stack<A>();
	}
	
	
	
	
	
	
	/** Non-generalised DFS method
	 * 
	 * @param map
	 * @param startNode
	 * @param p
	 * @return
	public Stack<A> findPathFromDFS(LinkedHashMap<Integer, Node<A>> map, Node<A> startNode, Predicate<Node<A>> p) {
		
		
		Stack<A> stack = new Stack<A>();
		Stack<A> path  = new Stack<A>();
		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
		
		stack.push(startNode);
			
		while(!stack.isEmpty()) {
			Node<A> x     = stack.pop();
			path.push(x);
			
			if(!(alreadyExplored(explored, x))) {
				
				if(p.holds(x)) {
					return reorderStack(path);
				}
				
				explored.add(x);
				
				if(x.isLeaf()) {
					path.pop();
					Node<A> y = path.pop();
					while(successorsExplored(explored, y)) {
						y = path.pop();
					}
					path.push(y);
				} else {
					for(Node<A> succ: x.getSuccessors()) {
						if(!(alreadyExplored(explored, succ))) {
							stack.push(map.get(succ.getNodeNum()));
						}
					}
				}
				

			}
		}
		return new Stack<A>();
	}
	*/
	
	/*
	public Stack<A> findPathFromBFS(LinkedHashMap<Integer, Node<A>> map, Node<A> startNode, Predicate<Node<A>> p) {
	
		Stack<A> path  = new Stack<A>();
		Queue<A> queue = new Queue<A>(startNode);
	
		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
		
		LinkedHashMap<Integer, Integer> route = new LinkedHashMap<Integer, Integer>();
		
		 
		while(!queue.isEmpty()) {
					
			Node<A> x = queue.dequeue();
			
			if(!(alreadyExplored(explored, x))) {
				if(p.holds(x)) {
					int backtrack = x.getNodeNum();
					while(backtrack != startNode.getNodeNum()) {
						path.push(map.get(backtrack));
						backtrack = route.get(backtrack);
					}
					return path;
				}
				
				explored.add(x);
				
				if(x.isLeaf()) {
					path.pop();
					Node<A> y = path.pop();
					while(successorsExplored(explored, y)) {
						y = path.pop();
					}
					path.push(y);
				} else {
					for(Node<A> succ: x.getSuccessors()) {
						if(!(alreadyExplored(explored, succ))) {
							queue.enqueue(map.get(succ.getNodeNum()));
							route.put(succ.getNodeNum(), x.getNodeNum());
						}
					}
				}
			}
		}	
		
		return new Stack<A>();
	}
	*/

	
	public Stack<A> findPathFromAStar(LinkedHashMap<Integer, Node<A>> map, Node<A> startNode, Node<A> destination,
			Function2<Node<A>, Node<A>, Integer> h) {
		
		Stack<A> path  = new Stack<A>();
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

		gethVals(map, h, destination);

		ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
		LinkedHashMap<Integer, Integer> route = new LinkedHashMap<Integer, Integer>();

		pending.add(startNode);
		
		while(!pending.isEmpty()) {
					
			Node<A> x = pending.poll();
			
			if(!(alreadyExplored(explored, x))) {
				if(x.toString().equals(destination.toString())) {
					int backtrack = x.getNodeNum();
					while(backtrack != startNode.getNodeNum()) {
						path.push(map.get(backtrack));
						backtrack = route.get(backtrack);
					}
					return path;
				}
				
				explored.add(x);
				
				if(x.isLeaf()) {
					path.pop();
					Node<A> y = path.pop();
					while(successorsExplored(explored, y)) {
						y = path.pop();
					}
					path.push(y);
				} else {
					for(Node<A> succ: map.get(x.getNodeNum()).getSuccessors()) {
						if(!(alreadyExplored(explored, succ))) {
							succ.increasefVal();
							pending.add(map.get(succ.getNodeNum()));
							route.put(succ.getNodeNum(), x.getNodeNum());
						}
					}
				}
			}
	}
	return new Stack<A>();	
	}

	public void gethVals (LinkedHashMap<Integer, Node<A>> map, Function2<Node<A>, Node<A>, 
		Integer> h, Node<A> destination) {
		for(int i = 0; i < map.size(); i++) {
			int hv = h.apply(map.get(i), destination);
			map.get(i).setHVal(hv);
		}
	
	}


	public Stack<A> reorderStack(Stack<A> stack) {
		
		Stack<A> copy = new Stack<A>();
		
		for(Node<A> n: stack) {
			copy.push(n);
		}
		return copy;	
	}
}




/*
Stack<A> path  = new Stack<A>();
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

gethVals(map, h, destination);

ArrayList<Node<A>> explored = new ArrayList<Node<A>>();
LinkedHashMap<Integer, Integer> route = new LinkedHashMap<Integer, Integer>();

while(!pending.isEmpty()) {
			
	Node<A> x = pending.poll();
	
	if(!(alreadyExplored(explored, x))) {
		if(x.toString().equals(destination.toString())) {
			int backtrack = x.getNodeNum();
			while(backtrack != startNode.getNodeNum()) {
				path.push(map.get(backtrack));
				backtrack = route.get(backtrack);
			}
			return path;
		}
		
		explored.add(x);
		
		if(x.isLeaf()) {
			path.pop();
			Node<A> y = path.pop();
			while(successorsExplored(explored, y)) {
				y = path.pop();
			}
			path.push(y);
		} else {
			for(Node<A> succ: map.get(x.getNodeNum()).getSuccessors()) {
				if(!(alreadyExplored(explored, succ))) {
					succ.increasefVal();
					pending.add(map.get(succ.getNodeNum()));
					route.put(succ.getNodeNum(), x.getNodeNum());
				}
			}
		}
	}
}	
*/