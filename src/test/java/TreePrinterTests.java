import PacmanAI.TreeNode;
import PacmanAI.Utility.TreePrinter;
import org.junit.Before;
import org.junit.Test;

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
        right.addEdge("inkyDist", new TreeNode("RIGHT"));
        root.addEdge(left.getLabel(), left);
        root.addEdge(right.getLabel(), right);
    }

    @Test
    public void PrintTree() {
        root.printTree(root ,1);
    }
}
