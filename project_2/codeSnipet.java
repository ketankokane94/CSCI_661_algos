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

    public HuffmanNode createTree(Map<K,V> <Character,Integer> frequency){

    PriorityQueue<HuffmanNode> queue  = new PriorityQueue<>();
    
    for (char var : frequency.keySet()) {
        queue.add(new HuffmanNode(frequency.get(var), var));
    }
    // while atleast 2 elemens in the queue
    while(queue.size() > 1){
        HuffmanNode leftChild = queue.remove();
        leftChild.isLeafNode = true;

        HuffmanNode rightChild = queue.remove();
        rightChild.isLeafNode = true;

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
    File Ou;
}