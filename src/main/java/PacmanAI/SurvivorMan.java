package main.java.PacmanAI;
import static main.java.pacman.game.Constants.*;

import main.java.PacmanAI.interfaces.DecisionTree;
import main.java.pacman.controllers.Controller;
import main.java.pacman.game.*;

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
