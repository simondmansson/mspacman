import PacmanAI.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

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
        root.addEdge("inkyDir", new TreeNode("UP"));
    }

    @Test
    public void PrintTree() {
        System.out.println(root);
        root.printTree(root ,1);
    }

    @Test
    public void TreePrint() {
        System.out.println(root.treePrint(root ,1, ""));

    }

    @Test
    public void printBFS() {
        for(int i = 0; i < root.getEdges().size(); i++) {
            System.out.print("\t");
        }
        System.out.println("\t"+ root);
        LinkedList<TreeNode> nodes = new LinkedList<>();
        root.getEdges().values().forEach(nodes::add);
        print(nodes, 1);

    }

    @Test
    public void printBFS2() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!node.isLeaf()) {
                System.out.print("Parent: "+ node +", Children: ");
            }
            node.getEdges().values().forEach(n -> {
                System.out.print(n+ " ");
                queue.add(n);
            });
            if(!node.isLeaf())
                System.out.println();
        }
    }

    private void print(LinkedList<TreeNode> nodes, int offset) {
        LinkedList<TreeNode> children = new LinkedList<>();
        for(int i = 0; i < offset; i++) {
            System.out.print("\t");
        }
        nodes.forEach(n->System.out.print(n + "\t"));
        System.out.println();
        nodes.forEach(n->n.getEdges().values().forEach(children::add));
        if(children.size()>0)
            print(children, offset+1);
    }
}
