

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EncodeTest {

    @org.junit.jupiter.api.Test
    void encodeFile() {

    }

    @org.junit.jupiter.api.Test
    void createCompressedFile() {
    }

    @org.junit.jupiter.api.Test
    void psuhTheBitsToFile() {
    }

    @org.junit.jupiter.api.Test
    void setCodeMap() {
    }

    @org.junit.jupiter.api.Test
    void getCharacterFrequencyOfFile() throws IOException {
        File inputFile = new File(new File("").getCanonicalPath() + "/test.txt");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        final Map<Integer, Integer> characterFrequencyOfFile = new Encode().getCharacterFrequencyOfFile(inputStream);
        assertNotNull(characterFrequencyOfFile);
        assertTrue(characterFrequencyOfFile.size() > 6);
    }
}