package part15;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class FileHelper {

    private static final String FOLDER_PATH = "/json/";

    public static <T> T generateObjectFromResource(String jsonFile, Class<T> resource) {
        try (InputStream inputStream = FileHelper.class.getResourceAsStream(FOLDER_PATH + jsonFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + FOLDER_PATH + jsonFile);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, resource);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load resource: " + jsonFile, e);
        }
    }

}
