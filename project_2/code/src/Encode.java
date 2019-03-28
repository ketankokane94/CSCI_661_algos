import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Encode {
    int  BYTE_SIZE = 8;
    Map<Character, String> codeMap = new Hashtable<>();

    /**
     *
     * @param input
     * @param outputFileName
     * @throws IOException
     */

    public void encodeFile(InputStream input, String outputFileName) throws IOException {

        Map<Integer, Integer> frequencies = getCharacterFrequencyOfFile(input);
        HuffmanNode rootNode = Helper.makeHuffManTree(frequencies);
        setCodeMap(rootNode);
        createCompressedFile(input,outputFileName);
        System.out.println(codeMap.toString());
    }

    private void createCompressedFile(InputStream input,String outputFileName) throws IOException {
        File output = new File(new File("").getCanonicalPath() +  "/encode.txt");
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));

        int inputChar;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputChar = input.read()) != -1) {
            stringBuilder.append(codeMap.get(inputChar));
            if (stringBuilder.length() > 1000){
                int EOFFound = psuhTheBitsToFile(stringBuilder.toString(), outputStream);
                stringBuilder = new StringBuilder();
            }
        }
    }

    private int psuhTheBitsToFile(String string, OutputStream outputStream) throws IOException {
        String temp = "";
        byte[] buffer = new byte[10000];
        for (int i = 0; i < 1000; i+=7) {
            temp = string.substring(i, i+7);
            buffer[0] = (byte) Integer.parseInt(temp, 2);
        }
        outputStream.write(buffer);
        return 0;

    }

    private void setCodeMap(HuffmanNode rootNode) {
        postOrderTraversal(rootNode, "");
    }

    private void postOrderTraversal(@NotNull HuffmanNode node, String code) {
        if (node.isLeafNode) {
            codeMap.put(node.character, code);
            return;
        }
        postOrderTraversal(node.left, code + "0");
        postOrderTraversal(node.right, code + "1");
    }


    /**
     * @param input
     * @return
     * @throws IOException
     */
    private Map<Integer, Integer> getCharacterFrequencyOfFile(@NotNull InputStream input) throws IOException {
        Map<Integer, Integer> frequencies = new HashMap<>();

        int inputChar;
        while ((inputChar = input.read()) != -1) {
            if (frequencies.containsKey(inputChar)) {
                // update the count value
                frequencies.put(inputChar, frequencies.get(inputChar) + 1);
            } else {
                // add the character in the
                frequencies.put(inputChar, 1);
            }
        }
        // GET MAX INT FROM KEY
        Set<Integer> keys = frequencies.keySet();
        Iterator iterator = keys.iterator();
        int max = Integer.MIN_VALUE;
        while (iterator.hasNext()) {
            int temp = frequencies.get(iterator.next());
            if (temp > max) {
                max = temp;
            }
        }

        frequencies.put(max, 1);
        return frequencies;
    }
}
