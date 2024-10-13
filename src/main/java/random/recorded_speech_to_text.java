package random;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;


import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class recorded_speech_to_text {
    private static final String speech_region = System.getenv("speech_Region");
    private static final String speech_key = System.getenv("speech_key");
    public static String filePath ="C:\\Users\\cabdi\\Downloads\\recording (online-audio-random.converter.com).m4a";



    public static void main(String[] args) throws ExecutionException, InterruptedException, UnsupportedAudioFileException, IOException {
        System.out.println(speech_region);
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speech_key, speech_region);
        //random.converter.setinputFile(filePath);
        recognizeAudio(speechConfig);

    }

    public static void recognizeAudio(SpeechConfig speechConfig) throws ExecutionException, InterruptedException, UnsupportedAudioFileException, IOException {
       // System.out.println(speech_key);

        speechConfig.setSpeechRecognitionLanguage("en-Us");
       // System.out.println(random.converter.getOutputFileFile());
        File outputFile =  new File("C:\\Users\\cabdi\\Downloads\\new_recorded.WAV");
        System.out.println(outputFile);
        AudioConfig audioInput = AudioConfig.fromWavFileInput(AudioConverter.convertToWav(new File(filePath),outputFile));
        SpeechRecognizer speechRecognizer= new SpeechRecognizer(speechConfig,audioInput);
        SpeechRecognitionResult result = speechRecognizer.recognizeOnceAsync().get();

        //Handle  the reconized speech by using an if statement and do something with it 
        
        if (result.getReason() == ResultReason.RecognizedSpeech){
            System.out.println("your text:"+result.getText());
            
        }else if (result.getReason() == ResultReason.NoMatch) {
            System.out.println("I am sorry No speech Was recognized");
        }


         else if (result.getReason() == ResultReason.Canceled) {
            System.out.println("text got cancelled");
         }

    }
}
