package org.unibl.etf.dao;

import java.util.List;

import org.unibl.etf.classes.Stadium;

public interface StadiumDAO {

	public List<Stadium> selectAll();
	public int insert(Stadium s);
	public int update(Stadium s);
	public int delete(Stadium s);
	
}
