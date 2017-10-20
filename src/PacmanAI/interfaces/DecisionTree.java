package PacmanAI.interfaces;

import pacman.game.Constants.*;

/**
 * @author Kalk88 & Kemaldev
 *
 * Returns a direction based on the current game state.
 *
 */
public interface DecisionTree {
    MOVE makeDecision(String gamestate);

}
