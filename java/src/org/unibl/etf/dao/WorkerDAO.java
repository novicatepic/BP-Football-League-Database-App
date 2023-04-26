package org.unibl.etf.dao;

import java.util.List;
import org.unibl.etf.classes.Worker;

public interface WorkerDAO {
	public List<Worker> selectAll();
}
