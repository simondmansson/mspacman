package PacmanAI;

import java.util.HashMap;

/**
 * @author Kalk88 & Kemaldev
 *
 * If the tree node is a leaf it represents a move
 * Else it will hold paths to child nodes.
 */
public class TreeNode {
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

    public boolean isLeaf() {
        return edges.isEmpty();
    }
}
