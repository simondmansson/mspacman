package PacmanAI.Utility;

import PacmanAI.TreeNode;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.HierarchicalLayout;
import org.graphstream.ui.*;
import org.graphstream.ui.view.Viewer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TreeWalker {
    private Graph graph;
    private ArrayList<TreeNode> vertices;
    private HashMap<String, LinkedList<TreeNode>> edges;


    public TreeWalker() {
        graph = new SingleGraph("ID3Tree");
        vertices = new ArrayList<>();
        edges = new HashMap<>();
    }

    public void VisualizeTree(TreeNode root) {
        walkTree(root);
        fillGraph();
        Viewer viewer = graph.display();
        HierarchicalLayout hl = new HierarchicalLayout();
        viewer.enableAutoLayout(hl);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void fillGraph() {
        for(TreeNode n : vertices) {
          Node node = graph.addNode(n.toString()+vertices.indexOf(n));
           node.addAttribute("ui.label", n.toString()+vertices.indexOf(n));
        }
        for(TreeNode n : vertices) {
            for(TreeNode c : edges.get(n.toString())) {
                String from = n.toString()+vertices.indexOf(n);
                String to = c.toString()+vertices.indexOf(c);
                String key = from + to;
                Edge e = graph.addEdge(key, from, to);
                e.addAttribute("ui.label", key);
            }
        }
    }

    private void walkTree(TreeNode node) {
        vertices.add(node);
        LinkedList<TreeNode> children = new LinkedList(node.getEdges().values());
        edges.put(node.toString(), children);
        node.getEdges().values().forEach(child-> walkTree(child));
    }

}
