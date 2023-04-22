package org.unibl.etf.classes;

import java.io.Serializable;

public class Fixture implements Serializable {

	private int brojKola;

	public Fixture() {
		super();
	}

	public Fixture(int brojKola) {
		super();
		this.brojKola = brojKola;
	}

	public int getBrojKola() {
		return brojKola;
	}

	public void setBrojKola(int brojKola) {
		this.brojKola = brojKola;
	}

	@Override
	public String toString() {
		return String.valueOf(brojKola);
	}
	
	
	
}
