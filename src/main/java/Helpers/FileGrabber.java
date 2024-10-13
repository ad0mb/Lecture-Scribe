package Helpers;

import java.util.Map;

public class FileGrabber {

    public static String getFilePath(String input) {
        Map<String, String> filePaths = Map.of(
                "LectureScribeMain", "C:\\Users\\aboul\\LectureScribe",
                "LectureScribeConfigs", "C:\\Users\\aboul\\LectureScribe\\Configs"
        );

        return filePaths.get(input);
    }
}
