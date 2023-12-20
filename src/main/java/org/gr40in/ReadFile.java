package org.gr40in;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {
    public static List<String> getStringList(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

}
