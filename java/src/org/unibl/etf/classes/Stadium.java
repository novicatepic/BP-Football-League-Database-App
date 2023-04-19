package org.unibl.etf.classes;

import java.io.Serializable;

public class Stadium implements Serializable {
	private static final long serialVersionUID = 6509688041213616984L;
	private int stadionId;
	private String naziv;
	private int kapacitet;
	private String grad;
	
	public Stadium() {
		
	}

	public Stadium(int stadionId, String naziv, int kapacitet, String grad) {
		super();
		this.stadionId = stadionId;
		this.naziv = naziv;
		this.kapacitet = kapacitet;
		this.grad = grad;
	}

	public int getStadionId() {
		return stadionId;
	}

	public void setStadionId(int stadionId) {
		this.stadionId = stadionId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	@Override
	public String toString() {
		return naziv;
	}
	
	
	
}
