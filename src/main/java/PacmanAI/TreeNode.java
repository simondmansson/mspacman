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
        for (int i = 1; i < height; i++) {
            System.out.print("\t");
        }
        System.out.println(node);
        node.edges.values().forEach(n->printTree(n, height+1));
    }
}
