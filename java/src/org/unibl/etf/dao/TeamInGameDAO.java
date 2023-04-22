package org.unibl.etf.dao;

import java.util.List;
import org.unibl.etf.classes.TeamInGame;

public interface TeamInGameDAO {
	public List<TeamInGame> selectAllTeamsInGame();
	public int insertTeamInGame(TeamInGame s);
	public int updateTeamInGame(int p1, int p2, int p3, int p4);
	public int deleteTeamInGame(int s);
}
