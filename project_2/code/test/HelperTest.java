import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void makeHuffManTree() throws IOException {
        File inputFile = new File(new File("").getCanonicalPath() + "/test.txt");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        final Map<Integer, Integer> characterFrequencyOfFile = new Encode().getCharacterFrequencyOfFile(inputStream);
        final HuffmanNode huffmanNode = Helper.makeHuffManTree(characterFrequencyOfFile);
        assertNotNull(huffmanNode);
        assertFalse(huffmanNode.isLeafNode);
        assertTrue(huffmanNode.frequency == 9);
    }
}