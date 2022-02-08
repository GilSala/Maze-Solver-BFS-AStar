import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;




public class algorithms {

	
	static String fileName; 
	
	public  algorithms(String fileName ){
		algorithms.fileName = fileName;	
	}
	
	
	
				//---------------------------------------- BFS ----------------------------------------//
	
	
	/*Breath-First Search Algorithm that takes a Maze (by reference) in as a parameter.
	 *Uses Manhattan distance as heuristic 
	 */
	public static void BFS(MazeData maze)
	{
	
	
		System.out.println();
		
		Queue<queueNode> que = new LinkedList<>();
		ArrayList<queueNode> solutions = new ArrayList<queueNode> ();
		boolean [][]visited = new boolean[maze.M*2+1][maze.N*2+1];
		
		int NumberofNodesExpanded=0;
		int maxfrontier= 0;
		int tempmax=0;
		int depth =0;

		//push the start position on to the stack
		queueNode start = new queueNode(maze.getStartPoint(), 0);
		start.pt.setParent(null);
		que.add(start);
		
		tempmax=tempmax+1;
			
		if(tempmax> maxfrontier)
			maxfrontier=tempmax;
	
		
		while( !que.isEmpty() )
		{
			queueNode curr = que.remove();
			solutions.add(curr);
			
			tempmax=tempmax-1;
			
			if(curr.pt.getX() == maze.getEndPoint().getX() && curr.pt.getY() == maze.getEndPoint().getY())
			{
			
				ArrayList<Position> toPrint = new ArrayList<Position>();
				queueNode qN =  solutions.get(solutions.size() - 1);
				Position p = qN.pt;
				while( p.getParent() != null) {
					toPrint.add(p.getParent());
				
					p = p.getParent();
				}
				 Position[] b = new Position[toPrint.size()];
			        int j = toPrint.size();
			        for (int i = 0; i < toPrint.size(); i++) {
			            b[j - 1] = (Position) toPrint.get(i);
			            j = j - 1;
			        }
			        System.out.println("Path: ");
			        for( Position n : b) {
			        	if(n.getMove() != null)
			        	System.out.println(n.getMove()+" -> " + n );
			        	else
			        		System.out.println("Start: "+n);
			        }
				System.out.println();
				System.out.println("Cost of Search : " + (depth-1));
				System.out.println();
				System.out.println("Visit Count : " + NumberofNodesExpanded);
				System.out.println();
				return;
			}
			

			
			//add neighbors
			if( !visited[curr.pt.getX()][curr.pt.getY()] )
			{
				//update visited
				visited[curr.pt.getX()][curr.pt.getY()]=true;
				
				NumberofNodesExpanded=NumberofNodesExpanded+1;
			
				//add up neighbor
				if( canMove(maze, curr, "up", visited) )
				{
				
					queueNode temp = new queueNode
                            (new Position(curr.pt.getX()-2 , curr.pt.getY()),
                                  curr.dist + 1 );
					temp.pt.setParent(curr.pt);
					temp.pt.setMove("up");
					que.add(temp);
					tempmax=tempmax+1;
					if(tempmax> maxfrontier)
						maxfrontier=tempmax;

					if(depth < curr.dist+1)
						depth = curr.dist+1;
				}
				


				//add right neighbor
				if( canMove(maze, curr, "right", visited) )
				{
				
					queueNode temp = new queueNode
                            (new Position(curr.pt.getX() , curr.pt.getY()+2),
                                  curr.dist + 1 );
					temp.pt.setParent(curr.pt);
					temp.pt.setMove("right");
					que.add(temp);
					tempmax=tempmax+1;
					if(tempmax> maxfrontier)
						maxfrontier=tempmax;

					
					if(depth < curr.dist+1)
						depth = curr.dist+1;
				}
				
				//add down neighbor
				if( canMove(maze, curr, "down" , visited) )
				{
				
					queueNode temp = new queueNode
                            (new Position(curr.pt.getX()+2 , curr.pt.getY()),
                                  curr.dist + 1 );
					temp.pt.setParent(curr.pt);
					temp.pt.setMove("down");
					que.add(temp);
					tempmax=tempmax+1;
					if(tempmax> maxfrontier)
						maxfrontier=tempmax;
					
					if(depth < curr.dist+1)
						depth = curr.dist+1;

				}
				
				//add left neighbor
				if( canMove( maze,curr, "left" , visited ) )
				{
					queueNode temp = new queueNode
	                             (new Position(curr.pt.getX() , curr.pt.getY()-2),
	                                   curr.dist + 1 );
					
					temp.pt.setParent(curr.pt);
					temp.pt.setMove("left");
					que.add(temp);
					tempmax=tempmax+1;
					if(tempmax> maxfrontier)
						maxfrontier=tempmax;
					
					if(depth < curr.dist+1)
						depth = curr.dist+1;	
				}
				
			}
		}
		
	}


