import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class converter {

    /**
     * Converts the provided byte array of audio data from its source format to the target format.
     *
     * @param sourceBytes  The byte array containing the source audio data.
     * @param audioFormat  The desired target AudioFormat.
     * @return A byte array containing the converted audio data in the target format.
     * @throws UnsupportedAudioFileException If the source byte array is not a supported audio format.
     * @throws IllegalArgumentException If the arguments are invalid (e.g., null sourceBytes or audioFormat).
     * @throws Exception If an unexpected error occurs during audio conversion.
     */
    public static byte[] getAudioDataBytes(byte[] sourceBytes, AudioFormat audioFormat)
            throws UnsupportedAudioFileException, IllegalArgumentException, Exception {

        // Validate input parameters
        if (sourceBytes == null || sourceBytes.length == 0 || audioFormat == null) {
            throw new IllegalArgumentException("Illegal Argument passed to this method: Null or empty data.");
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(sourceBytes);
             AudioInputStream sourceAIS = AudioSystem.getAudioInputStream(bais)) {

            AudioFormat sourceFormat = sourceAIS.getFormat();

            // Intermediate format: PCM signed, 16 bits, same sample rate and channels as source
            AudioFormat convertFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    sourceFormat.getSampleRate(),
                    16,
                    sourceFormat.getChannels(),
                    sourceFormat.getChannels() * 2,  // 16 bits per sample
                    sourceFormat.getSampleRate(),
                    false  // Little-endian
            );

            try (AudioInputStream convert1AIS = AudioSystem.getAudioInputStream(convertFormat, sourceAIS);
                 AudioInputStream convert2AIS = AudioSystem.getAudioInputStream(audioFormat, convert1AIS);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[8192];
                int readCount;

                // Read the audio data in chunks and write it to the ByteArrayOutputStream
                while ((readCount = convert2AIS.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, readCount);
                }

                // Return the converted audio data as a byte array
                return baos.toByteArray();
            }
        } catch (UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
            // Log or rethrow the exception to allow higher-level handling
            throw e;
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            throw new Exception("An unexpected error occurred during audio conversion", e);
        }
    }

    public static void main(String[] args) {
        try {
            // Example source bytes and target format
            byte[] sourceBytes = getDummyAudioBytes();  // Replace with actual audio byte data
            AudioFormat targetFormat = new AudioFormat(16000, 16, 1, true, false); // Mono, 16-bit, 16kHz

            // Perform the conversion
            byte[] convertedBytes = getAudioDataBytes(sourceBytes, targetFormat);

            System.out.println("Audio conversion successful! Converted data length: " + convertedBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Dummy method to simulate loading audio bytes (replace with actual logic)
    private static byte[] getDummyAudioBytes() {
        // Normally, you'd read this from a file, stream, or other data source.
        // Return a byte array representing the audio data for the demo.
        return new byte[8192];  // Just a placeholder
    }
}
