

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EncodeTest {

    @org.junit.jupiter.api.Test
    void encodeFile() {

    }

    @org.junit.jupiter.api.Test
    void createCompressedFile() throws IOException {
        File output = new File(new File("").getCanonicalPath() +  "/encode.txt");
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));
        Encode e = new Encode();

        File inputFile = new File(new File("").getCanonicalPath() + "/test.txt");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        final Map<Integer, Integer> characterFrequencyOfFile = new Encode().getCharacterFrequencyOfFile(inputStream);
        Helper.makeHuffManTree(characterFrequencyOfFile);
        e.setCodeMap(Helper.makeHuffManTree(characterFrequencyOfFile));
        inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        e.createCompressedFile(inputStream,"");
    }

    @org.junit.jupiter.api.Test
    void pushTheBitsToFile() throws IOException {
     String input = "0111010100001110101010100101010";
        File output = new File(new File("").getCanonicalPath() +  "/encode.txt");
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));
        Encode e = new Encode();
        e.pushTheBitsToFile(input,outputStream);
    }
    @org.junit.jupiter.api.Test
    void pushTheBitsToFileWithOverFlow() throws IOException {

        String input = "0111010100001110101010100101010";
        File output = new File(new File("").getCanonicalPath() +  "/encode.txt");
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));
        Encode e = new Encode();
        e.overflow = "0101010";
        e.pushTheBitsToFile(input,outputStream);
    }

    @org.junit.jupiter.api.Test
    void setCodeMap() throws IOException {
        File inputFile = new File(new File("").getCanonicalPath() + "/test.txt");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        Encode encode = new Encode();
        final Map<Integer, Integer> characterFrequencyOfFile = encode.getCharacterFrequencyOfFile(inputStream);
        HuffmanNode rootNode = Helper.makeHuffManTree(characterFrequencyOfFile);
        encode.setCodeMap(rootNode);
        assertFalse(encode.codeMap.isEmpty());
        assertTrue(encode.codeMap.size() > 6);
        assertTrue(encode.codeMap.containsKey('b'));
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