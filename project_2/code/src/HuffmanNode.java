class HuffmanNode implements Comparable<HuffmanNode> {

    public char character;
    public int frequency;

    public HuffmanNode left;
    public HuffmanNode right;

    public boolean isLeafNode;

    public HuffmanNode(int frequency) {
        this(frequency, ' ');
    }

    public HuffmanNode(int frequency, char character) {
        this.character = character;
        this.frequency = frequency;
    }


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