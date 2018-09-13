drop schema if exists multipleks;
create schema multipleks;
use multipleks;

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

create table Reperoar
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

create table Klijent
(
idKlijenta int primary key,
korisnickoIme varchar(20),
ime varchar(20),
prezime varchar(20),
email varchar(30)
);

create table Karta
(
idKarte int,
datumIzdavanja datetime,
cijena decimal(6,2),
rezervisana boolean,
korisnickoIme varchar(20),
primary key (idKarte),
foreign key (korisnickoIme) references Klijent(korisnickoIme)# provjeriti sa cijelim nisam siguran sta treba referencirati
);

create table Projekcija
(
idProjekcije int,
idFilma int,
vrijemeFilma int,
idZaposlenog int,
primary key (idProjekcije)
);

create table Sala
(
idSale int,
brojVrsta int,
brojKolona int, 
primary key (idSale)
);

create table Sjediste
(
idSjedista int,
idSale int,
vrsta int,
kolona int,
primary key (idSjedista),
foreign key (idSale) references Sala(idSale)
);

create table Izdavanje
(
idKarte int,
idSjedista int,
idSale int,
idProjekcije int,
idFilma int,
idZaposlenog int,
foreign key (idKarte) references Karta(idKarte),
foreign key (idSjedista) references Sjediste(idSjedista),
foreign key (idSale) references Sala(idSale),
foreign key (idProjekcije) references Projekcija(idProjekcije),
foreign key (idFilma) references Film(idFilma)
);

alter table Izdavanje add foreign key (idZaposlenog) references Zaposleni(idZaposlenog);

create table Artikal
(
idArtikla int primary key,
naziv varchar(30),
kolicinaNaStanju int,
jedinicnaCijena decimal(6,3),
tip varchar(20),
barKod varchar(20),
idZaposlenog int
);

alter table Artikal add foreign key (idZaposlenog) references Zaposleni(idZaposlenog);

create table Stavka
(
idStavke int primary key,
kolicina int,
ukupnaCijena decimal(6,3),
idArtikla int,
foreign key (idArtikla) references Artikal(idArtikla)
);

create table Racun
(
idRacuna int primary key,
idStavke int,
datumIzdavanja datetime,
ukupnaCijena decimal(12,2),
idZaposlenog int,
kolicina float
);

alter table Racun add foreign key (idZaposlenog) references Zaposleni(idZaposlenog);

alter table UlaznaFaktura_Artikal add foreign key (idArtikla) references Arikal(idArtikla);
