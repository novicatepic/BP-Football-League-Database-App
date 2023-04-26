package org.unibl.etf.dao;

import java.util.List;
import org.unibl.etf.classes.PlayerInGame;

public interface PlayerInGameDAO {

	public List<PlayerInGame> selectAll();
	public int insert(PlayerInGame s);
	
}
