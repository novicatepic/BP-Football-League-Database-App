package org.unibl.etf.dao;

import java.util.List;
import org.unibl.etf.classes.Game;

public interface GameDAO {

	public List<Game> selectAllGames();
	public int insertGame(Game s);
	//public int update(Game s);
	public int deleteGame(int s);
	
}
