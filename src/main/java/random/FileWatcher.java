package random;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileWatcher {

    private static boolean isRecording = false;// Variable to store the recording status

    public static String filePath = new String("C:\\Users\\aboul\\IdeaProjects\\Lecture-Scribe\\src\\main\\java\\bool.txt");

    public static boolean getBool() throws IOException{
        List<String> lines = Files.readAllLines(Path.of(filePath));

        return Boolean.parseBoolean(lines.get(0));
    }
}



