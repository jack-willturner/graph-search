







import java.util.ArrayList;

public class Search<A> {
	
	public Node<Coordinate> DFS(Node<Coordinate> startNode, Node<Coordinate> goalNode) {
		
		Stack<Coordinate> stack = new Stack<Coordinate>();
		stack.push(startNode);
		
		assert(!stack.isEmpty());
		
		ArrayList<Node<Coordinate>> explored = new ArrayList<Node<Coordinate>>();
		
		while(!stack.isEmpty()) {
			//System.out.println(stack.top.content);
			
			Node<Coordinate> x = stack.pop();

			if(!(alreadyExplored(explored, x))) {
				//System.out.println();
				//System.out.println("X:       " + x.getNodeContent());
				//System.out.println("Goal:    " + goalNode.getNodeContent());
				if(x.toString().equals(goalNode.toString())) {
					return x;
				}
				
				//System.out.println("     " + x.getNodeContent());
				//System.out.println(x.getSuccessors());
				explored.add(x);
				
				for(Node<Coordinate> succ: x.getSuccessors()) {
					if(!(alreadyExplored(explored, succ))) {
						//System.out.print("Adding successor: " + succ.toString());
						//System.out.println("         Searching for: " + succ.toString());
						stack = refillStack();
						stack.push(stack.findNode(stack, succ.toString()));
					}
				}
			}
			//System.out.println("here");
		}	
		return startNode;		
	}	
	
	public boolean alreadyExplored(ArrayList<Node<Coordinate>> explored, Node<Coordinate> searchNode) {
		//System.out.println("Visited: ");
		for(int i = 0; i < explored.size(); i++) {
			//System.out.println(explored.get(i).toString());
			if(explored.get(i).toString().equals(searchNode.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public Stack<Coordinate> refillStack() {
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
		Stack<Coordinate> stack = new Stack<Coordinate>();
		
		for(int i = 0; i < nicksGraph.length; i++) {
			
			Coordinate coord = new Coordinate(nicksGraph[i][0], nicksGraph[i][1]);
			Node<Coordinate> nodeToPush = new Node<Coordinate>(coord);
			
			for(int j = 2; j < (nicksGraph[i].length); j=j+2) {
				Coordinate others = new Coordinate(nicksGraph[i][j], nicksGraph[i][j+1]);
				Node<Coordinate> successor = new Node<Coordinate>(others);
				nodeToPush.addSuccessor(successor);
			}
			stack.push(nodeToPush);
		}
		return stack;

	}
	
	
	public Queue<Coordinate> refillQueue() {
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
		Queue<Coordinate> queue = new Queue<Coordinate>();
		
		for(int i = 0; i < nicksGraph.length; i++) {
			
			Coordinate coord = new Coordinate(nicksGraph[i][0], nicksGraph[i][1]);
			Node<Coordinate> nodeToQueue = new Node<Coordinate>(coord);
			
			for(int j = 2; j < (nicksGraph[i].length); j=j+2) {
				Coordinate others = new Coordinate(nicksGraph[i][j], nicksGraph[i][j+1]);
				Node<Coordinate> successor = new Node<Coordinate>(others);
				nodeToQueue.addSuccessor(successor);
			}
			queue.enqueue(nodeToQueue);
		}
		return queue;

	}
	
	public Node<Coordinate> BFS(Node<Coordinate> node, Node<Coordinate> goalNode) {
		
		Queue<Coordinate> queue = new Queue<Coordinate>(node);
		
		ArrayList<Node<Coordinate>> explored = new ArrayList<Node<Coordinate>>();
		
		while(!queue.isEmpty()) {
					
			Node<Coordinate> x = queue.dequeue();
			
			System.out.print(x.toString());
			System.out.println("    Successors: " + x.getSuccessors());
			
			if(!(alreadyExplored(explored, x))) {
				
				//if(p.holds(x.getNodeContent())) {
				if(x.toString().equals(goalNode.toString())) {
					return x;
				}
				
				explored.add(x);
			}	
			
			for(Node<Coordinate> succ: x.getSuccessors()) {
				if(!(alreadyExplored(explored, succ))) {
					//System.out.print("Adding successor: " + succ.toString());
					//System.out.println("         Searching for: " + succ.toString());
					
					queue.enqueue(queue.findNode(queue, succ.toString()));
				}
			}
			
		}	
		return node;
	}
}
