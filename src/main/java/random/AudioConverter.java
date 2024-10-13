package random;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class AudioConverter {

    public static String convertToWav(File sourceFile, File destinationFile) throws IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sourceFile);
        AudioFormat baseFormat = audioInputStream.getFormat();
        AudioFormat targetFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false
        );
        AudioInputStream convertedInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        AudioSystem.write(convertedInputStream, AudioFileFormat.Type.WAVE, destinationFile);
        return  destinationFile.getAbsolutePath();
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        File sourceFile = new File("input.mp3"); // Source audio file
        File destinationFile = new File("output.wav"); // Output WAV file
        convertToWav(sourceFile, destinationFile);
        System.out.println("Conversion to WAV completed.");
    }
}