	//determines if we can move in a certain direction from the current node
	/*
	 * @param Maze m is the maze currently in
	 * @param queueNode curr is the current node containing the current position
	 * @param String s is the direction we want to move in
	 * @param boolean [][] visited stores weather place has been visited or not
	 */
	
	private static	boolean isValid(int row, int col, MazeData m)
		{
		    // return true if row number and
		    // column number is in range
		    return (row >= 0) && (row < m.M*2+1) && 
		           (col >= 0) && (col < m.N*2+1);
		}
		
	private static boolean canMove(MazeData m, queueNode curr, String s, boolean [][] visited )
	{
	
			 
         
		if(s=="right")
		{
			
			if( isValid(curr.pt.getX(), curr.pt.getY()+1, m) && m.getMaze()[curr.pt.getX()][curr.pt.getY()+1] != '+' &&  m.getMaze()[curr.pt.getX()][curr.pt.getY()+1] != '|'
					&&  m.getMaze()[curr.pt.getX()][curr.pt.getY()+1] != '-' && !visited[curr.pt.getX()][curr.pt.getY()+1])
			{
					//no wall to the right of the current position and it is inbounds of the walls
					return true;
			}
			
			else return false;
		}
		if(s=="left")
		{
			
			if(  isValid(curr.pt.getX(), curr.pt.getY()- 1, m) && m.getMaze()[curr.pt.getX()][curr.pt.getY()-1] != '+' &&  m.getMaze()[curr.pt.getX()][curr.pt.getY()-1] != '|'
					&&  m.getMaze()[curr.pt.getX()][curr.pt.getY()-1] != '-' && !visited[curr.pt.getX()][curr.pt.getY()-1])
			{
					//no wall to the left of the current position and it is inbounds of the walls
					return true;
			}
			
			else return false;
		}
		if(s=="up")
		{
	
			if( isValid(curr.pt.getX()-1, curr.pt.getY(), m) && m.getMaze()[curr.pt.getX()-1][curr.pt.getY()] != '+' &&  m.getMaze()[curr.pt.getX()-1][curr.pt.getY()] != '|'
					&&  m.getMaze()[curr.pt.getX()-1][curr.pt.getY()] != '-' && !visited[curr.pt.getX()-1][curr.pt.getY()])
			{
					//no wall above the current position and it is inbounds of the walls
					return true;
			}
			
			else return false;
		}
		if(s=="down")
		{
		
			if( isValid(curr.pt.getX()+1, curr.pt.getY(), m) && m.getMaze()[curr.pt.getX()+1][curr.pt.getY()] != '+' &&  m.getMaze()[curr.pt.getX()+1][curr.pt.getY()] != '|'
					&&  m.getMaze()[curr.pt.getX()+1][curr.pt.getY()] != '-' && !visited[curr.pt.getX()+1][curr.pt.getY()])
			{
					//no wall to the right of the current position and it is inbounds of the walls
					return true;
			}
			else 
				return false;
		}
		
		else
			return false;
		
	}
	
	
	
	
		//---------------------------------------- Best-First -Search  ----------------------------------------//
	
	
	/*Greedy-First Search Algorithm that takes a Maze (by reference) in as a parameter.
	 *The Maze class is defined in maze.java
	 */
	public static void GreedySearch(MazeData maze )
	{
		System.out.println("THIS IS GREEDY Best-First Search");
		System.out.println();
		
		Queue<Position> Gque = new LinkedList<>();
		ArrayList<Position> solutions = new ArrayList<Position> ();
		boolean [][]visited = new boolean[maze.M*2+1][maze.N*2+1];
		ArrayList< Position > frontier = new ArrayList< Position>();
		
			
		int NumberofNodesExpanded=0;
		int maxfrontier= 0;
		int depth =0;
		int solIndex=0;
		
		//push the start position on to the stack
		Position start = maze.getStartPoint();
		Position solution = maze.getEndPoint();
		
		Gque.add(start);

		while( !Gque.isEmpty() )
		{
			Position curr = Gque.remove();
			solutions.add(curr);
			solIndex=solIndex+1;
			
			if(curr.getX() == maze.getEndPoint().getX() && curr.getY() == maze.getEndPoint().getY())
			{
				//reachedEnd = true;
				
				ArrayList<Position> toPrint = new ArrayList<Position>();
				Position p =  solutions.get(solutions.size() - 1);
				
				while( p.getParent() != null) {
					toPrint.add(p.getParent());
				
					p = p.getParent();
				}
				
				 Position[] b = new Position[toPrint.size()];
			        int j = toPrint.size();
			        for (int i = 0; i < toPrint.size(); i++) {
			            b[j - 1] = (Position) toPrint.get(i);
			            j = j - 1;
			        }
				System.out.println();
				 System.out.println("Path: ");
			        for( Position n : b) {
			        	if(n.getMove() != null)
			        	System.out.println(n.getMove()+" -> " + n );
			        	else
			        		System.out.println("Start: "+n);
			        }
			      int  c=0;
				System.out.println();
				for (int row = 0; row < visited.length; row++)//Cycles through rows
				{
				  for (int col = 0; col < visited[row].length; col++)//Cycles through columns
				  {
					  if(visited[row][col]== true )
						  c++; //change the %5d to however much space you want
				  }
				}
				System.out.println("Cost : "+ toPrint.size());
				System.out.println();
				System.out.println("Visit Count : " + c);
				System.out.println();
				return;
			}
			
			if(Gque.isEmpty() && !frontier.isEmpty())
			{
				
				Position min2 = findMinFrontier( maze, frontier);
				Gque.add(min2);
			
				if(frontier.size()> maxfrontier)
					maxfrontier=frontier.size();

		}

			
			//add neighbors
			if( !visited[curr.getX()][curr.getY()] )
			{
				//update visited
				visited[curr.getX()][curr.getY()]=true;
				
				//
				NumberofNodesExpanded=NumberofNodesExpanded+1;
				
				queueNode n=new queueNode(curr , 0);
				
				//add up neighbor
				if( canMove(maze, n, "up", visited) )
				{
					Position temp = new Position(curr.getX()-2 , curr.getY());
					temp.setMove("up");
					temp.setParent(curr);
					frontier.add(temp);
				}
				
				//add left neighbor
				if( canMove( maze ,n, "left" , visited) )
				{
					Position temp = new Position(curr.getX(), curr.getY()-2);
					temp.setMove("left");
					temp.setParent(curr);
					frontier.add(temp);
					
				}
				
				//add down neighbor
				if( canMove(maze, n, "down", visited) )
				{
					Position temp = new Position(curr.getX()+2 , curr.getY());
					temp.setMove("down");
					temp.setParent(curr);
					frontier.add(temp);

				}
				

				//add right neighbor
				if( canMove(maze, n, "right", visited) )
				{
					Position temp = new Position(curr.getX(), curr.getY()+2);
					temp.setMove("right");
					temp.setParent(curr);
					frontier.add(temp);
				}

				
				Position min = findMinFrontier( maze, frontier);
				
				Gque.add(min);
	
			
				if(frontier.size()> maxfrontier)
					maxfrontier=frontier.size();
			
				if(depth < min.heuristic(min, solution))
					depth = min.heuristic(min, solution)+1;	
			}	
		}
		}
	
	
	//heuristic for greedy best search
		/*
		 * @param Maze maze is the the current maze we are in
		 * @param ArrayList<Position> frontier is the frontier of our search. Unexpanded nodes.
		 */
		private static Position findMinFrontier( MazeData maze, ArrayList<Position> frontier) {
		
			Position minPair= null;
			int distance;
			
			for(int i=0; i < frontier.size(); i++)
			{
				Position temp = frontier.get(i);
				distance =  ( Math.abs( temp.getX() - maze.getEndPoint().getX() ) +  Math.abs( temp.getY() - maze.getEndPoint().getY() ));
						
				if(minPair == null)
				{
					minPair = temp;
				}
				else 
				{
					int minDist = ( Math.abs( temp.getX() - maze.getEndPoint().getX() ) +  Math.abs( temp.getY() - maze.getEndPoint().getY() ));
					if(minDist > distance)
					{
						minPair = temp;
					}
					if (minDist == distance) {
						if(temp.getMove().equals("up") && !minPair.equals("up")) {
							minPair = temp;
						}	
						else if(temp.getMove().equals("right") 
								&& !minPair.getMove().equals("up")
								&& !minPair.getMove().equals("rigth")) {
							minPair = temp;
						}
						else if(temp.getMove().equals("down") 
								&& !minPair.getMove().equals("up")
								&& !minPair.getMove().equals("rigth")
								&& !minPair.getMove().equals("down")) {
							minPair = temp;
						}
						}

			}
			}
			frontier.remove(minPair);
			return minPair;
		}
		
		

		
		
