package Engine.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    private Utils(){

    }

    public static String readFile(String filePath){
        String fileData;
        try {
            fileData = new String(Files.readAllBytes(Paths.get(filePath)));
        }catch (IOException exception){
            throw new RuntimeException("Error reading file ["+filePath+"]", exception);
        }
        return fileData;
    }
}
