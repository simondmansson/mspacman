package PacmanAI.interfaces;

import PacmanAI.TreeNode;
import dataRecording.DataTuple;
import pacman.game.Constants.*;

/**
 * @author Kalk88 & Kemaldev
 *
 * Returns a direction based on the current game state.
 *
 */
public interface DecisionTree {
    MOVE makeDecision(String gamestate);
    MOVE makeDecision(DataTuple tuple);
    TreeNode getRoot();

}
