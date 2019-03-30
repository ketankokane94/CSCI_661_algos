import java.io.*;

public class Driver {

    public static void main(String[] args) {
        try {
            new Encode().encodeFile("analysis.txt", "");
        } catch (FileNotFoundException ex) {
            System.err.println("file not found");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
