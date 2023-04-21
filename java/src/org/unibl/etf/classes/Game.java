package org.unibl.etf.classes;

import java.io.Serializable;
import java.sql.Date;

public class Game implements Serializable {

	private int utakmicaId;
	private int kolo_idKola;
	private int glavni_sudija_sudija_sudijaId;
	private Date datumUtakmice;
	
	public Game() {
		super();
	}

	public Game(int utakmicaId, int kolo_idKola, int glavni_sudija_sudija_sudijaId, Date datumUtakmice) {
		super();
		this.utakmicaId = utakmicaId;
		this.kolo_idKola = kolo_idKola;
		this.glavni_sudija_sudija_sudijaId = glavni_sudija_sudija_sudijaId;
		this.datumUtakmice = datumUtakmice;
	}

	public int getUtakmicaId() {
		return utakmicaId;
	}

	public void setUtakmicaId(int utakmicaId) {
		this.utakmicaId = utakmicaId;
	}

	public int getKolo_idKola() {
		return kolo_idKola;
	}

	public void setKolo_idKola(int kolo_idKola) {
		this.kolo_idKola = kolo_idKola;
	}

	public int getGlavni_sudija_sudija_sudijaId() {
		return glavni_sudija_sudija_sudijaId;
	}

	public void setGlavni_sudija_sudija_sudijaId(int glavni_sudija_sudija_sudijaId) {
		this.glavni_sudija_sudija_sudijaId = glavni_sudija_sudija_sudijaId;
	}

	public Date getDatumUtakmice() {
		return datumUtakmice;
	}

	public void setDatumUtakmice(Date datumUtakmice) {
		this.datumUtakmice = datumUtakmice;
	}

	@Override
	public String toString() {
		return "Game [utakmicaId=" + utakmicaId + ", kolo_idKola=" + kolo_idKola + ", glavni_sudija_sudija_sudijaId="
				+ glavni_sudija_sudija_sudijaId + ", datumUtakmice=" + datumUtakmice + "]";
	}
	
}
