package app.netlify.codelittlestation.codelittlestation.classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class fileIO {
    private fileIO(){}
    public static String read(String filepath) throws IOException {
        return Files.readString(Path.of(filepath), StandardCharsets.UTF_8);
    }
    public static void write(String filepath, String data,boolean ap) throws IOException {
        File f = new File(filepath);
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(filepath,ap);
        byte[] b = data.getBytes(StandardCharsets.UTF_8);
        fos.write(b);
        fos.close();
    }
    public static void write(String filepath, String data) throws IOException {
        write(filepath,data,false);
    }
}
