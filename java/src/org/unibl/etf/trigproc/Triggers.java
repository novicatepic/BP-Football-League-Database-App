package org.unibl.etf.trigproc;

public class Triggers {

	public static String update_player_trigger  = "delimiter $$ \r\n"
			+ "create trigger update_player_stats after insert on igrac_na_utakmici for each row\r\n"
			+ "begin\r\n"
			+ "	update igrac i\r\n"
			+ "    set i.BrojGolova=i.BrojGolova+new.BrojGolovaNaUtakmici,\r\n"
			+ "    i.BrojAsistencija=i.BrojAsistencija+new.BrojAsistencijaNaUtakmici,\r\n"
			+ "    i.BrojZutihKartona=i.BrojZutihKartona+new.DobioZuti,\r\n"
			+ "    i.BrojCrvenihKartona=i.BrojCrvenihKartona+new.DobioCrveni\r\n"
			+ "    where i.ZAPOSLENIK_ZapolseniId=IGRAC_ZAPOSLENIK_ZapolseniId;\r\n"
			+ "end $$ delimiter ;";
	
}
