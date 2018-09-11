DROP SCHEMA IF EXISTS bioskop;
CREATE SCHEMA bioskop;
USE bioskop;
CREATE TABLE ADMINISTRATOR
(
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE ADMINISTRATOR
	ADD  PRIMARY KEY (JMBG,IdBioskopa)
;



CREATE TABLE ARTIKAL
(
	NazivArtikla          VARCHAR(20) NOT NULL,
	KolicinaNaStanju      INTEGER NOT NULL,
	Cijena                DECIMAL(10,2) NOT NULL,
	BarKod                VARCHAR(20) NULL,
	Tip                   VARCHAR(20) NOT NULL
)
;



ALTER TABLE ARTIKAL
	ADD  PRIMARY KEY (BarKod)
;



CREATE TABLE BANKA
(
	IdBanke               INTEGER NULL,
	NazivBanke            VARCHAR(20) NOT NULL
)
;



ALTER TABLE BANKA
	ADD  PRIMARY KEY (IdBanke)
;



CREATE TABLE BIOSKOP
(
	IdBioskopa            INTEGER NULL,
	Naziv                 VARCHAR(20) NOT NULL,
	Grad                  VARCHAR(20) NOT NULL,
	Adresa                VARCHAR(500) NOT NULL
)
;



ALTER TABLE BIOSKOP
	ADD  PRIMARY KEY (IdBioskopa)
;



CREATE TABLE DISTRIBUTER
(
	JIB                   INTEGER NOT NULL,
	Naziv                 VARCHAR(20) NOT NULL,
	Adresa                VARCHAR(50) NOT NULL,
	Telefon               VARCHAR(20) NOT NULL
)
;



ALTER TABLE DISTRIBUTER
	ADD  PRIMARY KEY (JIB)
;



CREATE TABLE FILM
(
	IdFilma               INTEGER NULL,
	Naziv                 VARCHAR(50) NOT NULL,
	DatumPocetakPrikazivanja  DATE NOT NULL,
	Opis                  VARCHAR(1000) NULL,
	JIB                   INTEGER NOT NULL,
	IdProdukcijskeKuce    INTEGER NULL,
	DatumKrajPrikazivanja  DATE NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	TrajanjeFilma         INTEGER NOT NULL,
	DatumIzlaskaFilma     DATE NOT NULL,
	Drzava                VARCHAR(20) NOT NULL
)
;



ALTER TABLE FILM
	ADD  PRIMARY KEY (IdFilma)
;



CREATE TABLE FILM_ZANR
(
	IdFilma               INTEGER NOT NULL,
	IdZanra               INTEGER NOT NULL
)
;



ALTER TABLE FILM_ZANR
	ADD  PRIMARY KEY (IdFilma,IdZanra)
;



CREATE TABLE FILMSKA_EKIPA
(
	IdFilma               INTEGER NOT NULL
)
;



ALTER TABLE FILMSKA_EKIPA
	ADD  PRIMARY KEY (IdFilma)
;



CREATE TABLE GLUMAC
(
	IdGlumca              INTEGER NOT NULL,
	Ime                   VARCHAR(20) NOT NULL,
	Prezime               VARCHAR(20) NOT NULL,
	IdFilma               INTEGER NOT NULL
)
;



ALTER TABLE GLUMAC
	ADD  PRIMARY KEY (IdGlumca,IdFilma)
;



CREATE TABLE KARTA
(
	IdKarte               INTEGER NULL,
	DatumIzdavanja        DATE NOT NULL,
	Cijena                INTEGER NOT NULL,
	Rezervisana           boolean NOT NULL,
	IdSjedista            INTEGER NOT NULL,
	IdSale                INTEGER NOT NULL,
	IdProjekcije          INTEGER NOT NULL,
	DatumProjekcije       TIMESTAMP NOT NULL,
	IdFilma               INTEGER NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE KARTA
	ADD  PRIMARY KEY (IdKarte,IdProjekcije,IdSale,IdFilma,JMBG,DatumProjekcije,IdBioskopa)
;



CREATE TABLE KINOOPERATER
(
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE KINOOPERATER
	ADD  PRIMARY KEY (JMBG,IdBioskopa)
;



CREATE TABLE NALOG
(
	LozinkaHash           VARCHAR(50) NOT NULL,
	KorisnickoIme         VARCHAR(20) NOT NULL,
	AdministratorJMBG     VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL
)
;



ALTER TABLE NALOG
	ADD  PRIMARY KEY (IdBioskopa,JMBG)
;



CREATE TABLE OPREMA
(
	Naziv                 VARCHAR(20) NOT NULL,
	Slobodna              boolean NOT NULL,
	Opis                  VARCHAR(100) NULL,
	Proizvodjac           VARCHAR(20) NOT NULL,
	Kolicina              INTEGER NOT NULL,
	SerijskiBroj          VARCHAR(11) NULL,
	IdOpreme              INTEGER NOT NULL
)
;



ALTER TABLE OPREMA
	ADD  PRIMARY KEY (SerijskiBroj)
;



CREATE TABLE OPREMA_U_SALI
(
	IdSale                INTEGER NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	SerijskiBroj          VARCHAR(11) NOT NULL
)
;



ALTER TABLE OPREMA_U_SALI
	ADD  PRIMARY KEY (IdSale,IdBioskopa,SerijskiBroj)
;



CREATE TABLE PRODAVAC_KASA
(
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE PRODAVAC_KASA
	ADD  PRIMARY KEY (JMBG,IdBioskopa)
;



CREATE TABLE PRODUCENT
(
	IdFilma               INTEGER NOT NULL,
	IdProducenta          INTEGER NULL,
	Ime                   VARCHAR(20) NOT NULL,
	Prezime               CHAR(18) NOT NULL
)
;



ALTER TABLE PRODUCENT
	ADD  PRIMARY KEY (IdProducenta,IdFilma)
;



CREATE TABLE PRODUKCIJSKA_KUCA
(
	IdProdukcijskeKuce    INTEGER NULL,
	Naziv                 VARCHAR(50) NOT NULL,
	Adresa                VARCHAR(500) NOT NULL,
	DatumOsnivanja        DATE NULL,
	Grad                  VARCHAR(20) NOT NULL,
	Telefon               VARCHAR(20) NOT NULL
)
;



ALTER TABLE PRODUKCIJSKA_KUCA
	ADD  PRIMARY KEY (IdProdukcijskeKuce)
;



CREATE TABLE PROJEKCIJA
(
	IdProjekcije          INTEGER NULL,
	DatumProjekcije       TIMESTAMP NULL,
	IdSale                INTEGER NOT NULL,
	IdFilma               INTEGER NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE PROJEKCIJA
	ADD  PRIMARY KEY (IdProjekcije,DatumProjekcije,IdSale,IdFilma,IdBioskopa)
;



CREATE TABLE RACUN
(
	DatumIzdavanja        DATE NOT NULL,
	IdRacuna              INTEGER NOT NULL,
	UkupnaCijena          DECIMAL(2) NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE RACUN
	ADD  PRIMARY KEY (IdRacuna,JMBG,IdBioskopa)
;



CREATE TABLE RACUN_KARTA
(
	IdRacuna              INTEGER NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	IdKarte               INTEGER NOT NULL,
	IdSale                INTEGER NOT NULL,
	IdProjekcije          INTEGER NOT NULL,
	DatumProjekcije       TIMESTAMP NOT NULL,
	IdFilma               INTEGER NOT NULL
)
;



ALTER TABLE RACUN_KARTA
	ADD  PRIMARY KEY (IdRacuna,JMBG,IdBioskopa,IdKarte,IdProjekcije,IdSale,IdFilma,DatumProjekcije)
;



CREATE TABLE RACUN_ZAPOSLENOG
(
	BrojRacuna            VARCHAR(16) NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBanke               INTEGER NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE RACUN_ZAPOSLENOG
	ADD  PRIMARY KEY (BrojRacuna)
;



CREATE TABLE REZERVACIJA
(
	Ime                   VARCHAR(20) NOT NULL,
	Prezime               VARCHAR(20) NOT NULL,
	BrojTelefona          VARCHAR(15) NULL,
	IdProjekcije          INTEGER NOT NULL,
	DatumProjekcije       TIMESTAMP NOT NULL,
	IdSale                INTEGER NOT NULL,
	IdSjedista            INTEGER NOT NULL,
	IdFilma               INTEGER NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE REZERVACIJA
	ADD  PRIMARY KEY (BrojTelefona,IdProjekcije,DatumProjekcije,IdSale,IdSjedista,IdFilma,IdBioskopa)
;



CREATE TABLE SALA
(
	IdSale                INTEGER NOT NULL,
	BrojRedova            INTEGER NOT NULL,
	BrojKolona            INTEGER NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	Slobodna              boolean NULL
)
;



ALTER TABLE SALA
	ADD  PRIMARY KEY (IdSale,IdBioskopa)
;



CREATE TABLE SJEDISTE
(
	IdSjedista            INTEGER NOT NULL,
	Red                   INTEGER NOT NULL,
	Kolona                INTEGER NOT NULL,
	IdSale                INTEGER NOT NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE SJEDISTE
	ADD  PRIMARY KEY (IdSjedista)
;



CREATE TABLE STAVKA
(
	IdRacuna              INTEGER NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	BarKod                VARCHAR(20) NOT NULL
)
;



ALTER TABLE STAVKA
	ADD  PRIMARY KEY (IdRacuna,JMBG,IdBioskopa,BarKod)
;



CREATE TABLE TELEFON
(
	IdBioskopa            INTEGER NOT NULL,
	TelefonBioskop        VARCHAR(20) NULL
)
;



ALTER TABLE TELEFON
	ADD  PRIMARY KEY (IdBioskopa,TelefonBioskop)
;



CREATE TABLE TIP_OPREME
(
	IdOpreme              INTEGER NOT NULL,
	Naziv                 VARCHAR(50) NULL
)
;



ALTER TABLE TIP_OPREME
	ADD  PRIMARY KEY (IdOpreme)
;



CREATE TABLE UGOVOR_O_RADU
(
	JMBG                  VARCHAR(13) NOT NULL,
	IdUgovora             INTEGER NOT NULL,
	DatumPocetak          DATE NOT NULL,
	DatumKraj             DATE NULL,
	IznosPlate            DECIMAL(10,2) NOT NULL,
	UgovorVazeci          boolean NULL,
	IdBioskopa            INTEGER NOT NULL
)
;



ALTER TABLE UGOVOR_O_RADU
	ADD  PRIMARY KEY (IdUgovora,JMBG)
;



CREATE TABLE UPLATA_PLATE
(
	IdUgovora             INTEGER NOT NULL,
	BrojUplate            INTEGER NULL,
	DatumUplate           DATE NOT NULL,
	BrojRacuna            VARCHAR(16) NOT NULL,
	JMBG                  VARCHAR(13) NOT NULL
)
;



ALTER TABLE UPLATA_PLATE
	ADD  PRIMARY KEY (BrojUplate,BrojRacuna)
;



CREATE TABLE ZANR
(
	IdZanra               INTEGER NULL,
	Naziv                 VARCHAR(20) NULL
)
;



ALTER TABLE ZANR
	ADD  PRIMARY KEY (IdZanra)
;



CREATE TABLE ZAPOSLENI
(
	Ime                   VARCHAR(20) NOT NULL,
	Prezime               VARCHAR(20) NOT NULL,
	DatumRodjenja         DATE NOT NULL,
	Adresa                VARCHAR(500) NOT NULL,
	MjestoRodjenja        VARCHAR(20) NULL,
	DrzavaRodjenja        VARCHAR(20) NULL,
	Grad                  VARCHAR(20) NOT NULL,
	IdBioskopa            INTEGER NOT NULL,
	JMBG                  VARCHAR(13) NULL
)
;



ALTER TABLE ZAPOSLENI
	ADD  PRIMARY KEY (JMBG,IdBioskopa)
;



ALTER TABLE ADMINISTRATOR
	ADD FOREIGN KEY (JMBG,IdBioskopa) REFERENCES ZAPOSLENI(JMBG,IdBioskopa)
		ON DELETE CASCADE
;



ALTER TABLE FILM
	ADD FOREIGN KEY R_60 (JIB) REFERENCES DISTRIBUTER(JIB)
;


ALTER TABLE FILM
	ADD FOREIGN KEY R_76 (IdProdukcijskeKuce) REFERENCES PRODUKCIJSKA_KUCA(IdProdukcijskeKuce)
;


ALTER TABLE FILM
	ADD FOREIGN KEY DODAJE (JMBG,IdBioskopa) REFERENCES ADMINISTRATOR(JMBG,IdBioskopa)
;



ALTER TABLE FILM_ZANR
	ADD FOREIGN KEY IMA (IdFilma) REFERENCES FILM(IdFilma)
;


ALTER TABLE FILM_ZANR
	ADD FOREIGN KEY R_22 (IdZanra) REFERENCES ZANR(IdZanra)
;



ALTER TABLE FILMSKA_EKIPA
	ADD FOREIGN KEY R_18 (IdFilma) REFERENCES FILM(IdFilma)
;



ALTER TABLE GLUMAC
	ADD FOREIGN KEY R_61 (IdFilma) REFERENCES FILMSKA_EKIPA(IdFilma)
;



ALTER TABLE KARTA
	ADD FOREIGN KEY K_S (IdSjedista) REFERENCES SJEDISTE(IdSjedista)
;


ALTER TABLE KARTA
	ADD FOREIGN KEY ZA (IdProjekcije,DatumProjekcije,IdSale,IdFilma,IdBioskopa) REFERENCES PROJEKCIJA(IdProjekcije,DatumProjekcije,IdSale,IdFilma,IdBioskopa)
;


ALTER TABLE KARTA
	ADD FOREIGN KEY R_65 (JMBG,IdBioskopa) REFERENCES PRODAVAC_KASA(JMBG,IdBioskopa)
;



ALTER TABLE KINOOPERATER
	ADD FOREIGN KEY (JMBG,IdBioskopa) REFERENCES ZAPOSLENI(JMBG,IdBioskopa)
		ON DELETE CASCADE
;



ALTER TABLE NALOG
	ADD FOREIGN KEY KREIRA (AdministratorJMBG,IdBioskopa) REFERENCES ADMINISTRATOR(JMBG,IdBioskopa)
;


ALTER TABLE NALOG
	ADD FOREIGN KEY KORISTI (JMBG,IdBioskopa) REFERENCES ZAPOSLENI(JMBG,IdBioskopa)
;



ALTER TABLE OPREMA
	ADD FOREIGN KEY R_109 (IdOpreme) REFERENCES TIP_OPREME(IdOpreme)
;



ALTER TABLE OPREMA_U_SALI
	ADD FOREIGN KEY S_SO (IdSale,IdBioskopa) REFERENCES SALA(IdSale,IdBioskopa)
;


ALTER TABLE OPREMA_U_SALI
	ADD FOREIGN KEY O_SO (SerijskiBroj) REFERENCES OPREMA(SerijskiBroj)
;



ALTER TABLE PRODAVAC_KASA
	ADD FOREIGN KEY (JMBG,IdBioskopa) REFERENCES ZAPOSLENI(JMBG,IdBioskopa)
		ON DELETE CASCADE
;



ALTER TABLE PRODUCENT
	ADD FOREIGN KEY R_58 (IdFilma) REFERENCES FILMSKA_EKIPA(IdFilma)
;



ALTER TABLE PROJEKCIJA
	ADD FOREIGN KEY SE_PRIKAZUJE (IdSale,IdBioskopa) REFERENCES SALA(IdSale,IdBioskopa)
;


ALTER TABLE PROJEKCIJA
	ADD FOREIGN KEY R_21 (IdFilma) REFERENCES FILM(IdFilma)
;


ALTER TABLE PROJEKCIJA
	ADD FOREIGN KEY VRSI (JMBG,IdBioskopa) REFERENCES KINOOPERATER(JMBG,IdBioskopa)
;



ALTER TABLE RACUN
	ADD FOREIGN KEY IZDAJE (JMBG,IdBioskopa) REFERENCES PRODAVAC_KASA(JMBG,IdBioskopa)
;



ALTER TABLE RACUN_KARTA
	ADD FOREIGN KEY R_110 (IdRacuna,JMBG,IdBioskopa) REFERENCES RACUN(IdRacuna,JMBG,IdBioskopa)
;


ALTER TABLE RACUN_KARTA
	ADD FOREIGN KEY R_112 (IdKarte,IdProjekcije,IdSale,IdFilma,JMBG,DatumProjekcije,IdBioskopa) REFERENCES KARTA(IdKarte,IdProjekcije,IdSale,IdFilma,JMBG,DatumProjekcije,IdBioskopa)
;



ALTER TABLE RACUN_ZAPOSLENOG
	ADD FOREIGN KEY R_86 (JMBG,IdBioskopa) REFERENCES ZAPOSLENI(JMBG,IdBioskopa)
;


ALTER TABLE RACUN_ZAPOSLENOG
	ADD FOREIGN KEY R_89 (IdBanke) REFERENCES BANKA(IdBanke)
;



ALTER TABLE REZERVACIJA
	ADD FOREIGN KEY P_R (IdProjekcije,DatumProjekcije,IdSale,IdFilma,IdBioskopa) REFERENCES PROJEKCIJA(IdProjekcije,DatumProjekcije,IdSale,IdFilma,IdBioskopa)
;


ALTER TABLE REZERVACIJA
	ADD FOREIGN KEY R_12 (IdSjedista) REFERENCES SJEDISTE(IdSjedista)
;



ALTER TABLE SALA
	ADD FOREIGN KEY SADRZI (IdBioskopa) REFERENCES BIOSKOP(IdBioskopa)
;



ALTER TABLE SJEDISTE
	ADD FOREIGN KEY PRIPADA (IdSale,IdBioskopa) REFERENCES SALA(IdSale,IdBioskopa)
;



ALTER TABLE STAVKA
	ADD FOREIGN KEY OBUHVATA (IdRacuna,JMBG,IdBioskopa) REFERENCES RACUN(IdRacuna,JMBG,IdBioskopa)
;


ALTER TABLE STAVKA
	ADD FOREIGN KEY A_S (BarKod) REFERENCES ARTIKAL(BarKod)
;



ALTER TABLE TELEFON
	ADD FOREIGN KEY R_11 (IdBioskopa) REFERENCES BIOSKOP(IdBioskopa)
;



ALTER TABLE UGOVOR_O_RADU
	ADD FOREIGN KEY R_85 (JMBG,IdBioskopa) REFERENCES ZAPOSLENI(JMBG,IdBioskopa)
;



ALTER TABLE UPLATA_PLATE
	ADD FOREIGN KEY R_87 (IdUgovora,JMBG) REFERENCES UGOVOR_O_RADU(IdUgovora,JMBG)
;


ALTER TABLE UPLATA_PLATE
	ADD FOREIGN KEY R_88 (BrojRacuna) REFERENCES RACUN_ZAPOSLENOG(BrojRacuna)
;



ALTER TABLE ZAPOSLENI
	ADD FOREIGN KEY R_23 (IdBioskopa) REFERENCES BIOSKOP(IdBioskopa)
;


