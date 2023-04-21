package org.unibl.etf.classes;

import java.io.Serializable;

public class Referee implements Serializable {

	private int sudijaId;
	private String ime;
	private String prezime;
	private int godineRada;
	private int brojUtakmica;
	public Referee() {
		super();
	}
	public Referee(int sudijaId, String ime, String prezime, int godineRada, int brojUtakmica) {
		super();
		this.sudijaId = sudijaId;
		this.ime = ime;
		this.prezime = prezime;
		this.godineRada = godineRada;
		this.brojUtakmica = brojUtakmica;
	}
	public int getSudijaId() {
		return sudijaId;
	}
	public void setSudijaId(int sudijaId) {
		this.sudijaId = sudijaId;
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
	public int getGodineRada() {
		return godineRada;
	}
	public void setGodineRada(int godineRada) {
		this.godineRada = godineRada;
	}
	public int getBrojUtakmica() {
		return brojUtakmica;
	}
	public void setBrojUtakmica(int brojUtakmica) {
		this.brojUtakmica = brojUtakmica;
	}
	@Override
	public String toString() {
		return "Referee [sudijaId=" + sudijaId + ", ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada
				+ ", brojUtakmica=" + brojUtakmica + "]";
	}
}
