package org.unibl.etf.classes;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.crypto.Data;

public class FootballClub implements Serializable {
	private static final long serialVersionUID = 6509688041213616985L;
	private int idKluba;
	private String naziv;
	private Date datumOsnivanja;
	private int brojOsvojenihTrofeja;
	private int StadionId;
	
	public FootballClub() {
		
	}
	
	public FootballClub(int idKluba, String naziv, Date datumOsnivanja, int brojOsvojenihTrofeja, int stadionId) {
		super();
		this.idKluba = idKluba;
		this.naziv = naziv;
		this.datumOsnivanja = datumOsnivanja;
		this.brojOsvojenihTrofeja = brojOsvojenihTrofeja;
		StadionId = stadionId;
	}

	public int getIdKluba() {
		return idKluba;
	}

	public void setIdKluba(int idKluba) {
		this.idKluba = idKluba;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Date getDatumOsnivanja() {
		return datumOsnivanja;
	}

	public void setDatumOsnivanja(Date datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}

	public int getBrojOsvojenihTrofeja() {
		return brojOsvojenihTrofeja;
	}

	public void setBrojOsvojenihTrofeja(int brojOsvojenihTrofeja) {
		this.brojOsvojenihTrofeja = brojOsvojenihTrofeja;
	}

	public int getStadionId() {
		return StadionId;
	}

	public void setStadionId(int stadionId) {
		StadionId = stadionId;
	}

	@Override
	public String toString() {
		return "FootballClub [idKluba=" + idKluba + ", naziv=" + naziv + ", datumOsnivanja=" + datumOsnivanja
				+ ", brojOsvojenihTrofeja=" + brojOsvojenihTrofeja + ", StadionId=" + StadionId + "]";
	}
	
	
	
}
