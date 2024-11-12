import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest distance for two words in a dictionary
 distance between elements of the word ladder is the absolute difference in the
 positions of the alphabet of the non-matching letter

 this is a weighted version of the wordladder
 example: position of r - l is 6; because l -> m -> n -> o -> p -> q -> r

 */
public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("dijkstras \n ----------- \n");
		long start = System.currentTimeMillis();


		
		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
		

		System.out.println(word1 + "\n" + word2 + "\n");



		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		

		HashSet<String> words = new HashSet<>();


		// read in the data here
		while(in.hasNextLine()){
			String line = in.nextLine().trim();
			words.add(line);

		}
		reader.close();
		in.close();


		// create graph here
		Graph g = new Graph(words.size());
		HashMap<String, Integer> wordToIndex = new HashMap<>();
		ArrayList<String> indexToWord = new ArrayList<>();
		int index = 0;
		for(String word: words){
			wordToIndex.put(word,index);
			indexToWord.add(word);
			index++;
		}


		// do the work here

		//build the adjacency lists
		// Build the adjacency lists
	for (String word : words){
		int wordIndex = wordToIndex.get(word);
		Vertex vertex = g.getVertex(wordIndex);

    	List<String> neighbours = generateNeighbors(word, words);

		for (String neighbour : neighbours){
			Integer neighbourIndex = wordToIndex.get(neighbour);
			if (neighbourIndex == null) {
				continue; // Skip if neighbour not in dictionary
			}

				// Calculate the weight of the edge
				int weight = calculateWeight(word, neighbour);

				// Add edge with weight to the adjacency list
				vertex.addToAdjList(neighbourIndex, weight);

				// Since the graph is undirected, add the edge in the other direction
				Vertex neighbourVertex = g.getVertex(neighbourIndex);
				neighbourVertex.addToAdjList(wordIndex, weight);
    }
}

		Integer startIndex = wordToIndex.get(word1);
		Integer endIndex = wordToIndex.get(word2);

		if(startIndex == null || endIndex == null){
			System.out.println("Start or end word not in dictionary");
			return;
		}

		List<Integer> pathIndices = g.dijkstra(startIndex, endIndex);

		if(pathIndices==null){
			System.out.println("no path exists");
		}else{
			int totalDistance = 0;



			for(int i = 0; i < pathIndices.size()-1; i++){
				int fromIndex = pathIndices.get(i);
				int toIndex = pathIndices.get(i+1);

				int weight = 0;

				for(AdjListNode adjNode : g.getVertex(fromIndex).getAdjList()){
					if(adjNode.getVertexIndex() == toIndex){
						weight = adjNode.getWeight();
						break;
					}
				}
				totalDistance += weight;
			}
			System.out.println("Minimum path distance: " + totalDistance);

			for(int idx : pathIndices){
				String word = indexToWord.get(idx);
				System.out.println(word);
			}
		}




		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}
	
	
	
	//hekper methods

	//make put a helper function here for Dijkstras algorithm?
	public static int calculateWeight(String word1, String word2){
		for(int i = 0; i < word1.length(); i++){
			if(word1.charAt(i) != word2.charAt(i)){
				int pos1 = word1.charAt(i) - 'a';
				int pos2 = word2.charAt(i) - 'a';
				return Math.abs(pos1 - pos2);
			}
		}
		return 0; 

	}
	public static List<String> generateNeighbors(String word, Set<String> dictionary){ //this is a helper method to generate all possible neighbors
        List<String> neighbours = new ArrayList<>();
        char[] wordChars = word.toCharArray();

        for(int i = 0; i < wordChars.length; i++){
            char originalChar = wordChars[i];

            for(char c = 'a'; c<= 'z'; c++){
                if(c !=originalChar){
                    wordChars[i]=c;
                    String newWord = new String(wordChars);
                    if(dictionary.contains(newWord)){
                        neighbours.add(newWord);
                    }
                }
            }
            wordChars[i] = originalChar;
        }
        return neighbours;
    }


}
