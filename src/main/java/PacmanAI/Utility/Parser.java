package PacmanAI.Utility;

import PacmanAI.interfaces.ParsingStrategy;
import dataRecording.DataTuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Parser {
    private ParsingStrategy ps;

    public Parser(){};

    public void setStrategy(ParsingStrategy ps) {
        this.ps = ps;
    }

   public HashMap<String, LinkedList<String>> parse(String file) {
       return ps.parse(file);
   }

   public LinkedList<DataTuple> parseTrainingData(String filepath) {
       LinkedList<DataTuple> tuples = new LinkedList<>();

       try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
           stream.forEach( line -> {
               tuples.add(new DataTuple(line));
           });

       } catch (IOException e) {
           e.printStackTrace();
       }
       return tuples;

   }



}
