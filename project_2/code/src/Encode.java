import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.*;


public class Encode {
    final int  BYTE_SIZE ;
    Map<Integer, String> codeMap ;
    String overflow ;


    public Encode() {
        BYTE_SIZE = 8;
        codeMap = new Hashtable<>();
        overflow  = new String();
    }


    public void encodeFile(String inputFileName , String outputFileName) throws IOException {
        Map<Integer, Integer> characterFrequencyOfFile = getCharacterFrequencyOfFile(inputFileName);
        HuffmanNode rootNode = Helper.makeHuffManTree(characterFrequencyOfFile);
        setCodeMap(rootNode);
        createCompressedFile(inputFileName,outputFileName);
        Helper.saveTree(rootNode,constants.SERIALIZED_FILE_NAME);
        System.out.println(codeMap.size());
    }


    private int calculateSize(Map<Integer, Integer> characterFrequencyOfFile) {
    int size =0 ;
        for(int currentChar : characterFrequencyOfFile.keySet())
        {
        size = size + (characterFrequencyOfFile.get(currentChar)) * codeMap.get((char)currentChar).length();
        }
        // is in bits size / 8 will be in byte s
        return size / 8 ;
    }


    //TODO: make private
    public void createCompressedFile(@NotNull String inputFileName, String outputFileName) throws IOException {
        InputStream input = Helper.getInputStream(inputFileName);
        OutputStream outputStream = Helper.getOuputStream(outputFileName);
        int inputChar;
        int EOFFound = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputChar = input.read()) != -1) {
            stringBuilder.append(codeMap.get(inputChar));
            if (stringBuilder.length() > 8) {
                pushTheBitsToFile(stringBuilder.toString(), outputStream);
                stringBuilder = new StringBuilder(overflow);
                overflow = new String();
            }
        }

        stringBuilder.append(overflow);
        overflow = new String();
        // insert end of file
        stringBuilder.append("");
        if (stringBuilder.length() > 0){
            pushTheBitsToFile(stringBuilder.toString(),outputStream);
        }
        if(overflow.length() > 0){
            // do something
            byte buffer = (byte) Integer.parseInt(overflow,2);
            outputStream.write(buffer);
        }
        outputStream.flush();
    }

    //TODO: make private
    public void pushTheBitsToFile(String string, OutputStream outputStream) throws IOException {
        int x = string.length();
        String temp = "";
        int j = 0;
        int i =0;
        byte[] buffer = new byte[(string.length() / BYTE_SIZE)];
        for (; i + BYTE_SIZE < string.length(); i+=BYTE_SIZE) {
            temp = string.substring(i, i + BYTE_SIZE );
            buffer[j] = (byte) Integer.parseInt(temp, 2);
            j++;
        }
            // check if any bits are remaining
        if (i<string.length() ){
        // this means less than 8 bits are remaining
            overflow = string.substring(i);
        }
        else {
            overflow = new String();
        }
        // write only if there is somthing to be written
        outputStream.write(buffer);
    }

    //TODO: make private
    public void setCodeMap(HuffmanNode rootNode) {
        postOrderTraversal(rootNode, "");
    }
    //TODO: make public
    private void postOrderTraversal(@NotNull HuffmanNode node, String code) {
        if (node.isLeafNode) {
            codeMap.put((int)node.character, code);
            return;
        }
        postOrderTraversal(node.left, code + "0");
        postOrderTraversal(node.right, code + "1");
    }


    /**
     * @param inputFileName
     * @return
     * @throws IOException
     */
    //TODO: make private
    public Map<Integer, Integer> getCharacterFrequencyOfFile(@NotNull String inputFileName) throws IOException {
        InputStream input = Helper.getInputStream(inputFileName);
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

        input.close();
        frequencies.put(max, 1);
        return frequencies;
    }

}
