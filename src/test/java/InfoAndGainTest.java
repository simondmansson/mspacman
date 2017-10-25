import PacmanAI.ID3TreeBuilder;
import PacmanAI.Partition;
import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import dataRecording.DataTuple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by kemkoi on 10/23/17.
 */
public class InfoAndGainTest {

    private static LinkedList<DataTuple> tuples;
    private static Parser parser;
    private ID3TreeBuilder id3Tree;
    private Partition partition;
    @BeforeClass
    public static void preSetup()
    {
        parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
        tuples = parser.parseTrainingData("src/test/resources/smallTrainingData.txt");
        Assert.assertTrue(tuples.size() > 0);
    }
    @Before
    public void setup() {
        partition = new Partition(tuples);
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/main/resources/survivormanattributes.txt");
        id3Tree = new ID3TreeBuilder(partition, attributelist);
        Assert.assertTrue(partition.getTuples() != null);
    }

    @Test
    public void infoTest() {
        double result = id3Tree.getInformationNeeded();
        Assert.assertEquals(2.32, result, 1e-2);
    }


    @Test
    public void infoAttributeTest() {
        double result = id3Tree.infoAttribute(partition, "isBlinkyEdible");
        Assert.assertEquals(1.6, result, 1e-2);
    }

    @Test
    public void gainTest() {
        double result = id3Tree.gain(partition, "isBlinkyEdible");
        Assert.assertEquals(0.72, result, 1e-2);
    }


}
