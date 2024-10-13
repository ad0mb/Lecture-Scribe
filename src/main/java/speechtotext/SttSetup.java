package speechtotext;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

public class SttSetup {

    private final String API_KEY = "--";
    private final String REGION = "eastus";

    public SttSetup() {

    }

    public SpeechRecognizer setup() {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(API_KEY, REGION);
        speechConfig.setSpeechRecognitionLanguage("en-Us");

        AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);

        return speechRecognizer;
    }


}
