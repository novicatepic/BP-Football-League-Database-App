package org.unibl.etf.dao;

import java.util.List;

import org.unibl.etf.classes.Fixture;

public interface FixtureDAO {

	public List<Fixture> selectAllFixtures();
	
}
