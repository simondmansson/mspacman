import PacmanAI.ID3TreeBuilder;
import PacmanAI.Partition;
import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import PacmanAI.Utility.TreeWalker;
import PacmanAI.interfaces.DecisionTree;
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
public class BuildTest {

    private static LinkedList<DataTuple> tuples;
    private static Parser parser;
    private ID3TreeBuilder id3Tree;
    private Partition partition;
    @BeforeClass
    public static void preSetup()
    {
        parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
        tuples = parser.parseTrainingData("src/test/resources/trainingData.txt");
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
    public void buildTest() {
        DecisionTree tree = id3Tree.buildTree();
        System.out.println();
        //double result = id3Tree.getInformationNeeded();
        //Assert.assertEquals(2.32, result, 1e-2);
    }

    @Test
    public void surviorMan3Tree(){
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/test/resources/survivormanattributes3.txt");
        tuples = parser.parseTrainingData("src/test/resources/trainingData.txt");
        partition = new Partition(tuples);
        id3Tree = new ID3TreeBuilder(partition, attributelist);
        Assert.assertTrue(partition.getTuples() != null);
        DecisionTree tree = id3Tree.buildTree();
        System.out.println();
        TreeWalker texasWalkerRanger = new TreeWalker();
        texasWalkerRanger.VisualizeTree(tree.getRoot());
    }

    @Test
    public void pureDataTest() {
        tuples = parser.parseTrainingData("src/test/resources/pureTrainingData.txt");
        partition = new Partition(tuples);
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/main/resources/survivormanattributes.txt");
        id3Tree = new ID3TreeBuilder(partition, attributelist);
        DecisionTree tree = id3Tree.buildTree();
        Assert.assertTrue(tree.makeDecision(partition.getTuples().get(0).getSaveString()).equals("LEFT"));
    }

}
