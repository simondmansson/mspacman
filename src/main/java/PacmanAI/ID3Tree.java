package PacmanAI;

import PacmanAI.interfaces.DecisionTree;
import dataRecording.DataTuple;
import pacman.game.Constants.*;

public class ID3Tree implements DecisionTree {
    private TreeNode root;

    public ID3Tree(TreeNode root) {
        this.root = root;
    }

    @Override
    public MOVE makeDecision(String state) {
        return think(new DataTuple(state));
    }

    @Override
    public MOVE makeDecision(DataTuple tuple) {
        return think(tuple);
    }

    @Override
    public TreeNode getRoot() {
        return root;
    }

    private MOVE think(DataTuple tuple) {
        /**
         * The node label is an attribute in the tree
         * use the label to get a value from the tuple
         * this is the next path to a node in the tree
         * when leaf return the MOVE
         * IF NOT EXIST RETURN NEUTRAL
         */
        TreeNode node = root;
        String next;
        while(!node.isLeaf()) {
            next = tuple.getAttribute(node.getLabel());
            node = node.getChild(next);
        }
        next = node.getLabel();
        return MOVE.valueOf(next);
    }
}
