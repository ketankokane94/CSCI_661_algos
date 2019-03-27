import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Encode {

    public void encodeFile(InputStream input, String outputFileName) throws IOException {

        Map<Integer, Integer> frequencies = getCharacterFrequencyOfFile(input);
        HuffmanNode rootNode = Helper.makeHuffManTree(frequencies);

        Map<Character, Integer> codeMap = new Hashtable<>();

        Set<HuffmanNode> visited = new HashSet<>();
        Stack<HuffmanNode> discovered = new Stack<>();
        discovered.push(rootNode);
        int index = 0;
        BitSet bit = new BitSet();
        while(!discovered.empty()){
            HuffmanNode currentNode = discovered.pop();
            if (currentNode.isLeafNode){
                // add entry into the hashtable
                codeMap.put(currentNode.character, 0);

            }
            else {
                currentNode = currentNode.left;

            }

        }

    }

    private Map<Integer, Integer> getCharacterFrequencyOfFile(InputStream input) throws IOException {
        Map<Integer, Integer> frequencies = new HashMap<>();

        int inputChar;
        while ((inputChar = input.read())!= -1) {
            if (frequencies.containsKey(inputChar)){
                // update the count value
                frequencies.put(inputChar, frequencies.get(inputChar) + 1);
            }
            else{
                // add the character in the
                frequencies.put(inputChar, 1);
            }
        }
        // GET MAX INT FROM KEY
        Set<Integer> keys = frequencies.keySet();
        Iterator iterator = keys.iterator();
        int max = Integer.MIN_VALUE;
        while(iterator.hasNext()){
            int temp = frequencies.get(iterator.next());
            if (temp > max){
                max = temp;
            }
        }

        frequencies.put(max, 1);
        return frequencies;
    }
}
