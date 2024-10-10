import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
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
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	*/
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexIndex();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	*/
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	/**
	 carry out a breadth first search/traversal of the graph
	 psedocode version
	 */
	public void bfs() {
		for(Vertex v : vertices){
			v.setVisited(false);
		}
		Queue<Vertex> q = new LinkedList<>();
		for(Vertex v: vertices){
			if(!v.getVisited()){
				v.setVisited(true);
				v.setPredecessor(-1);
				q.add(v);
				while(!q.isEmpty()){
					Vertex u = q.remove();
					for(AdjListNode n : u.getAdjList()){
						int neighborIndex = n.getVertexIndex();
						Vertex w = vertices[neighborIndex];
						if(!w.getVisited()){
							w.setVisited(true);
							w.setPredecessor(u.getIndex());
							q.add(w);
						}
					}
				}
			}
		}


	}
	    public void outputParents(FileWriter writer) throws IOException {
        StringBuilder output = new StringBuilder();
        for (Vertex v : vertices) {
            int parent = v.getPredecessor();
            // If the vertex is a root, its parent should be itself
            if (parent == -1) {
                parent = v.getIndex();  // Make the vertex its own parent if it's the root
            }
            output.append(parent).append(" ");
        }
        writer.write(output.toString().trim() + "\n");
    }

}
       			


		/* 
		assign each vertex to be unvisited;
  		set up an initially empty queue of  
         		visited but unprocessed vertices;
  		for each vertex v {
    		if v is not visited {
      			assign v to be visited;
      			assign the predecessor of v;
      			add v to the queue;
      			while the queue is not empty {
        			remove vertex u from the queue;
        			for each vertex w in the adjacency list of u {
        				if w is unvisited {
           					assign w to be visited;
           					assign the predecessor of w;
           					add w to the queue;
						*/