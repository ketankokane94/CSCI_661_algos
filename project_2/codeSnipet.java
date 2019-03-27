import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable{

    public char character;
    public int frequency;

    public HuffmanNode left;
    public HuffmanNode right;

    public boolean isLeafNode;

    public HuffmanNode(int frequency){
        HuffmanNode(frequency,"");
    }

    public HuffmanNode (int frequency, char character){
        this.character = character;
        this.frequency = frequency;
    }

    public int compareTo(HuffmanNode huffmanNode){
        if (this.frequency < huffmanNode.frequency){
            return -1;
        }
        else if(this.frequency > huffmanNode.frequency){
            return 1;
        }
        else{
            return 0;
        }
        
    }
}

class CreateHuffmanTree{

    public HuffmanNode createTree(Map<Character,Integer> frequency){

    PriorityQueue<HuffmanNode> queue  = new PriorityQueue<>();
    HuffmanNode temp ;
    for (char var : frequency.keySet()) {
        temp = new HuffmanNode(frequency.get(var), var);
        temp.isLeafNode = True; 
        queue.add(temp);
    }
    // while atleast 2 elemens in the queue
    while(queue.size() > 1){
        HuffmanNode leftChild = queue.remove();
        //leftChild.isLeafNode = true;

        HuffmanNode rightChild = queue.remove();
        //rightChild.isLeafNode = true;

        HuffmanNode parentChild = new HuffmanNode(leftChild.frequency + rightChild.frequency );
        parentChild.left = leftChild;
        parentChild.right = rightChild;

        queue.add(parentChild);
    
    }
    return queue.remove();
}
}
class Encode{
    File InputFile; 
    File OutputFile;
//HuffmanNode rootNode;
Map <Character,Integer> frequency = new HashMap<>();

InputStream is ;
int input;
while ((input = is.read())!= -1) {
    char c = (char) input;
    if (frequency.containsKey(c)){
        // update the count value
    }
    else{
        frequency.put(c, 1);
    }
    
}

if (input == -1){
// manage pseudo EOF
}

}