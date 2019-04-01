import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    private void RunExperimentForFile(String file) throws IOException {

        List<Long> initialFileSize = new ArrayList<>();
        List<Long> encodeTime = new ArrayList<>();
        List<Long> decodeTime = new ArrayList<>();
        List<Long> afterCompressionFileSize = new ArrayList<>();
        Encode encode = new Encode(file);
        Decode decode = new Decode("decoded_i.txt");
        for (int runCount = 0; runCount < 1; runCount++) {
            initialFileSize.add(encode.getFileSize(file));
            long startTime = System.nanoTime();
            encode.encodeFile();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            encodeTime.add(duration);
            afterCompressionFileSize.add(encode.getFileSize(constants.ENCODED_FILE_NAME));
            startTime = System.nanoTime();
            decode.decodeFile();
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            decodeTime.add(duration);
            Helper.deleteFiles();
        }
          //divide by 1000000 to get milliseconds.
        double temp = 0.0;
        for (int i = 0; i < initialFileSize.size(); i++) {
            temp += (((double) initialFileSize.get(i) / afterCompressionFileSize.get(i)));
        }
        System.out.println(temp / 1);
        temp = 0.0;
        for (int i = 0; i < encodeTime.size(); i++) {
            temp += (((double) encodeTime.get(i)));
        }

        System.out.println("Encoding time " + (temp/1000000));

        temp = 0.0;
        for (int i = 0; i < decodeTime.size(); i++) {
            temp += (((double) decodeTime.get(i)));
        }

        System.out.println("decodeTime time " + (temp/1000000));
    }


    public static void main(String[] args) {
        try {
            Driver driver = new Driver();
            driver.RunExperimentForFile("data/hundredMb.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("file not found");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
