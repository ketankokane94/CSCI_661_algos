import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;


public class Encode {
    private String inputFileName;
    private String encodedFileName;
    private final int BYTE_SIZE;
    private Map<Integer, String> codeMap;
    private String overflow;

    public Encode(String inputFileName) {
        this.inputFileName = inputFileName;
        this.encodedFileName = constants.ENCODED_FILE_NAME;
        BYTE_SIZE = 8;
        codeMap = new Hashtable<>();
        overflow = new String();
    }


    public void encodeFile(boolean useEstimatedFrequency) throws IOException {
        Map<Integer, Integer> characterFrequencyOfFile;

        characterFrequencyOfFile = Helper.getCharacterFrequencyOfFile(inputFileName, useEstimatedFrequency);

        HuffmanNode rootNode = Helper.makeHuffManTree(characterFrequencyOfFile);
        setCodeMap(rootNode);
        createCompressedFile(inputFileName, encodedFileName);
        Helper.saveTree(rootNode, constants.SERIALIZED_FILE_NAME);
    }


    //TODO: make private
    public void createCompressedFile(@NotNull String inputFileName, String outputFileName) throws IOException {
        InputStream input = Helper.getInputStream(inputFileName);
        OutputStream outputStream = Helper.getOuputStream(outputFileName);
        int inputChar;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputChar = input.read()) != -1) {
            if(codeMap.get(inputChar) != null){
                stringBuilder.append(codeMap.get(inputChar));
            }
            if (stringBuilder.length() > 10000) {
                pushTheBitsToFile(stringBuilder.toString(), outputStream);
                stringBuilder = new StringBuilder(overflow);
                overflow = new String();
            }
        }

        stringBuilder.append(overflow);
        overflow = new String();
        // insert end of file
        //stringBuilder.append("");
        if (stringBuilder.length() > 0) {
            pushTheBitsToFile(stringBuilder.toString(), outputStream);
        }
        if (overflow.length() > 0) {
            byte buffer = (byte) Integer.parseInt(overflow, 2);
            outputStream.write(buffer);
        }
        outputStream.flush();
    }

    //TODO: make private
    public void pushTheBitsToFile(String string, OutputStream outputStream) throws IOException {
        String temp = "";

        int i = 0;

        for (; i + BYTE_SIZE < string.length(); i += BYTE_SIZE) {
            temp = string.substring(i, i + BYTE_SIZE);
            outputStream.write((byte) Integer.parseInt(temp, 2));
        }
        // check if any bits are remaining
        if (i < string.length()) {
            // this means less than 8 bits are remaining
            overflow = string.substring(i);
        } else {
            overflow = new String();
        }
    }

    //TODO: make private
    // change string to string Builder
    public void setCodeMap(HuffmanNode rootNode) {
        postOrderTraversal(rootNode, "");
    }
    //TODO: make public

    private void postOrderTraversal(@NotNull HuffmanNode node, String code) {
        if (node.isLeafNode) {
            codeMap.put((int) node.character, code);
            return;
        }
        postOrderTraversal(node.left, code + "0");
        postOrderTraversal(node.right, code + "1");
    }


    //TODO: make private


    public long getFileSize(String s) {
        File file = Helper.getFile(s);
        if (file.isFile()) {
            return file.length();
        }
        return 0;
    }
}
