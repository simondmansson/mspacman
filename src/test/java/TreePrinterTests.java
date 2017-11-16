import PacmanAI.*;
import PacmanAI.Utility.*;
import PacmanAI.interfaces.DecisionTree;
import dataRecording.DataTuple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.LinkedList;

public class TreePrinterTests {
    private Parser parser;

    @Before
    public void setup() {
        parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
    }

    @Test
    public void printMediumSurvivorMan3() {
        LinkedList<DataTuple> tuples = parser.parseTrainingData("src/test/resources/mediumTrainingData.txt");
        Assert.assertTrue(tuples.size() > 0);
        Partition partition = new Partition(tuples);
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/test/resources/survivormanattributes3.txt");
        ID3TreeBuilder id3Tree = new ID3TreeBuilder(partition, attributelist);
        DecisionTree tree = id3Tree.buildTree();
        TreeWalker texasWalkerRanger = new TreeWalker();
        texasWalkerRanger.VisualizeTree(tree.getRoot());
        Assert.assertTrue(partition.getTuples() != null);
    }

    @Test
    public void printMediumSurvivorMan() {
        LinkedList<DataTuple> tuples = parser.parseTrainingData("src/test/resources/mediumTrainingData.txt");
        Assert.assertTrue(tuples.size() > 0);
        Partition partition = new Partition(tuples);
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/test/resources/survivormanattributes.txt");
        ID3TreeBuilder id3Tree = new ID3TreeBuilder(partition, attributelist);
        DecisionTree tree = id3Tree.buildTree();
        TreeWalker texasWalkerRanger = new TreeWalker();
        texasWalkerRanger.VisualizeTree(tree.getRoot());
        Assert.assertTrue(partition.getTuples() != null);
    }

    @Test
    public void printLargeTree() {
        LinkedList<DataTuple> tuples = parser.parseTrainingData("src/test/resources/trainingData.txt");
        Assert.assertTrue(tuples.size() > 0);
        Partition partition = new Partition(tuples);
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/main/resources/survivormanattributes.txt");
        ID3TreeBuilder id3Tree = new ID3TreeBuilder(partition, attributelist);
        DecisionTree tree = id3Tree.buildTree();
        TreeWalker texasWalkerRanger = new TreeWalker();
        texasWalkerRanger.VisualizeTree(tree.getRoot());
        Assert.assertTrue(partition.getTuples() != null);
    }

    @Test
    public void printTreeSMAN3() {
        LinkedList<DataTuple> tuples = parser.parseTrainingData("src/test/resources/KemalStarPlayer.txt");
        Assert.assertTrue(tuples.size() > 0);
        Partition partition = new Partition(tuples);
        HashMap<String, LinkedList<String>> attributelist = parser.parse("src/main/resources/survivormanattributes3.txt");
        ID3TreeBuilder id3Tree = new ID3TreeBuilder(partition, attributelist);
        DecisionTree tree = id3Tree.buildTree();
        TreeWalker texasWalkerRanger = new TreeWalker();
        texasWalkerRanger.VisualizeTree(tree.getRoot());
        Assert.assertTrue(partition.getTuples() != null);
    }
}
