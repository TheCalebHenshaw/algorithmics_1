import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 creates a new instance of Graph with n vertices
	*/
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}



	/**
	 carry out a breadth first search/traversal of the graph
	 */
	public List<Integer> bfs(int startIndex, int endIndex) {
		
		for (Vertex v : vertices){
			v.setVisited(false);
			v.setPredecessor(-1); //this indicates no predecessor
		}
  		Queue<Vertex> queue = new LinkedList<>(); // queue to process
  		Vertex startVertex = getVertex(startIndex);
		startVertex.setPredecessor(-1);
		startVertex.setVisited(true);
		queue.add(startVertex);
		while(!queue.isEmpty()){
			Vertex currentVertex = queue.remove();
			if(currentVertex.getIndex() == endIndex){
				break;
			}
			for(AdjListNode neighbourNode : currentVertex.getAdjList()){
				Vertex neighbourVertex = getVertex(neighbourNode.getVertexIndex());
				if(!neighbourVertex.getVisited()){
					neighbourVertex.setVisited(true);
					neighbourVertex.setPredecessor(currentVertex.getIndex());
					queue.add(neighbourVertex);
				}
			}
		}
		List<Integer> path = new LinkedList<>();
		Vertex endVertex = getVertex(endIndex);

		if(endVertex.getVisited()){
			int currentIndex = endIndex;

			while(currentIndex != -1){
				path.add(0,currentIndex);
				currentIndex = getVertex(currentIndex).getPredecessor();
			}
		}else{
			path = null;
		}
		return path;
		
	}

}
