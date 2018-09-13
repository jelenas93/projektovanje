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

