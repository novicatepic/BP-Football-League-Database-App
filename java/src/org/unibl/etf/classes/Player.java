package org.unibl.etf.classes;

public class Player {

	private int ZAPOSLENIK_ZapolseniId;
	private int brojOpreme;
	private int brojGolova;
	private int brojAsistencija;
	private int brojZutihKartona;
	private int brojCrvenihKartona;
	
	public Player() {
		super();
	}

	public Player(int zAPOSLENIK_ZapolseniId, int brojOpreme, int brojGolova, int brojAsistencija, int brojZutihKartona,
			int brojCrvenihKartona) {
		super();
		ZAPOSLENIK_ZapolseniId = zAPOSLENIK_ZapolseniId;
		this.brojOpreme = brojOpreme;
		this.brojGolova = brojGolova;
		this.brojAsistencija = brojAsistencija;
		this.brojZutihKartona = brojZutihKartona;
		this.brojCrvenihKartona = brojCrvenihKartona;
	}

	public int getZAPOSLENIK_ZapolseniId() {
		return ZAPOSLENIK_ZapolseniId;
	}

	public void setZAPOSLENIK_ZapolseniId(int zAPOSLENIK_ZapolseniId) {
		ZAPOSLENIK_ZapolseniId = zAPOSLENIK_ZapolseniId;
	}

	public int getBrojOpreme() {
		return brojOpreme;
	}

	public void setBrojOpreme(int brojOpreme) {
		this.brojOpreme = brojOpreme;
	}

	public int getBrojGolova() {
		return brojGolova;
	}

	public void setBrojGolova(int brojGolova) {
		this.brojGolova = brojGolova;
	}

	public int getBrojAsistencija() {
		return brojAsistencija;
	}

	public void setBrojAsistencija(int brojAsistencija) {
		this.brojAsistencija = brojAsistencija;
	}

	public int getBrojZutihKartona() {
		return brojZutihKartona;
	}

	public void setBrojZutihKartona(int brojZutihKartona) {
		this.brojZutihKartona = brojZutihKartona;
	}

	public int getBrojCrvenihKartona() {
		return brojCrvenihKartona;
	}

	public void setBrojCrvenihKartona(int brojCrvenihKartona) {
		this.brojCrvenihKartona = brojCrvenihKartona;
	}

	private String toDisplay;
	public void setDisplay(String str) {
		toDisplay = str;
	}
	
	@Override
	public String toString() {
		return toDisplay;
	}
	
	
}
