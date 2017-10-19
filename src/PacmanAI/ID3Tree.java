package PacmanAI;

import dataRecording.DataTuple;
import pacman.game.Constants.*;

public class ID3Tree implements DecisionTree {
    private TreeNode root;

    public ID3Tree(TreeNode root) {
        this.root = root;
    }

    @Override
    public MOVE makeDecision(String state) {
        return makeDecision( new DataTuple(state));
    }

    private MOVE makeDecision(DataTuple tuple) {
        //TODO walk through tree KEMAL FIX PLZ
        /**
         * The node label is an attribute in the tree
         * use the label to get a value from the tuple
         * this is the next path to a node in the tree
         * when leaf return the MOVE
         * IF NOT EXIST RETURN NEUTRAL
         */
        return null;
    }
}