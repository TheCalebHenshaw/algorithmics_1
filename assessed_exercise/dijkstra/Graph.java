import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

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




	//dijkstras for program 2
	public List<Integer> dijkstra(int startIndex, int endIndex) {
        int[] distances = new int[numVertices];
        int[] predecessors = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        // Initialize distances and predecessors
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[startIndex] = 0;

        // Priority queue to select vertex with minimal distance
        PriorityQueue<VertexDistance> queue = new PriorityQueue<>();
        queue.add(new VertexDistance(startIndex, 0));

        while (!queue.isEmpty()) {
            VertexDistance vd = queue.poll();
            int currentIndex = vd.vertexIndex;

            if (visited[currentIndex]) continue;
            visited[currentIndex] = true;

            if (currentIndex == endIndex) break; // Reached destination

            Vertex currentVertex = getVertex(currentIndex);

            for (AdjListNode adjNode : currentVertex.getAdjList()) {
                int neighborIndex = adjNode.getVertexIndex();
                int weight = adjNode.getWeight();

                if (!visited[neighborIndex]) {
                    int newDist = distances[currentIndex] + weight;
                    if (newDist < distances[neighborIndex]) {
                        distances[neighborIndex] = newDist;
                        predecessors[neighborIndex] = currentIndex;
                        queue.add(new VertexDistance(neighborIndex, newDist));
                    }
                }
            }
        }

        // Reconstruct the path
        List<Integer> path = new LinkedList<>();
        if (distances[endIndex] == Integer.MAX_VALUE) {
            return null; // No path found
        } else {
            for (int at = endIndex; at != -1; at = predecessors[at]) {
                path.add(0, at);
            }
        }
        return path;
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
