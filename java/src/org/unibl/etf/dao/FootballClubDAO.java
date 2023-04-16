package org.unibl.etf.dao;

import java.util.List;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;

public interface FootballClubDAO {

	public List<FootballClub> selectAll();
	public int insert(FootballClub s);
	public int update(FootballClub s);
	public int delete(FootballClub s);
	
}
