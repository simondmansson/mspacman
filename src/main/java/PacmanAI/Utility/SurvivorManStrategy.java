package PacmanAI.Utility;


import PacmanAI.interfaces.ParsingStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

public class SurvivorManStrategy implements ParsingStrategy {
    @Override
    public HashMap<String, LinkedList<String>> parse(String filepath) {
        HashMap<String, LinkedList<String>> attributelist = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
            stream.forEach( line -> {
                String[] parts = line.trim().split(":");
                String key = parts[0].trim();
                String[] values = parts[1].trim().split(",");
                LinkedList<String> vals = new LinkedList<>();
                for(int i = 0; i < values.length; i++)
                {
                    vals.add(values[i].trim());
                }
                attributelist.put(key, vals);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attributelist;
    }
}
