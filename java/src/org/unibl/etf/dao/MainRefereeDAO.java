package org.unibl.etf.dao;

import java.util.List;

import org.unibl.etf.classes.MainReferee;
import org.unibl.etf.classes.Referee;

public interface MainRefereeDAO {

	public List<MainReferee> selectAllMainReferees();
	
}
