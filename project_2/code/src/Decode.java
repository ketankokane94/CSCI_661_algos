import java.io.*;

public class Decode {
    HuffmanNode rootNode;

    public void decodeFile() throws IOException {
        rootNode = Helper.getSavedTree(constants.SERIALIZED_FILE_NAME);
        // TODO: add charset of UTF 8
        InputStream inputStream = Helper.getInputStream("encoded.txt");
        OutputStream outputStream = Helper.getOuputStream("dasd.txt");


        byte inputByte ;
        StringBuilder stringBuilder  = new StringBuilder();
        while ((inputByte = (byte)inputStream.read()) != -1){
        stringBuilder.append(getStringRepresentationOfByte(inputByte));
        if (stringBuilder.length() > 1000){
            getCharacters(stringBuilder.toString(),outputStream);
        }
        }

    }

    private void getCharacters(String string,OutputStream outputStream) throws IOException {

        HuffmanNode node = rootNode;
        final char[] chars = string.toCharArray();
        for (char c: chars){
                if (node.isLeafNode){
                    // push the character to the file
                    outputStream.write(node.character);
                }
                else {
                    if (c =='0')
                        node = node.left;
                    else
                        node = node.right;
                }
        }
            outputStream.flush();
    }

    private String getStringRepresentationOfByte(byte inputByte) {
        String s1 = String.format("%8s", Integer.toBinaryString(inputByte & 0xFF)).replace(' ', '0');
        return s1;
    }


}
