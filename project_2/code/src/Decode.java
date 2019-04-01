import java.io.*;

public class Decode {
    String encodedFileName;
    String decodedFileName;
    HuffmanNode rootNode;
    HuffmanNode overFlowNode;

    public Decode(String decodedFileName) {
        this.encodedFileName = constants.ENCODED_FILE_NAME;
        this.decodedFileName = decodedFileName;
        overFlowNode = null;
    }

    public void decodeFile() throws IOException {
        rootNode = Helper.getSavedTree(constants.SERIALIZED_FILE_NAME);
        // TODO: add charset of UTF 8
        InputStream inputStream = Helper.getInputStream(encodedFileName);
        OutputStream outputStream = Helper.getOuputStream(decodedFileName);


        byte inputByte;
        StringBuilder stringBuilder = new StringBuilder();
        int temp ;
        //while ((inputByte = (byte) inputStream.read()) != -1) {
        while ((temp = inputStream.read()) != -1) {
            inputByte = (byte) temp;
            stringBuilder.append(getStringRepresentationOfByte(inputByte));
            writeCharacterToFile(stringBuilder.toString(), outputStream);
            stringBuilder = new StringBuilder();
        }
        outputStream.close();
/*
        while ((inputChar = input.read()) != -1) {
            stringBuilder.append(codeMap.get(inputChar));
            if (stringBuilder.length() > 1000) {
                pushTheBitsToFile(stringBuilder.toString(), outputStream);
                stringBuilder = new StringBuilder(overflow);
                overflow = new String();
            }
        }
 */
    }

    private void writeCharacterToFile(String string, OutputStream outputStream) throws IOException {

        HuffmanNode node = rootNode;

        if (overFlowNode != null) {
            node = overFlowNode;
        }

        final char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; ) {
            char c = chars[i];
            if (node.isLeafNode) {
                outputStream.write(node.character);
                node = rootNode;
                overFlowNode = null;
            } else {
                if (c == '0')
                    node = node.left;
                else
                    node = node.right;
                i++;
            }
        }

        if (node.isLeafNode) {
            outputStream.write(node.character);
            node = rootNode;
            overFlowNode = null;
        }

        if (node != rootNode) {
            overFlowNode = node;
        }
        outputStream.flush();
    }

    private String getStringRepresentationOfByte(byte inputByte) {
        return String.format("%8s", Integer.toBinaryString(inputByte & 0xFF)).replace(' ', '0');
        //return (Integer.toBinaryString(inputByte & 0xFF)).replace(' ', '0');
    }


}
