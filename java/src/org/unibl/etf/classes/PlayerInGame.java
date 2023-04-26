package org.unibl.etf.classes;

public class PlayerInGame {

	private int igrac_zaposlenik_zapolseniId;
	private int brojGolovaNaUtakmici;
	private int brojAsistencijaNaUtakmici;
	private boolean dobioZuti;
	private boolean dobioCrveni;
	private int klub_na_utakmici_fudbalski_klub_idKluba;
	private int klub_na_utakmici_utakmica_utakmicaId;
	private boolean poceoUtakmicu;
	private int odigraoMinuta;
	public PlayerInGame() {
		super();
	}
	public PlayerInGame(int igrac_zaposlenik_zapolseniId, int brojGolovaNaUtakmici, int brojAsistencijaNaUtakmici,
			boolean dobioZuti, boolean dobioCrveni, int klub_na_utakmici_fudbalski_klub_idKluba,
			int klub_na_utakmici_utakmica_utakmicaId, boolean poceoUtakmicu, int odigraoMinuta) {
		super();
		this.igrac_zaposlenik_zapolseniId = igrac_zaposlenik_zapolseniId;
		this.brojGolovaNaUtakmici = brojGolovaNaUtakmici;
		this.brojAsistencijaNaUtakmici = brojAsistencijaNaUtakmici;
		this.dobioZuti = dobioZuti;
		this.dobioCrveni = dobioCrveni;
		this.klub_na_utakmici_fudbalski_klub_idKluba = klub_na_utakmici_fudbalski_klub_idKluba;
		this.klub_na_utakmici_utakmica_utakmicaId = klub_na_utakmici_utakmica_utakmicaId;
		this.poceoUtakmicu = poceoUtakmicu;
		this.odigraoMinuta = odigraoMinuta;
	}
	public int getIgrac_zaposlenik_zapolseniId() {
		return igrac_zaposlenik_zapolseniId;
	}
	public void setIgrac_zaposlenik_zapolseniId(int igrac_zaposlenik_zapolseniId) {
		this.igrac_zaposlenik_zapolseniId = igrac_zaposlenik_zapolseniId;
	}
	public int getBrojGolovaNaUtakmici() {
		return brojGolovaNaUtakmici;
	}
	public void setBrojGolovaNaUtakmici(int brojGolovaNaUtakmici) {
		this.brojGolovaNaUtakmici = brojGolovaNaUtakmici;
	}
	public int getBrojAsistencijaNaUtakmici() {
		return brojAsistencijaNaUtakmici;
	}
	public void setBrojAsistencijaNaUtakmici(int brojAsistencijaNaUtakmici) {
		this.brojAsistencijaNaUtakmici = brojAsistencijaNaUtakmici;
	}
	public boolean isDobioZuti() {
		return dobioZuti;
	}
	public void setDobioZuti(boolean dobioZuti) {
		this.dobioZuti = dobioZuti;
	}
	public boolean isDobioCrveni() {
		return dobioCrveni;
	}
	public void setDobioCrveni(boolean dobioCrveni) {
		this.dobioCrveni = dobioCrveni;
	}
	public int getKlub_na_utakmici_fudbalski_klub_idKluba() {
		return klub_na_utakmici_fudbalski_klub_idKluba;
	}
	public void setKlub_na_utakmici_fudbalski_klub_idKluba(int klub_na_utakmici_fudbalski_klub_idKluba) {
		this.klub_na_utakmici_fudbalski_klub_idKluba = klub_na_utakmici_fudbalski_klub_idKluba;
	}
	public int getKlub_na_utakmici_utakmica_utakmicaId() {
		return klub_na_utakmici_utakmica_utakmicaId;
	}
	public void setKlub_na_utakmici_utakmica_utakmicaId(int klub_na_utakmici_utakmica_utakmicaId) {
		this.klub_na_utakmici_utakmica_utakmicaId = klub_na_utakmici_utakmica_utakmicaId;
	}
	public boolean isPoceoUtakmicu() {
		return poceoUtakmicu;
	}
	public void setPoceoUtakmicu(boolean poceoUtakmicu) {
		this.poceoUtakmicu = poceoUtakmicu;
	}
	public int getOdigraoMinuta() {
		return odigraoMinuta;
	}
	public void setOdigraoMinuta(int odigraoMinuta) {
		this.odigraoMinuta = odigraoMinuta;
	}
}
