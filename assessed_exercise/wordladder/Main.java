
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
		
		System.out.println("------------ \n wordladder\n");
		HashSet<String> words = new HashSet<>();


		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary filename
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word



		System.out.println("word1 = " + word1 + "\nword2 = " + word2 + "\n");





		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data here
		while(in.hasNextLine()){
			String line = in.nextLine().trim();
			words.add(line);
		}


		in.close();
		reader.close();




		Graph g = new Graph(words.size());

		HashMap<String, Integer> wordToIndex = new HashMap<>();
		ArrayList<String> indexToWord = new ArrayList<>();
		int index = 0;
		for(String word: words){
			wordToIndex.put(word,index);
			indexToWord.add(word);
			index++;
		}
		/*&
		 * 		for(int i = 0; i < words.size();i++){
			g.setVertex(i);
		}
		 */


		// create graph here


        
		// do the work here
		

		//build the adjacency lists
		for(String word : words){
			int wordIndex = wordToIndex.get(word);
			Vertex vertex = g.getVertex(wordIndex);

			List<String> neighbours = Util.generateNeighbors(word, words);

			for(String neighbour : neighbours){
				int neighbourIndex = wordToIndex.get(neighbour);
				vertex.addToAdjList(neighbourIndex);

				Vertex neighbourVertex = g.getVertex(neighbourIndex);
				neighbourVertex.addToAdjList(wordIndex);
			}
		}
		Integer startIndex = wordToIndex.get(word1);
		Integer endIndex = wordToIndex.get(word2);

		if(startIndex == null || endIndex == null){
			System.out.println("Start or end word not in dictionary");
			return;
		}

		List<Integer> pathIndices = g.bfs(startIndex, endIndex);

		if (pathIndices == null) {
			System.out.println("No ladder possible.");
		} else {
			int pathLength = pathIndices.size() - 1;
			System.out.println("Length of shortest path: " + pathLength);
		
			// Convert indices back to words and print the ladder
			System.out.println("shortest word ladder:");
			for (int idx : pathIndices) {
				String word = indexToWord.get(idx);
				System.out.println(word);
			}
		}




		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
		System.out.println("---------------\n");
	}

}
