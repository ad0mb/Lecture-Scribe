package speechtotext;

import com.microsoft.cognitiveservices.speech.SpeechRecognizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class InternalListener {

    public static String filePath = new String("C:\\Users\\aboul\\IdeaProjects\\Lecture-Scribe\\src\\main\\java\\bool.txt");

    public void ListeningProcess(SpeechRecognizer speechRecognizer) throws IOException, ExecutionException, InterruptedException {

        //this is for testing
        speechRecognizer.recognizing.addEventListener((s, e) -> {
            System.out.println("RECOGNIZING: " + e.getResult().getText());
        });
        //this is going to append to text
        speechRecognizer.recognized.addEventListener((s, e) -> {
            System.out.println("RECOGNIZED: " + e.getResult().getText());
        });

        speechRecognizer.startContinuousRecognitionAsync().get();
        while(getBool()){}
        speechRecognizer.stopContinuousRecognitionAsync().get();

        System.out.println("ended");
    }

    public static boolean getBool() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));

        return Boolean.parseBoolean(lines.get(0));
    }
}