package PacmanAI.interfaces;

import java.util.HashMap;
import java.util.LinkedList;

public interface ParsingStrategy {
    HashMap<String, LinkedList<String>> parse(String file);
}
