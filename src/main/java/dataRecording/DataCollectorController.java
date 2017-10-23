package main.java.dataRecording;


import main.java.pacman.controllers.HumanController;
import main.java.pacman.controllers.KeyBoardInput;
import main.java.pacman.game.Constants.*;
import main.java.pacman.game.Game;

/**
 * The DataCollectorHumanController class is used to collect training data from playing PacMan.
 * Data about game state and what MOVE chosen is saved every time getMove is called.
 * @author andershh
 *
 */
public class DataCollectorController extends HumanController {
	
	public DataCollectorController(KeyBoardInput input){
		super(input);
	}
	
	@Override
	public MOVE getMove(Game game, long dueTime) {
		MOVE move = super.getMove(game, dueTime);
		
		DataTuple data = new DataTuple(game, move);
				
		DataSaverLoader.SavePacManData(data);		
		return move;
	}

}
