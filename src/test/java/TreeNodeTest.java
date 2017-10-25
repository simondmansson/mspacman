import PacmanAI.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeNodeTest {
    TreeNode node;
    @Before
    public void setup() {
        node = new TreeNode("Test");
    }

    @Test
    public void shouldCreateANewTreeNode() {
        node = new TreeNode("Test");
        assertTrue("Test".equals(node.getLabel()));
    }

    @Test
    public void ShouldAddAnEdge() {
        node.addEdge("true", new TreeNode("LEFT"));
        TreeNode child = node.getChild("true");
        assertEquals(child.getLabel(),"LEFT");
    }

    @Test
    public void ShouldBeALeaf() {
        assertTrue(node.isLeaf());
    }
}
