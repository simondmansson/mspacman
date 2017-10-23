
import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class ParserTests {
    private Parser parser;
    private String[] keys = {
            "isBlinkyEdible",
            "isInkyEdible",
            "isPinkyEdible",
            "isSueEdible",
            "blinkyDist",
            "inkyDist",
            "pinkyDist",
            "sueDist",
            "blinkyDir",
            "inkyDir",
            "pinkyDir",
            "sueDir",
            "numOfPowerPillsLeft"
    };


    @Before
    public void Setup() {
        parser = new Parser();
    }

    @Test
   public void ShouldCreateASurvivorManAttributesList() {
        parser.setStrategy(new SurvivorManStrategy());
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/main/resources/survivormanattributes.txt");
        for(int i = 0; i < keys.length; i++) {
            Assert.assertTrue(attributelist.containsKey(keys[i]));
        }
    }
}
