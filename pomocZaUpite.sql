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

#trazenje odredjenih projekcija

select f.naziv,p.vrijemeFilma from Projekcija as p inner join Film as f;# where f.naziv = "Prvi film";
insert into repertoar values (default,1,'2006-01-31','2007-01-31');
insert into repertoarProjekcija values (1,1);
insert into repertoarProjekcija values (1,2);

#izlistavanje svih projekcija sa repertoara 
select f.naziv,p.idProjekcije,p.idFilma,p.VrijemeFilma from RepertoarProjekcija as rp inner join Projekcija as p inner join Film as f where rp.idProjekcije = p.idProjekcije and p.idFilma=f.idFilma;

insert into Plata values(1,0,0,0,0,0,0,0,0,0,0,0,0,"00-00-00","00-00-00");


select * from Film;

update Film set idZaposlenog = 9,
                            naziv = 'TerminatorSila',
                            trajanje = 5000,
                            opis = "Bona",
                            link = "link",
                            tipFilma = "2D" 
                        where idFilma = 3;

insert into nalog values ("Coa","Coa");
