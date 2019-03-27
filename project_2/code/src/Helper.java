import java.util.Map;
import java.util.PriorityQueue;

public class Helper {

    public static  HuffmanNode makeHuffManTree(Map<Integer,Integer>  frequency){
        PriorityQueue<HuffmanNode> queue  = new PriorityQueue<>();

        HuffmanNode temp;
        // create forest
        for (int var : frequency.keySet()) {
            temp = new HuffmanNode(frequency.get(var), (char) var);
            temp.isLeafNode = true;
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
