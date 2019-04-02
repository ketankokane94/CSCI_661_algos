import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    private static final int RUNS = 11;
    private List<Long> initialFileSize;
    private List<Long> encodeTime;
    private List<Long> decodeTime;
    private List<Long> afterCompressionFileSize;

    public Driver() {
        initialFileSize = new ArrayList<>();
        encodeTime = new ArrayList<>();
        decodeTime = new ArrayList<>();
        afterCompressionFileSize = new ArrayList<>();
    }

    private void RunExperimentForFile(String file, boolean useEstimatedFrequency) throws IOException {

        Encode encode = new Encode(file);
        Decode decode = new Decode("decoded_.txt");
        for (int runCount = 0; runCount < Driver.RUNS; runCount++) {
            initialFileSize.add(encode.getFileSize(file));
            long startTime = System.nanoTime();
            encode.encodeFile(useEstimatedFrequency);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            encodeTime.add(duration);
            afterCompressionFileSize.add(encode.getFileSize(constants.ENCODED_FILE_NAME));
            startTime = System.nanoTime();
            decode.decodeFile();
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            decodeTime.add(duration);
            //Helper.deleteFiles();
        }
        //divide by 1000000 to get milliseconds.
        double temp = getAverage(initialFileSize);
        double compressionRatio = 0.0;
        for (int i = 0; i < initialFileSize.size(); i++) {
            compressionRatio += (((double) initialFileSize.get(i) / afterCompressionFileSize.get(i)));
        }
        compressionRatio = compressionRatio / Driver.RUNS;

        double ecodingTime = getAverage(encodeTime) / 1000000;
        double decodingTime = getAverage(decodeTime) / 1000000;
        System.out.format("%20s %20f %20s %20s", file, compressionRatio, ecodingTime, decodingTime);
        System.out.println();
    }

    private double getAverage(List<Long> arrayList) {

        double sum = 0.0;
        for (int i = 0; i < arrayList.size(); i++) {
            sum += (((double) arrayList.get(i)));
        }
        sum = sum / Driver.RUNS;
        return sum;
    }


    public static void main(String[] args) {
        System.out.format("%20s %20s%20s%20s", "fileName", "compressionRatio", "encodingTime", "Decoding Time");
        System.out.println();
        String[] files = getFilesInsideDataDirectory();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                try {
                    Driver driver = new Driver();
                    driver.RunExperimentForFile("data/"+ files[i], false);
                } catch (FileNotFoundException ex) {
                    System.err.println("file not found");
                    ex.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String[] getFilesInsideDataDirectory() {
        File file = new File("./data");
        if (file.isDirectory()) {
            final String[] list = file.list();
            return list;
        }
        return null;
    }
}
