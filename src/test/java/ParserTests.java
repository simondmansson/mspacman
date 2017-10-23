
import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class ParserTests {
    private Parser parser;

    @Before
    public void Setup() {
        parser = new Parser();
    }

    @Test
   public void ShouldCreateASurvivorManAttributesList() {
        parser.setStrategy(new SurvivorManStrategy());
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/main/resources/survivormanattributes.txt");
        System.out.println(attributelist);
    }
}
