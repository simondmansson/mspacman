import PacmanAI.ID3Tree;
import PacmanAI.ID3TreeBuilder;
import PacmanAI.Partition;
import PacmanAI.TreeNode;
import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import PacmanAI.Utility.TreeWalker;
import PacmanAI.interfaces.DecisionTree;
import dataRecording.DataTuple;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.HierarchicalLayout;
import org.graphstream.ui.view.Viewer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.LinkedList;

public class TreePrinterTests {
    private TreeNode root;

    @Before
    public void setup() {
        root = new TreeNode("ROOT");
        TreeNode left = new TreeNode("blinkyIsEdible");
        TreeNode leftleft = new TreeNode("true");
        TreeNode leftright = new TreeNode("false");
        leftright.addEdge("RIGHT", new TreeNode("RIGHT"));
        leftleft.addEdge("LEFT", new TreeNode("LEFT"));
        left.addEdge("true", leftleft);
        left.addEdge("false", leftright);
        TreeNode right = new TreeNode("inkyIsEdible");
        right.addEdge("inkyDist", new TreeNode("DOWN"));
        root.addEdge(left.getLabel(), left);
        root.addEdge(right.getLabel(), right);
        root.addEdge("inkyDir", new TreeNode("UP"));
    }

    @Test
    public void TreeWalker() {
        TreeWalker texasWalkerRanger = new TreeWalker();
        texasWalkerRanger.VisualizeTree(root);
    }

    @Test
    public void printTreeMediumSurvivorMan3()
    {
        Parser parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
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
    public void printTreeMediumSurvivorMan()
    {
        Parser parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
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
    public void printTreeLargeTest()
    {
        Parser parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
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
    public void printTreeSMAN3()
    {
        Parser parser = new Parser();
        parser.setStrategy(new SurvivorManStrategy());
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
