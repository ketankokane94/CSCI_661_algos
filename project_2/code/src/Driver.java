import java.io.*;

public class Driver {

    public static void main(String[] args) {
        try {
            Encode encode = new Encode("test.txt");
            encode.encodeFile();
            Decode decode = new Decode("decoded_test.txt");
            decode.decodeFile();
            Helper.deleteFiles();
        } catch (FileNotFoundException ex) {
            System.err.println("file not found");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
