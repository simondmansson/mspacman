package main.java.pacman.controllers.examples;

import java.util.EnumMap;
import java.util.Random;
import main.java.pacman.game.Constants.GHOST;
import main.java.pacman.game.Constants.MOVE;
import main.java.pacman.game.Game;
import main.java.pacman.controllers.Controller;

/*
 * The Class RandomGhosts.
 */
public final class RandomGhosts extends Controller<EnumMap<GHOST,MOVE>>
{	
	private EnumMap<GHOST,MOVE> moves=new EnumMap<GHOST,MOVE>(GHOST.class);
	private MOVE[] allMoves=MOVE.values();
	private Random rnd=new Random();
	
	/* (non-Javadoc)
	 * @see pacman.controllers.Controller#getMove(pacman.game.Game, long)
	 */
	public EnumMap<GHOST,MOVE> getMove(Game game,long timeDue)
	{	
		moves.clear();
		
		for(GHOST ghostType : GHOST.values())
			if(game.doesGhostRequireAction(ghostType))
				moves.put(ghostType,allMoves[rnd.nextInt(allMoves.length)]);
		
		return moves;
	}
}