import java.io.Serializable;

class HuffmanNode implements Comparable<HuffmanNode>, Serializable {
    /**
     *
     */
    public char character;
    public int frequency;

    public HuffmanNode left;
    public HuffmanNode right;

    public boolean isLeafNode;

    public HuffmanNode(int frequency) {
        this(frequency, '@');
    }

    public HuffmanNode(int frequency, char character) {
        this.character = character;
        this.frequency = frequency;
    }


    /**
     *
     * @param huffmanNode
     * @return
     */
    @Override
    public int compareTo(HuffmanNode huffmanNode) {

        if (this.frequency < huffmanNode.frequency) {
            return -1;
        } else if (this.frequency > huffmanNode.frequency) {
            return 1;
        } else {
            return 0;
        }
    }
}