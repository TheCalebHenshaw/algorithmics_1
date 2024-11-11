
import java.io.*;
import java.util.*;



/**
 program to find word ladder with shortest path (i.e. minimum number edges)

use bfs which is well suited to find the shortest path in an unweighted graph

Begin from the start word and explore neighbors by changing one letter at a time, checking if each transformation
is in the dictionary


 */
public class Main {

	public static void main(String[] args) throws IOException {
		

		HashSet<String> words = new HashSet<>();


		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary filename
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data here
		while(in.hasNextLine()){
			String line = in.nextLine();
			words.add(line);
		}

		Graph g = new Graph(0);

		// create graph here

		reader.close();

        
		// do the work here
		





		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
