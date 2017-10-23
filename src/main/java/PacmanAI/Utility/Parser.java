package PacmanAI.Utility;

import PacmanAI.interfaces.ParsingStrategy;

import java.util.HashMap;
import java.util.LinkedList;

public class Parser {
    private ParsingStrategy ps;

    public Parser(){};

    public void setStrategy(ParsingStrategy ps) {
        this.ps = ps;
    }

   public HashMap<String, LinkedList<String>> parse(String file) {
       return ps.parse(file);
   }



}
