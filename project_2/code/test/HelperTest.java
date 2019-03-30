import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void makeHuffManTree() throws IOException {
        final Map<Integer, Integer> characterFrequencyOfFile = new Encode().getCharacterFrequencyOfFile("test.txt");
        final HuffmanNode huffmanNode = Helper.makeHuffManTree(characterFrequencyOfFile);
        assertNotNull(huffmanNode);
        assertFalse(huffmanNode.isLeafNode);
        assertTrue(huffmanNode.frequency == 9);
    }
}