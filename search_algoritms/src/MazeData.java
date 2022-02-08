import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MazeData {

public int getN() {
		return N;
	}
	public int getM() {
		return M;
	}
	
	
public char[][] getMaze() {
		return maze;
	}
	public void setMaze(char[][] maze) {
		this.maze = maze;
	}

	public int[][] getMaze2() {
		return maze2;
	}
	
public int N,M;
private char[][] maze;
private int[][] maze2;

	@Override
public String toString() {
	return "MazeData [maze=" + Arrays.toString(maze) + "]";
}
	public MazeData(String fileName) {
		Scanner scanner = null; 
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
			String nmLine = scanner.nextLine();
			
			String[] nm = nmLine.split("\\s+");
			N= Integer.parseInt(nm[0]);
			M= Integer.parseInt(nm[1]);
			maze = new char[M+M+1][N+N+1];
			maze2 = new int[M+M+1][N+N+1];
			
			for( int i=0; i< M+M+1 ; i++) {
			String line = scanner.nextLine();
			System.out.println(line);
			
			for( int j=0; j<N+N +1 ; j++) {
			maze[i][j]=line.charAt(j);
			if(maze[i][j] == ' ') maze2[i][j] = 1;
			else if (maze[i][j] == '*')  maze2[i][j] = 1;
			else if (maze[i][j] == 'X')  maze2[i][j] = 1;
			else maze2[i][j] = 0;
			
			
			}
			}
			System.out.println("N= "+ N);
			System.out.println("M= "+ M);
		}catch(Exception e) {
			e.printStackTrace();		
			}finally {
				if(scanner != null)
				{
					//System.out.println(Arrays.deepToString(maze));
				
				//	System.out.println(Arrays.deepToString(maze2));
//					 for (int i = 0; i < maze2.length; i++)
//				        {
//				            for (int j = 0; (maze2[i] != null && j < maze2[i].length); j++) {
//				                System.out.print(maze2[i][j] + " ");
//				            }
//				 
//				            System.out.println();
//				        }
					scanner.close();
				}
			}
		
}
	
	public Position getStartPoint() {
		for (int row = 0; row < maze.length; row++) {
	        for (int col = 0; col < maze[row].length; col++) {
	            char value = maze[row][col];
	            if (value == '*') {
	            	Position s_p = new Position(row, col);
	              return s_p;
	            }
	        }
	    }
		return null;
	}
	
	public Position getEndPoint() {
		for (int row = 0; row < maze.length; row++) {
	        for (int col = 0; col < maze[row].length; col++) {
	            char value = maze[row][col];
	            if (value == 'X') {
	            	Position e_p = new Position(row, col);
	              return e_p;
	            }
	        }
	    }
		return null;
	}

}
