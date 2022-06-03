package sample.webhook.enums;

import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public enum PropertiesEnum {
    URL("webhook.url");
    
    private static Properties properties;

    private final String key;

    private PropertiesEnum(String key) {
        this.key = key;
    }

    public static void load(String resourcePath) throws IOException {
        properties = new Properties();
        properties.load(
            Files.newBufferedReader(
                Paths.get(
                    Thread.currentThread()
                          .getContextClassLoader()
                          .getResource(resourcePath)
                          .getPath()
                ),
                StandardCharsets.UTF_8
            )
        );
    }

    public String getPropertiesValue() {
        return properties.getProperty(this.key);
    }
    
}