import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Util {
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
