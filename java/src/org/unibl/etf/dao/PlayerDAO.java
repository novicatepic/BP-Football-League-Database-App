package org.unibl.etf.dao;

import java.util.List;
import org.unibl.etf.classes.Player;

public interface PlayerDAO {
	public List<Player> selectAll();
}
