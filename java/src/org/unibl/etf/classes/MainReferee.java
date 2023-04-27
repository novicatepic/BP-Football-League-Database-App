package org.unibl.etf.classes;

import java.io.Serializable;

public class MainReferee implements Serializable {

	private int sudija_sudijaId;

	public MainReferee() {
		super();
	}

	public MainReferee(int sudija_sudijaId) {
		super();
		this.sudija_sudijaId = sudija_sudijaId;
	}

	public int getSudija_sudijaId() {
		return sudija_sudijaId;
	}

	public void setSudija_sudijaId(int sudija_sudijaId) {
		this.sudija_sudijaId = sudija_sudijaId;
	}

	private String toPrint="";
	public void setToPrint(String str) {
		toPrint = str;
	}
	
	@Override
	public String toString() {
		return toPrint;
	}
	
}
