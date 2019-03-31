import java.io.*;
import java.util.Map;
import java.util.PriorityQueue;

public class Helper {

    public static HuffmanNode makeHuffManTree(Map<Integer, Integer> frequency) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        HuffmanNode temp;
        // create forest
        for (int var : frequency.keySet()) {
            temp = new HuffmanNode(frequency.get(var), (char) var);
            temp.isLeafNode = true;
            queue.add(temp);
        }
        // while atleast 2 elemens in the queue
        while (queue.size() > 1) {
            HuffmanNode leftChild = queue.remove();
            //leftChild.isLeafNode = true;

            HuffmanNode rightChild = queue.remove();
            //rightChild.isLeafNode = true;

            HuffmanNode parentChild = new HuffmanNode(leftChild.frequency + rightChild.frequency);
            parentChild.left = leftChild;
            parentChild.right = rightChild;

            queue.add(parentChild);

        }
        return queue.remove();

    }

    public static void saveTree(HuffmanNode rootNode, String rootNodeFileName){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFile(rootNodeFileName));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rootNode);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static HuffmanNode getSavedTree(String rootNodeFileName){
        FileInputStream fileInputStream = null;
        try {

            fileInputStream = new FileInputStream(getFile(rootNodeFileName));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return(HuffmanNode) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static  InputStream getInputStream(String fileName) throws IOException {
        File inputFile = getFile(fileName);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        return inputStream;
    }

    public static  OutputStream getOuputStream(String fileName) throws IOException {
        File output = getFile(fileName);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));
        return outputStream;
    }

    public static  File getFile(String fileName){
        try {
            File output = new File(new File("").getCanonicalPath() +  "/" + fileName);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void deleteFiles() {
        File file = getFile(constants.SERIALIZED_FILE_NAME);
        file.delete();
        file = getFile(constants.ENCODED_FILE_NAME);
        file.delete();
    }
}
