/*ovaj triger funkcionise - triger za dodavanje statistike igraca*/
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
drop trigger update_player_stats;

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
drop trigger delete_player_stats;

/*nece ispod dio sa points!!!*/
delimiter $$
create trigger update_team_stats after insert on klub_na_utakmici
for each row
begin
	/*declare points int default 0;
    if BrojPostignutihGolova>BrojPrimljenihGolova then
		set points=3;*/
    /*elseif BrojPostignutihGolova=BrojPrimljenihGolova then
		set points=1;*/
    /*end if;*/
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova+new.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova+new.BrojPrimljenihGolova,
    sz.BrojPobjeda=sz.BrojPobjeda+(new.BrojPostignutihGolova>new.BrojPrimljenihGolova),
    sz.BrojNerijesenih=sz.BrojNerijesenih+(new.BrojPostignutihGolova=new.BrojPrimljenihGolova),
    sz.BrojPoraza=sz.BrojPoraza+(new.BrojPostignutihGolova<new.BrojPrimljenihGolova),
    sz.BrojOdigranihUtakmica=sz.BrojOdigranihUtakmica+1,
    sz.BrojBodova=sz.BrojBodova+1
    where sz.FUDBALSKI_KLUB_IdKluba=new.FUDBALSKI_KLUB_IdKluba;
end$$
delimiter ;

drop trigger update_team_stats;

delimiter $$
create trigger delete_team_stats after delete on klub_na_utakmici
for each row
begin
	/*declare points int default 0;
    if BrojPostignutihGolova>BrojPrimljenihGolova then
		set points=3;*/
    /*elseif BrojPostignutihGolova=BrojPrimljenihGolova then
		set points=1;*/
    /*end if;*/
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova-old.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova-old.BrojPrimljenihGolova,
    sz.BrojPobjeda=sz.BrojPobjeda-(old.BrojPostignutihGolova>old.BrojPrimljenihGolova),
    sz.BrojNerijesenih=sz.BrojNerijesenih-(old.BrojPostignutihGolova=old.BrojPrimljenihGolova),
    sz.BrojPoraza=sz.BrojPoraza-(old.BrojPostignutihGolova<old.BrojPrimljenihGolova),
    sz.BrojOdigranihUtakmica=sz.BrojOdigranihUtakmica-1,
    sz.BrojBodova=sz.BrojBodova-1
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

delimiter $$
create procedure find_fixture_based_on_game(in pGame int, out fixture int) 
begin
	set fixture=-1;
    select u.KOLO_IdKola into fixture
    from utakmica u 
    where u.UtakmicaId=pGame;
end$$
delimiter ;

delimiter $$
create trigger did_team_play before insert on klub_na_utakmici
for each row
begin
	call find_fixture_based_on_game(new.UTAKMICA_UtakmicaId, @fixture);
    call check_club_in_game(@fixture, 3, @pstatus);
    if @pstatus=1 then
		signal sqlstate '45000' set message_text='Vec postoji unos!';
    end if;
end$$
delimiter ;

drop trigger did_team_play;

delimiter $$
create procedure check_club_in_game(in pKolo int,in pKlubId int, out pStatus bool) 
begin
    select count(*)>0 into pStatus
    from klub_na_utakmici knu 
    inner join utakmica u on knu.UTAKMICA_UtakmicaId=u.UtakmicaId
    where u.KOLO_IdKola=pKolo and knu.FUDBALSKI_KLUB_IdKluba=pKlubId;
    if pStatus=1 then
		signal sqlstate '45000' set message_text='Vec postoji unos!';
    end if;
end$$
delimiter ;

drop procedure check_club_in_game;
drop procedure find_fixture_based_on_game;

call find_fixture_based_on_game(50, @fixture);
select @fixture;
call check_club_in_game(@fixture, 1, @pstatus);
select @pstatus;

insert into utakmica (UtakmicaId, KOLO_IdKola, GLAVNI_SUDIJA_SUDIJA_SudijaId, DatumUtakmice)
values(50, 1, 1, date'2021-05-05');
insert into klub_na_utakmici (FUDBALSKI_KLUB_IdKluba, UTAKMICA_UtakmicaId, BrojPostignutihGolova, IsDomacin, BrojPrimljenihGolova)
values (2, 50, 2, 1, 2);

/*RADI*/
/*select * from utakmica 
where utakmica.KOLO_IdKola=@fixture;*/