				//---------------------------------------- A Star ----------------------------------------//
		
		
		/* A* Search Algorithm that takes a Maze (by reference) in as a parameter.
		 * The Maze class is defined in maze.java
		 * @param Maze maze is the maze we are in 
		 */
		public static void Astar(MazeData maze )
		{
			
			//queue data structure
			System.out.println("THIS IS A* Search");
			System.out.println();
			
			Queue<Position> Aque = new LinkedList<>();
			ArrayList<Position> solutions = new ArrayList<Position> ();
			boolean [][]visited = new boolean[maze.M*2+1][maze.N*2+1];
			ArrayList< Position > frontier = new ArrayList< Position>();
			
			int solIndex=0;
			int NumberofNodesExpanded=0;
			int depth =0;
			int maxfrontier= 0;
			int countFromStart=0;
			
			//push the start position on to the stack
			Position start = maze.getStartPoint();
			start.setCostFromStart(countFromStart);
			Aque.add( start );
		
			
			while( !Aque.isEmpty() )
			{
				Position curr = Aque.remove();
				solutions.add(curr); 
				countFromStart++;
				solIndex=solIndex+1;
				
		
				if(curr.getX() == maze.getEndPoint().getX() && curr.getY() == maze.getEndPoint().getY())
				{

					ArrayList<Position> toPrint = new ArrayList<Position>();
					Position p =  solutions.get(solutions.size() - 1);
					
					while( p.getParent() != null) {
						toPrint.add(p.getParent());
					
						p = p.getParent();
					}
					
					 Position[] b = new Position[toPrint.size()];
				        int j = toPrint.size();
				        for (int i = 0; i < toPrint.size(); i++) {
				            b[j - 1] = (Position) toPrint.get(i);
				            j = j - 1;
				        }
				        System.out.println();
						 System.out.println("Path: ");
					        for( Position n : b) {
					        	if(n.getMove() != null)
					        	System.out.println(n.getMove()+" -> " + n );
					        	else
					        		System.out.println("Start: "+n);
					        }
						      int  c=0;
								System.out.println();
								for (int row = 0; row < visited.length; row++)//Cycles through rows
								{
								  for (int col = 0; col < visited[row].length; col++)//Cycles through columns
								  {
									  if(visited[row][col]== true )
										  c++; //change the %5d to however much space you want
								  }
								}
						System.out.println();
						System.out.println("Cost : " + toPrint.size());
						System.out.println();
						System.out.println("Visit Count : " + c);
						System.out.println();
					return;
				}
				
				
				if(Aque.isEmpty() && !frontier.isEmpty())
				{
					
					Position min2 = findMinFrontierWithCost( maze, frontier);
					Aque.add(min2);
				
					if(frontier.size()> maxfrontier)
						maxfrontier=frontier.size();
				
					if(depth < min2.heuristic(min2,maze.getEndPoint()))
						depth = min2.heuristic(min2,maze.getEndPoint())+1;
				}
		
				
				//add neighbors
				if( !visited[curr.getX()][curr.getY()] )
				{
					//update visited
					visited[curr.getX()][curr.getY()]=true;
					
					//
					NumberofNodesExpanded=NumberofNodesExpanded+1;
					
					queueNode n=new queueNode(curr , 0);
					
					
					
					//add left neighbor
					if( canMove( maze,n, "left", visited) )
					{
						
						Position temp = new Position(curr.getX() , curr.getY()-2);
						temp.setMove("left");
						temp.setParent(curr);
						temp.setCostFromStart(countFromStart);
						frontier.add(temp);
					}
					
					//add down neighbor
					if( canMove(maze, n, "down", visited) )
					{

						Position temp = new Position(curr.getX()+2 , curr.getY());
						temp.setMove("down");
						temp.setParent(curr);
						temp.setCostFromStart(countFromStart);
						frontier.add(temp);
		
					}
					
					//add up neighbor
					if( canMove(maze, n, "up", visited) )
					{

						Position temp = new Position(curr.getX()-2 , curr.getY());
						temp.setMove("up");
						temp.setParent(curr);
						temp.setCostFromStart(countFromStart);
						frontier.add(temp);
					}
		
		
					//add right neighbor
					if( canMove(maze, n, "right", visited) )
					{

						Position temp = new Position(curr.getX() , curr.getY()+2);
						temp.setMove("rigth");
						temp.setParent(curr);
						temp.setCostFromStart(countFromStart);
						frontier.add(temp);
					}
		
					
					Position min = findMinFrontierWithCost( maze, frontier);
					
					if(min != null)
					Aque.add(min);
		
				
					if(frontier.size()> maxfrontier)
						maxfrontier=frontier.size();
				

				}
			}
		}
		
		
		
		
		//Heuristic for A* search
		/*
		 * @param Maze maze is the the current maze we are in
		 * @param ArrayList<pair> frontier is the frontier of our search. Unexpanded nodes.
		 */
		private static Position findMinFrontierWithCost(MazeData maze, ArrayList<Position> frontier) {
			Position minPair= null;
			int distance;
			
			for(int i=0; i < frontier.size(); i++)
			{
				Position temp = frontier.get(i);
			
			distance = 	temp.getCostFromStart() +( Math.abs( temp.getX() - maze.getEndPoint().getX() ) +  Math.abs( temp.getY() - maze.getEndPoint().getY() ));
				if(minPair == null)
				{
					minPair = temp;
				}
				else 
				{
					int minDist = ( minPair.getCostFromStart() +( Math.abs( minPair.getX() - maze.getEndPoint().getX() ) +  Math.abs( minPair.getY() - maze.getEndPoint().getY() )));
					if(minDist >= distance)
					{
						minPair = temp;
					}
					
				}

			}
			frontier.remove(minPair);
			return minPair;

		}

		
		
		
		
		
		//------------------------
	

}