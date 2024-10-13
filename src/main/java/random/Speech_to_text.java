package random;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Speech_to_text {

    private static String speech_region = System.getenv("speech_Region");
    private static String speech_key = System.getenv("speech_key");

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        System.out.println(speech_region);
        System.out.println(speech_key);
        String filePath = "bool.txt";
        String content = Files.readString(Paths.get("C:\\Users\\cabdi\\OneDrive\\Documents\\bool.txt")).trim();
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speech_key, speech_region);
        speechConfig.setSpeechRecognitionLanguage("en-Us");
        recognizeOnceFromMicrophone(speechConfig);

    }



    public static void recognizeFromMicrophone(SpeechConfig speechConfig) throws InterruptedException, ExecutionException {
        AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig);


//        System.out.println("speak lil bro");
        Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
        SpeechRecognitionResult speechRecognitionResult = task.get();

        // Start continuous recognition
        //SpeechRecognizer recognizer = ;
        speechRecognizer.recognizing.addEventListener((s, e) -> {
            System.out.println("RECOGNIZING: " + e.getResult().getText());
        });

        speechRecognizer.recognized.addEventListener((s, e) -> {
            System.out.println("RECOGNIZED: " + e.getResult().getText());
        });

        speechRecognizer.startContinuousRecognitionAsync();


        if (speechRecognitionResult.getReason() == ResultReason.RecognizedSpeech) {
            System.out.println("RECOGNIZED: Text=" + speechRecognitionResult.getText());
        } else if (speechRecognitionResult.getReason() == ResultReason.NoMatch) {
            System.out.println("NOMATCH: Speech could not be recognized.");
        } else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
            CancellationDetails cancellation = CancellationDetails.fromResult(speechRecognitionResult);
            System.out.println("CANCELED: Reason=" + cancellation.getReason());

            if (cancellation.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                System.out.println("CANCELED: Did you set the speech resource key and region values?");
            }
        }
    }

    public static void recognizeOnceFromMicrophone(SpeechConfig speechConfig) throws IOException, ExecutionException, InterruptedException {
        AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig);


        System.out.println("Speak into your microphone.");


        speechRecognizer.recognizing.addEventListener((s, e) -> {
           // System.out.println("RECOGNIZING: " + e.getResult().getText());
        });


        speechRecognizer.recognized.addEventListener((s, e) -> {
            System.out.println("recognized: " + e.getResult().getText());
        });


        speechRecognizer.startContinuousRecognitionAsync().get();
        while(FileWatcher.getBool()){}
        speechRecognizer.stopContinuousRecognitionAsync().get();

        try {
            speechRecognizer.stopContinuousRecognitionAsync().get();
            System.out.println("Recording stopped.");
        } catch (InterruptedException | ExecutionException e) {
            // If any exception occurs during the stop process, print the error stack trace for debugging.
            e.printStackTrace();
        }


        System.exit(0);


    }
}









