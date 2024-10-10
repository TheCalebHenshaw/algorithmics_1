import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		String inputFileName = "/Users/calebhenshaw/Workspace/year3/algorithmics_1/Lab_Exercise/lab exercise/input.txt";
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
        Scanner lineScanner = new Scanner(line);
		int numVertices = lineScanner.nextInt();
		
		

        // insert code here to build the graph from the input file

        // create empty graph with correct number of vertices
		Graph graph = new Graph(numVertices);

		for(int i = 0; i < numVertices; i++){
			for(int j = 0; j < numVertices; j++){
				int value = in.nextInt();
				if(value==1){
					graph.getVertex(i).addToAdjList(j);
				}
			}
		}

        // then go through input line by line adding edges to the graph
        
		reader.close();

		graph.bfs();
		
		String outputFileName = "output.txt";
		FileWriter writer = new FileWriter(outputFileName);
		graph.outputParents(writer);
		// insert code here to output the predecessor information

		writer.close();

	}

}
