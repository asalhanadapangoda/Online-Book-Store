package com.bookstore.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return lines;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeFile(String filePath, List<String> lines) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(String filePath, String line) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, true));
            writer.println(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
