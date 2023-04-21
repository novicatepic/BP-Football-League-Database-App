package org.unibl.etf.classes;

import java.io.Serializable;

public class TeamInGame implements Serializable {

	private int fudbalski_klub_idKluba;
	private int utakmica_utakmicaId;
	private int brojPostignutihGolova;
	private boolean isDomacin;
	private int brojPrimljenihGolova;
	public TeamInGame() {
		super();
	}
	public TeamInGame(int fudbalski_klub_idKluba, int utakmica_utakmicaId, int brojPostignutihGolova, boolean isDomacin,
			int brojPrimljenihGolova) {
		super();
		this.fudbalski_klub_idKluba = fudbalski_klub_idKluba;
		this.utakmica_utakmicaId = utakmica_utakmicaId;
		this.brojPostignutihGolova = brojPostignutihGolova;
		this.isDomacin = isDomacin;
		this.brojPrimljenihGolova = brojPrimljenihGolova;
	}
	public int getFudbalski_klub_idKluba() {
		return fudbalski_klub_idKluba;
	}
	public void setFudbalski_klub_idKluba(int fudbalski_klub_idKluba) {
		this.fudbalski_klub_idKluba = fudbalski_klub_idKluba;
	}
	public int getUtakmica_utakmicaId() {
		return utakmica_utakmicaId;
	}
	public void setUtakmica_utakmicaId(int utakmica_utakmicaId) {
		this.utakmica_utakmicaId = utakmica_utakmicaId;
	}
	public int getBrojPostignutihGolova() {
		return brojPostignutihGolova;
	}
	public void setBrojPostignutihGolova(int brojPostignutihGolova) {
		this.brojPostignutihGolova = brojPostignutihGolova;
	}
	public boolean isDomacin() {
		return isDomacin;
	}
	public void setDomacin(boolean isDomacin) {
		this.isDomacin = isDomacin;
	}
	public int getBrojPrimljenihGolova() {
		return brojPrimljenihGolova;
	}
	public void setBrojPrimljenihGolova(int brojPrimljenihGolova) {
		this.brojPrimljenihGolova = brojPrimljenihGolova;
	}
	@Override
	public String toString() {
		return "TeamInGame [fudbalski_klub_idKluba=" + fudbalski_klub_idKluba + ", utakmica_utakmicaId="
				+ utakmica_utakmicaId + ", brojPostignutihGolova=" + brojPostignutihGolova + ", isDomacin=" + isDomacin
				+ ", brojPrimljenihGolova=" + brojPrimljenihGolova + "]";
	}
}
