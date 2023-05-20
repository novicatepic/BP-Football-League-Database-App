/*triger koji provjerava, pri unosu igraca, da li je uneseno previse golova/asistencija u odnosu na proslijedjene golove i asistencije*/
delimiter $$ 
create trigger check_stats_before_insert before insert on igrac_na_utakmici 
for each row
begin
	declare vBrojPostignutihGolova int default 0;
    declare vBrojPrimljenihGolova int default 0;
    declare vBrojPostignutihGolovaDoSad int default 0;
    declare vBrojAsistencijaDoSad int default 0;
    
	if new.OdigraoMinuta<0 or new.OdigraoMinuta>90 then
		signal sqlstate '45000' set message_text='Nevalidan unos minuta!';
    end if;
    
    /*nadji broj postignutih golova kluba na utakmici*/
    select knu.BrojPostignutihGolova into vBrojPostignutihGolova
    from klub_na_utakmici knu
    where new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=knu.FUDBALSKI_KLUB_IdKluba 
    and new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=knu.UTAKMICA_UtakmicaId;

	/*nadji broj primljenih golova kluba na utakmici => bespotrebno*/
    select knu.BrojPrimljenihGolova into vBrojPrimljenihGolova
    from klub_na_utakmici knu
    where new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=knu.FUDBALSKI_KLUB_IdKluba 
    and new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=knu.UTAKMICA_UtakmicaId;
	
    /*nadji do sada broj postignutih golova ostalih igraca iz tog tima na toj utakmici*/
    select ifnull(sum(inu.BrojGolovaNaUtakmici), 0) into vBrojPostignutihGolovaDoSad
    from igrac_na_utakmici inu 
    where inu.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId
    and new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=inu.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba;
    
    /*nadji do sada broj asistencija ostalih igraca iz tog tima na toj utakmici*/
    select ifnull(sum(inu.BrojAsistencijaNaUtakmici), 0) into vBrojAsistencijaDoSad
    from igrac_na_utakmici inu 
    where inu.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId
    and new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=inu.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba;
    
    /*ako je broj do sada asistencija, golova, novih asistencija i novih golova prekoracio limit => ne moze!*/
    if vBrojPostignutihGolovaDoSad+vBrojAsistencijaDoSad+new.BrojGolovaNaUtakmici+new.BrojAsistencijaNaUtakmici>vBrojPostignutihGolova then
		signal sqlstate '45000' set message_text='Previse golova!';
    end if;
end$$ 
delimiter ;

/*triger za dodavanje statistike igraca*/
delimiter $$ 
create trigger update_player_stats after insert on igrac_na_utakmici 
for each row
begin
	update igrac i
    set i.BrojGolova=i.BrojGolova+new.BrojGolovaNaUtakmici,
    i.BrojAsistencija=i.BrojAsistencija+new.BrojAsistencijaNaUtakmici,
    i.BrojZutihKartona=i.BrojZutihKartona+new.DobioZuti,
    i.BrojCrvenihKartona=i.BrojCrvenihKartona+new.DobioCrveni
    where i.ZAPOSLENIK_ZapolseniId=new.IGRAC_ZAPOSLENIK_ZapolseniId;
end$$ 
delimiter ;
/*drop trigger update_player_stats;*/

/*triger za brisanje statistike*/
delimiter $$ 
create trigger delete_player_stats after delete on igrac_na_utakmici 
for each row
begin
	update igrac i
    set i.BrojGolova=i.BrojGolova-old.BrojGolovaNaUtakmici,
    i.BrojAsistencija=i.BrojAsistencija-old.BrojAsistencijaNaUtakmici,
    i.BrojZutihKartona=i.BrojZutihKartona-old.DobioZuti,
    i.BrojCrvenihKartona=i.BrojCrvenihKartona-old.DobioCrveni
    where i.ZAPOSLENIK_ZapolseniId=old.IGRAC_ZAPOSLENIK_ZapolseniId;
end$$ 
delimiter ;
/*drop trigger delete_player_stats;*/

