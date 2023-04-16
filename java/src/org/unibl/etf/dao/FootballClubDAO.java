package org.unibl.etf.dao;

import java.util.List;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;

public interface FootballClubDAO {

	public List<FootballClub> selectAll();
	public int insert(FootballClub f);
	public int update(FootballClub f);
	public int delete(FootballClub f);
	
}
