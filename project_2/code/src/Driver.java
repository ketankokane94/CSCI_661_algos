import java.io.*;

public class Driver {

    public static void main(String[] args) {
        try {
            File inputFile = new File(new File("").getCanonicalPath() + "/analysis.txt");
            InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
            new Encode().encodeFile(inputStream, "");

        } catch (FileNotFoundException ex) {
            System.err.println("file not found");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
