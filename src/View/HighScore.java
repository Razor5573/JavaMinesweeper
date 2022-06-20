package View;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class HighScore {
    private static Path tablePath;

    static {
        try {
            URL fileURL = HighScore.class.getResource("scoreTable.csv");
            tablePath = Path.of(fileURL.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void saveScore(String name, long time, int score) {
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(tablePath, StandardCharsets.UTF_8);
            int position = 0;
            for (String data : lines) {
                if (Integer.parseInt(data.split(",")[2]) < score) {
                    break;
                }
                position++;
            }
            lines.add(position, name + "," + time + "," + score);
            Files.write(tablePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Can't save score");
        }
    }

    public static ArrayList<String> getScore() {
        ArrayList<String> outArray = null;
        try {
            outArray = (ArrayList<String>) Files.readAllLines(tablePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outArray;
    }

    public static void dropTable() {
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(tablePath, StandardCharsets.UTF_8);
            lines.clear();
            Files.write(tablePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Can't save score");
        }
    }
}
