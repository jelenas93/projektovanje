drop schema if exists multipleks;
create schema multipleks;
use multipleks;

create table Zaposleni
(
idZaposlenog int primary key,
idPlate int ,
ime varchar(50),
prezime varchar(50),
JMBG char(13),
aktivan boolean
);

create table Plata
(
idPlate int primary key,
doprinosZaPenziono decimal(10,2),
doprinosZaZdravstveno decimal(10,2),
doprinosZaDjecijuZastitu decimal(10,2),
doprinosZaZaposljavanje decimal(10,2),
stopaPoreza decimal(10,2),
stopaZaPenziono decimal(10,2),
stopaZaZdravstveno decimal(10,2),
stopaZaDjecijuZastitu decimal(10,2),
stopaZaZaposljavanje decimal(10,2),
netoTekuciRad decimal(10,2),
netoMinuliRad decimal(10,2),
porezNaPlatu decimal(10,2),
datumOd date,
datumDo date
);

create table Nalog
(
korisnickoIme varchar(20) primary key,
hashLozinke varchar(500) primary key
);

alter table Zaposleni add column idPlate int;
alter table Zaposleni add foreign key(idPlate) references Plata(idPlate);

alter table Zaposleni add column korisnickoIme varchar(20);

create table Administrator
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table Racunovodja
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table Kinooperater
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table ProdavacHraneIPica
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table Skladistar
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table Menadzer
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table ProdavacKarata
(
idZaposlenog int primary key,
foreign key(idZaposlenog) references Zapoleni(idZaposlenog)
);

create table Transakcije
(
idTransakcije int primary key,
idZaposlenog int,
vrstaTransakcije varchar(50),
primaoc varchar(50),
posiljaoc varchar(50),
foreign key(idZaposlenog) references Zaposleni(idZaposlenog)
);

create table UlaznaFaktura
(
idFakture int primary key,
idZaposlenog int,
foreign key(idZaposelnog) references Zaposleni(idZaposlenog),
brojRacuna char(16),
vrstaTransakcije varchar(50),
jedinicaMjere varchar(20),
kolicina decimal(12,3),
cijena decimal(12,3),
kupac varchar(20),
datum date
);

create table Oprema
(
idOpreme int primary key,
brojInventara int,
naziv varchar(50),
ispravnost bool,
idZaposlenog int,
foreign key (idZaposlenog) references Zaposleni(idZaposlenog)
);

create table UlaznaFaktura_Oprema
(
idOpreme int,
idFakture int,
primary key(idOpreme, idFakture),
foreign key(idOpreme) references Oprema(idOpreme),
foreign key(idFakture) references UlaznaFaktura(idFakture)
);

create table UlaznaFaktura_Artikal
(
idArtikla int,
idFakture int,
primary key(idArtikla, idFakture),
foreign key (idFakture) references UlaznaFaktura(idFakture)
);

create table Film
(
idFilma int,
idZaposlenog int,
naziv varchar(45),
trajanje int not null,
opis varchar(200),
link varchar(20),
tipFilma varchar(2),
primary key(idFilma),
foreign key(idZaposlenog) references Zaposleni(idZaposlenog)
);

create table Zanr
(
idZanra int,
nazivZanra varchar(20),
primary key(idZanra)
);

create table Ponuda
(
idPonude int,
datumponude date not null,
idZaposlenog int,
primary key(idZanra)
);

create table FilmPonuda
(
idFilma int,
idPonude int,
primary key(idPonude),
foreign key(idFilma) references Film(idFilma)
);

create table FilmZanr
(
idFilma int,
idZanra int,
primary key(idZanra),
foreign key(idFilma) references Film(idFilma)
);

create table Repertoar
(
idRepertoara int,
idProjekcije int,
idFilma int,
idZaposlenog int,
datumOd date,
datumDo date,
foreign key(idProjekcije) references Projekcija(idProjekcije),
foreign key(idFilma) references Film(idFilma),
foreign key(idZaposlenog) references Zaposleni(idZaposlenog),
primary key(idRepertora,idProjekcije, idFilma)
);

