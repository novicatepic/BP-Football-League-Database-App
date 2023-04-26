package org.unibl.etf.classes;

import java.sql.Date;

public class Worker {

	private int zapolseniId;
	private String ime;
	private String prezime;
	private Date datumZaposlenja;
	private int fudbalski_klub_idKluba;
	public Worker() {
		super();
	}
	public Worker(int zapolseniId, String ime, String prezime, Date datumZaposlenja, int fudbalski_klub_idKluba) {
		super();
		this.zapolseniId = zapolseniId;
		this.ime = ime;
		this.prezime = prezime;
		this.datumZaposlenja = datumZaposlenja;
		this.fudbalski_klub_idKluba = fudbalski_klub_idKluba;
	}
	public int getZapolseniId() {
		return zapolseniId;
	}
	public void setZapolseniId(int zapolseniId) {
		this.zapolseniId = zapolseniId;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public Date getDatumZaposlenja() {
		return datumZaposlenja;
	}
	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}
	public int getFudbalski_klub_idKluba() {
		return fudbalski_klub_idKluba;
	}
	public void setFudbalski_klub_idKluba(int fudbalski_klub_idKluba) {
		this.fudbalski_klub_idKluba = fudbalski_klub_idKluba;
	}
	@Override
	public String toString() {
		return "Worker [zapolseniId=" + zapolseniId + ", ime=" + ime + ", prezime=" + prezime + ", datumZaposlenja="
				+ datumZaposlenja + ", fudbalski_klub_idKluba=" + fudbalski_klub_idKluba + "]";
	}
	
	
}
