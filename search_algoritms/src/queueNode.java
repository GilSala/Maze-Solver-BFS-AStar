import java.util.Comparator;

public class queueNode {
	
	    Position pt; // The cordinates of a cell
	    int dist; // cell's distance of from the source
	 
	    public queueNode(Position pt, int dist)
	    {
	        this.pt = pt;
	        this.dist = dist;
	    }

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return super.equals(obj);
		}
		
		public int compareTo(queueNode x)
	    {
	        return this.dist;
	    }

		public static Comparator<queueNode> idComparator = new Comparator<queueNode>(){
			
			

			@Override
			public int compare(queueNode o1, queueNode o2) {
				// TODO Auto-generated method stub
				return o1.dist-o2.dist;
			}
		};
	}

