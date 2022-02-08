
public class MazeRunner {

	static final String FILE_NAME= "/Users/gylslmndr/eclipse-workspace/HW_1_AI/src/HW1-input-files/";
	
	//static final String FILE_ME= "/Users/gylslmndr/eclipse-workspace/HW_1_AI/src/HW1-input-files/nocycles2.txt";


	public static void main(String[] args) {
		
//		MazeData data = new MazeData(FILE_ME); 
//		  algorithms.BFS(data);
//		  algorithms.GreedySearch(data);
//		  algorithms.Astar(data);
		  
	 String mazePath =	FILE_NAME+args[1] ;

		MazeData data = new MazeData(mazePath); 
				
		new algorithms(FILE_NAME );
		
		switch(args[0]) {
		  case "bfs": 
			 System.out.println("Alg Name : BFS");
			 System.out.println("Input :"+ args[1]);
			  algorithms.BFS(data);
		    break;
		  case "best":
			 System.out.println("Alg Name : Best first search");
			 System.out.println("Input :"+ args[1]);
		    algorithms.GreedySearch(data);
		    break;
		  case "A*":
			 System.out.println("Alg Name : A star");
			 System.out.println("Input :"+ args[1]);
			  algorithms.Astar(data);
			    break;   
		  default:
		    System.out.println("Only recieves BFS or GREEDY or ASTAR");
		}
		
		
	
	
	}
	
}

