import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    private void RunExperimentForFile(String file) throws IOException {

        List<Long> initialFileSize = new ArrayList<>();
        List<Long> afterCompressionFileSize = new ArrayList<>();
        Encode encode = new Encode(file);
        Decode decode = new Decode("decoded_" + file);
        for (int runCount = 0; runCount < 19; runCount++) {
            initialFileSize.add(encode.getFileSize(file));
            encode.encodeFile();
            afterCompressionFileSize.add(encode.getFileSize(constants.ENCODED_FILE_NAME));
            decode.decodeFile();
            Helper.deleteFiles();
        }
        double temp = 0.0;
        for (int i = 0; i < initialFileSize.size(); i++) {

            temp +=(((double) initialFileSize.get(i) / afterCompressionFileSize.get(i)));
        }

        System.out.println(temp/19);
    }

    public static void main(String[] args) {
        try {

            Driver driver = new Driver();
            driver.RunExperimentForFile("test.txt");

        } catch (FileNotFoundException ex) {
            System.err.println("file not found");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
