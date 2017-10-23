package PacmanAI;

import PacmanAI.interfaces.TreePrint;
import java.util.HashMap;

/**
 * @author Kalk88 & Kemaldev
 *
 * If the tree node is a leaf it represents a move
 * Else it will hold paths to child nodes.
 */
public class TreeNode implements TreePrint {
    private String label;
    private HashMap<String, TreeNode> edges;

    public TreeNode(String label) {
        this.label = label;
        edges = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    public void addEdge(String path, TreeNode child) {
        edges.put(path, child);
    }

    public TreeNode getChild(String path) {
        return edges.get(path);
    }

    public HashMap<String, TreeNode> getEdges() {
        return edges;
    }

    public boolean isLeaf() {
        return edges.isEmpty();
    }

    public String toString() {
        return label;
    }

    @Override
    public void printTree(TreeNode node, int height) {

        node.edges.values().forEach(n-> {
            for (int i = 1; i < height; i++) {
                System.out.print("\t");
            }
            System.out.print(n+"\t");
        });
        node.edges.values().forEach(n->printTree(n, height+1));
        if(!node.isLeaf())
            System.out.println();

    }

    @Override
    public String treePrint(TreeNode node, int height, String res) {
        if(!node.isLeaf())
        {
            node.edges.values().forEach(n->printTree(n, height+1));
        }

        for (int i = 1; i < height; i++) {
            res+="\t";
        }

        for(TreeNode n : node.edges.values())
            res += n +"\t";
        res += "\n";
        return res;
    }
}
