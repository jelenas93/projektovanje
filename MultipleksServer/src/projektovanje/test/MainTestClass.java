package projektovanje.test;

import projektovanje.bin.film.Film;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Administrator;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainTestClass {
    public static void main(String[] args)throws Exception{
       /* List<DTOArtikal> lista = new ArrayList<>();
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setIdZaposlenog(2);
        Artikal artikal = new Artikal(3,"voce",7,299.9,"hrana","1100111", zaposleni);
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "marko123");
        DBDAOArtikal dbdaoArtikal = new DBDAOArtikal();
        DTOArtikal dtoArtikal = new DTOArtikal(artikal);
        dbdaoArtikal.upisiUBazu(dtoArtikal, c);
        dbdaoArtikal.azurirajBazu(dtoArtikal, c);
        lista = dbdaoArtikal.citajIzBaze(c);
        Iterator<DTOArtikal> iterator = lista.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getArtikal().getNaziv());
        }*/
        /*System.out.println("Done1");
        Plata plata = new Plata(1,1.5,2.5,3.5,4.5,5.5,6.5,7.5,8.5,9.5,10.5,11.5,12.5,13.5,new Date(),new Date());
        Nalog test = new Nalog("T4","T4");
       Zaposleni coa = new Zaposleni(0,null,"Boss5","Fukara4","4609554160006",test);
       DTOZaposleni coadto = new DTOZaposleni(coa);
        DBDAOZaposleni dbz = new DBDAOZaposleni();
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "marko123");
        dbz.upisiUBazu(coadto,c);
        System.out.println("Writin to db done");

        ArrayList<DTOZaposleni> sviZaposleni = (ArrayList<DTOZaposleni>) dbz.citajIzBaze(c);
        sviZaposleni.stream().forEach(x-> System.out.println(x.getZaposleni()));
        System.out.println("Reading done, trying to update");
        coadto.getZaposleni().setIme("aeiou");
        System.out.println("novo ime: "+coadto.getZaposleni().getIme());
        coadto.getZaposleni().setPlata(plata);
        dbz.azurirajBazu(coadto,c);
        System.out.println("updating done");

        sviZaposleni = (ArrayList<DTOZaposleni>) dbz.citajIzBaze(c);
        sviZaposleni.stream().forEach(x-> System.out.println(x.getZaposleni()));
        System.out.println("Everything done");
        c.close();*/


        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "marko123");
        //
//DBDAOAdministrator admin = new DBDAOAdministrator();
//Administrator varA = new Administrator();
//varA.setIdAdministratora(3);
////admin.upisiUBazu(new DTOAdministrator(varA), c);
//List<DTOAdministrator> listaAdmina = new ArrayList<>();
//listaAdmina = admin.citajIzBaze(c);
//listaAdmina.stream().parallel().forEach(x -> System.out.println(x.getAdministrator().getIdAdministratora()));
//DTOAdministrator odabraniAdmin = (DTOAdministrator) admin.pretraziBazu(c, "2");
//System.out.println("Odabrani administrator je 2(" + odabraniAdmin.getAdministrator().getIdAdministratora() + ")");
//Nalog test = new Nalog("Coa","Coa");
//
//Zaposleni coa = new Zaposleni(9,Plata.nulaPlata,"Boss5","Fukara4","4609554160006",test);
//DTOZaposleni coadto = new DTOZaposleni(coa);
//DBDAOZaposleni dbz = new DBDAOZaposleni();
////dbz.upisiUBazu(coadto, c);
//Artikal artikal = new Artikal(1, "balon cokolada", 50_000, 50.0,  "sreca", "IIIlllI11L llllllIII", coa);
//DBDAOArtikal artikalDAO = new DBDAOArtikal();
//artikalDAO.upisiUBazu(new DTOArtikal(artikal), c);
//artikal.setNaziv("OVDJE JE IZMJENA");
//artikal.setIdArtikla(2);
//artikalDAO.azurirajBazu(new DTOArtikal(artikal), c);
//List<DTOArtikal> listaArtikala = artikalDAO.ispisi(c);
//listaArtikala.stream().parallel().forEach(x->System.out.println(x.getArtikal()));
//
/*
        Film film = new Film(1,coa,"Terminator 2", 2500, "Jedno od najboljih osvarenja filmeske umjetnosti!!!","https:\\www.terminator2.ter","2D");
        DBDAOFilm dbdaoFilm = new DBDAOFilm();
        dbdaoFilm.upisiUBazu(new DTOFilm(film),c);
        Film film2 = new Film(3,coa,"Terminator3", 5000, "PREMASIO PRETHODNIKA!!!","https:\\www.terminator3.ter","3D");
        dbdaoFilm.upisiUBazu(new DTOFilm(film2),c);
        List<DTOFilm> listaFilmova = dbdaoFilm.ispisi(c);
        System.out.println("ISPIS PRIJE AZURIRANJA:\n");
        listaFilmova.stream().parallel().forEach(x->System.out.println(x.getFilm()));
        System.out.println("ISPIS NAKON AZURIRANJA:\n");
        film2.setNaziv("TERMINATOR NAJACI");
        dbdaoFilm.azurirajBazu(new DTOFilm(film2),c);

        listaFilmova = dbdaoFilm.ispisi(c);
        listaFilmova.stream().parallel().forEach(x->System.out.println(x.getFilm()));
        */
/*
        Karta karta = new Karta(1, new Date(System.currentTimeMillis()),12.2, true, new Nalog("Coa", "Coa"),null);
        DBDAOKarta kartaDAO = new DBDAOKarta();
        //kartaDAO.upisiUBazu(new DTOKarta(karta), c);
        Karta karta2 = new  Karta(2, new Date(System.currentTimeMillis()),12.2, false, null,null);
        //kartaDAO.upisiUBazu(new DTOKarta(karta2), c);
        List<DTOKarta> listaKarti = kartaDAO.ispisi(c);
        listaKarti.stream().parallel().forEach(x->System.out.println(x.getKarta()));

        karta2.setIdKarte(8);
        karta2.setRezervisana(true);
        karta2.setNalog(new Nalog("Coa", "Coa"));
        kartaDAO.azurirajBazu(new DTOKarta(karta2), c);
        listaKarti = kartaDAO.ispisi(c);
        listaKarti.stream().parallel().forEach(x->System.out.println(x.getKarta()));
*/
        DBDAOAdministrator adminDAO = new DBDAOAdministrator();
        List<DTOAdministrator> listaAdministratora;
        listaAdministratora = adminDAO.citajIzBaze(c);
        listaAdministratora.stream().parallel().forEach(x->System.out.println(x));

        c.close();

    }
}
