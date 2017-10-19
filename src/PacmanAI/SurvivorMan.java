package PacmanAI;
import static pacman.game.Constants.*;

import PacmanAI.interfaces.DecisionTree;
import pacman.controllers.Controller;
import pacman.game.*;

/**
 * @author Kalk88 & Kemaldev
 * The survivorMan lives for one purpose only... Never die.
 *
 */
public class SurvivorMan extends Controller<MOVE>{
    private DecisionTree tree;

    public SurvivorMan(DecisionTree tree) {
        this.tree = tree;
    }


    @Override
    public MOVE getMove(Game game, long timeDue) {
        return tree.makeDecision(game.getGameState());
    }
}
