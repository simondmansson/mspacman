package PacmanAI;
import static pacman.game.Constants.*;

import PacmanAI.Utility.Parser;
import PacmanAI.Utility.SurvivorManStrategy;
import PacmanAI.interfaces.DecisionTree;
import dataRecording.DataTuple;
import pacman.controllers.Controller;
import pacman.game.*;

import java.util.LinkedList;

/**
 * @author Kalk88 & Kemaldev
 * The survivorMan lives for one purpose only... Never die.
 *
 */
public class SurvivorMan extends Controller<MOVE> {
    private DecisionTree tree;


    public SurvivorMan(DecisionTree tree) {
        this.tree = tree;
    }

    public SurvivorMan() {
        ID3TreeBuilder builder =  new ID3TreeBuilder();
        this.tree = builder.buildTree();
        System.out.println();
    }



    @Override
    public MOVE getMove(Game game, long timeDue) {
        return tree.makeDecision(game.getGameState());
    }
}