/*triger za azuriranje statistike tima nakon evidentiranja utakmice*/
delimiter $$
create trigger update_team_stats after insert on klub_na_utakmici
for each row
begin
	declare points int default 0;
    if new.BrojPostignutihGolova>new.BrojPrimljenihGolova then
		set points=3;
    elseif new.BrojPostignutihGolova=new.BrojPrimljenihGolova then
		set points=1;
    end if;
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova+new.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova+new.BrojPrimljenihGolova,
    sz.BrojPobjeda=sz.BrojPobjeda+(new.BrojPostignutihGolova>new.BrojPrimljenihGolova),
    sz.BrojNerijesenih=sz.BrojNerijesenih+(new.BrojPostignutihGolova=new.BrojPrimljenihGolova),
    sz.BrojPoraza=sz.BrojPoraza+(new.BrojPostignutihGolova<new.BrojPrimljenihGolova),
    sz.BrojOdigranihUtakmica=sz.BrojOdigranihUtakmica+1,
    sz.BrojBodova=sz.BrojBodova+points
    where sz.FUDBALSKI_KLUB_IdKluba=new.FUDBALSKI_KLUB_IdKluba;
end$$
delimiter ;

drop trigger update_team_stats;
drop trigger delete_team_stats;

/*nakon sto se obrise klub na utakmici azurira se njegova statistika*/
delimiter $$
create trigger delete_team_stats after delete on klub_na_utakmici
for each row
begin
	declare points int default -3;
    if old.BrojPostignutihGolova>old.BrojPrimljenihGolova then
		set points=3;
    elseif old.BrojPostignutihGolova=old.BrojPrimljenihGolova then
		set points=1;
    end if;
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova-old.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova-old.BrojPrimljenihGolova,
    sz.BrojPobjeda=sz.BrojPobjeda-(old.BrojPostignutihGolova>old.BrojPrimljenihGolova),
    sz.BrojNerijesenih=sz.BrojNerijesenih-(old.BrojPostignutihGolova=old.BrojPrimljenihGolova),
    sz.BrojPoraza=sz.BrojPoraza-(old.BrojPostignutihGolova<old.BrojPrimljenihGolova),
    sz.BrojOdigranihUtakmica=sz.BrojOdigranihUtakmica-1,
    sz.BrojBodova=sz.BrojBodova-points
    where sz.FUDBALSKI_KLUB_IdKluba=old.FUDBALSKI_KLUB_IdKluba;
end$$
delimiter ;

delimiter $$
create trigger change_team_stats after update on klub_na_utakmici
for each row
begin
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova-old.BrojPostignutihGolova+new.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova-old.BrojPrimljenihGolova+new.BrojPrimljenihGolova
    where sz.FUDBALSKI_KLUB_IdKluba=old.FUDBALSKI_KLUB_IdKluba;
end$$
delimiter ;

/*procedura koja vraca kolo za dati mec*/
delimiter $$
create procedure find_fixture_based_on_game(in pGame int, out fixture int) 
begin
	set fixture=-1;
    select u.KOLO_IdKola into fixture
    from utakmica u 
    where u.UtakmicaId=pGame;
end$$
delimiter ;

/*procedura koja vraca status koji pokazuje da li je tim vec igrao na nekom kolu*/
delimiter $$
create procedure check_club_in_game(in pKolo int,in pKlubId int, out pStatus bool) 
begin
    select count(*)>0 into pStatus
    from klub_na_utakmici knu 
    inner join utakmica u on knu.UTAKMICA_UtakmicaId=u.UtakmicaId
    where u.KOLO_IdKola=pKolo and knu.FUDBALSKI_KLUB_IdKluba=pKlubId;
end$$
delimiter ;

/*triger koji onemogucava unos istog tima u kolu u kojem je vec igrao*/
delimiter $$
create trigger did_team_play before insert on klub_na_utakmici
for each row
begin
	call find_fixture_based_on_game(new.UTAKMICA_UtakmicaId, @fixture);
    call check_club_in_game(@fixture, new.FUDBALSKI_KLUB_IdKluba, @pstatus);
    if @pstatus=1 then
		signal sqlstate '45000' set message_text='Vec postoji unos!';
    end if;
end$$
delimiter ;

/*call find_fixture_based_on_game(50, @fixture);
select @fixture;
call check_club_in_game(@fixture, 1, @pstatus);
select @pstatus;*/

/*test koji pokazuje da prethodni triger radi, mozda nece raditi kasnije!*/
insert into utakmica (UtakmicaId, KOLO_IdKola, GLAVNI_SUDIJA_SUDIJA_SudijaId, DatumUtakmice)
values(50, 1, 1, date'2021-05-05');
insert into klub_na_utakmici (FUDBALSKI_KLUB_IdKluba, UTAKMICA_UtakmicaId, BrojPostignutihGolova, IsDomacin, BrojPrimljenihGolova)
values (2, 50, 2, 1, 2);




