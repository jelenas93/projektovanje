use multipleks;

#dodavanje novog klijenta
insert into Nalog values ("Cijeli","Cijeli");
insert into Klijent values (default,"Cijeli","Marko","Polovina","polovinamarko95@gmail.com");

#provjera logina
select * from Nalog where korisnickoIme = "Cijeli" and hashLozinke = "Cbla";


#dodavanje novog zaposlenog
select * from Nalog;

#dodavanje nove plate

insert into Plata values(default,15,20,30,40,0.17,0.1,0.2,0.3,0.5,100,500,50,'2010-02-18',null);

select * from plata;
#dodavanje nogvog zaposlenog od strane administratora
insert into Nalog values ("Zelimir","Zelimir");

insert into Zaposleni values(default,null,"Zelimir","Grahovac",'1234567891231',true,"Zelimir");
select last_insert_id() from Zaposleni into @id;
insert into Menadzer values (@id);

select * from Menadzer;

insert into Nalog values ("Stevo","Stevo");
insert into Zaposleni values(default,null,"Stevo","Stevic",'1234544891231',true,"Stevo");
insert into Menadzer values (2);

#dodavanje plate za zaposlenog
update Zaposleni
set idPlate=1
where idZaposlenog = 1;

select * from Zaposleni;

# promjena lozinke
update Nalog 
set hashLozinke = "Cijeli2"
where korisnickoIme = "Cijeli";

select * from Nalog;

select * from Plata;

#update bilo cega

update Plata
set doprinosZaPenziono = 54,
    doprinosZaZdravstveno = 16
where idPlate = 2;

delete from Plata where idPlate = 1;

select * from Zaposleni;

select * from Film;
#dodavanje novog filma
insert into Zanr values(default,"Akcija");
insert into Zanr values(default,"Komedija");
insert into Film values(default,1,"Prvi film",45,"mnogo kul film","www.xxx.com","2d");
select  idZanra from Zanr where nazivZanra = "Akcija" into @z;
select last_insert_id() from Film into @f; 
insert into FilmZanr values (@f,@z);

select * from FilmZanr;
#dodavanje nove projekcije
insert into Projekcija values (default,1,'2006-01-31',1);

select * from Projekcija;
insert into Projekcija values (default,1,'2006-01-21',2);

update Plata
set		datumDo = '2022-09-21'
where idPlate < 3;

#trazenje odredjenih projekcija

select f.naziv,p.vrijemeFilma from Projekcija as p inner join Film as f;# where f.naziv = "Prvi film";
insert into repertoar values (default,1,'2006-01-31','2007-01-31');
insert into repertoarProjekcija values (1,1);
insert into repertoarProjekcija values (1,2);

#izlistavanje svih projekcija sa repertoara 
select f.naziv,p.idProjekcije,p.idFilma,p.VrijemeFilma from RepertoarProjekcija as rp inner join Projekcija as p inner join Film as f where rp.idProjekcije = p.idProjekcije and p.idFilma=f.idFilma;


insert into Plata values(2,0,0,0,0,0,0,0,0,0,0,0,0,"00-00-00","00-00-00");


select * from Plata;

insert into Administrator values(1);

update Film set idZaposlenog = 9,
                            naziv = 'TerminatorSila',
                            trajanje = 5000,
                            opis = "Bona",
                            link = "link",
                            tipFilma = "2D" 
                        where idFilma = 3;

insert into nalog values ("Coa","Coa");

insert into ProdavacHraneIPica values(6);
delete from Plata where idPlate = 2;
select * from Nalog;
select * from Zaposleni;
select * from Oprema;
select * from Administrator;
select * from Klijent;
insert into Administrator values(9);

update Nalog
set hashLozinke = "92668751"
where korisnickoIme = "admin";

insert into Racunovodja values(6);
insert into Zaposleni values(default, 9, 'Ime', 'Prezime', '2589631470357', true, 'Obavezno');

select last_insert_id() from Zaposleni;

update nalog 
set hashLozinke = ""
where korisnickoIme = "jelena";

delete from Administrator where idZaposlenog = 9;

select * from Film;
insert into Karta values(default,'1970-01-01 00:00:00', 4.5, false, "Cijeli");

select * from filmponuda;
select * from Ponuda;

delete from filmzanr where idFilma = 3 or idFilma = 5 or idFilma = 6;
delete from Film where idFilma = 3 or idFilma = 5 or idFilma = 6;

select * from Sala;
select * from Sjediste;
select * from ulaznafaktura;
select * from ulaznafaktura_oprema;
select * from projekcija;
select * from repertoar;

update projekcija 
set idRepertoara = 1,
	idSale = 2;
    
alter table Plata add column brutoMinuliRad decimal(10,2);
alter table plata add column doprinosi decimal(10,2);
alter table plata add column isplataRadniku decimal(10,2);
update plata set brutoMinuliRad = 1;
update plata set doprinosi = 1;
update plata set isplataRadniku = 1;
alter table plata drop column brutoMinuliRad;

select * from Film;

update Film set posterFilma = 1;
