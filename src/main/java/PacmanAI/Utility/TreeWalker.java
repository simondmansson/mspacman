package PacmanAI.Utility;

import PacmanAI.TreeNode;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeWalker {
    private Graph graph;
    ArrayList<TreeNode> vertices;
    HashMap<String, Integer> edges;

    public TreeWalker() {
        graph = new SingleGraph("ID3Tree");
        vertices = new ArrayList<>();
        edges = new HashMap<>();

    }

    public void VisualizeTree(TreeNode root) {
        walkTree(root, 0);
        int i = 0;
        for(TreeNode v :vertices)
        //vertices.forEach( v->
        {
            System.out.println(v);
            Node node = graph.addNode(v.toString()+ i++);

            node.addAttribute("ui.label", v.toString());
        //});
        }
        /*
        vertices.forEach( v-> {
            v.getEdges().forEach((key, val)-> {
                Edge e  = graph.addEdge(key, v.toString(), val.toString());
                e.addAttribute("ui.label", key);
            });
        });
        */
        graph.display();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void walkTree(TreeNode node, int index) {
        vertices.add(index, node);
        node.getEdges().values().forEach(child-> {
            edges.put(node.toString()+child.toString(), index+1);
            walkTree(child, index+1);
        });

    }

}
