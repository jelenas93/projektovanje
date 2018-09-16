package projektovanje.test;

import projektovanje.bin.film.Film;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.repertoar.Repertoar;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.transakcije.UlaznaFaktura;
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

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/multipleks", "root", "admin");

        DBDAORepertoar faktDao = new DBDAORepertoar();
        Zaposleni z = new Zaposleni(1);
        Date datumOd = new Date (System.currentTimeMillis() - 1000000000);
        Date datumDo = new Date(System.currentTimeMillis() + 1000000000);
        Repertoar novaRepertoar = new Repertoar(1,null,z,datumOd,datumDo);
        List<DTORepertoar> ispisi =(ArrayList<DTORepertoar>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getRepertoar()));


        System.out.println("Dodavanje...");
        faktDao.upisiUBazu(new DTORepertoar(novaRepertoar),c);
        System.out.println("Dodavanje uspjesno");


        ispisi =(ArrayList<DTORepertoar>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getRepertoar()));
        System.out.println("Pokusaj updatea");
        Date noviDatumOd = new Date(System.currentTimeMillis()-100000);
        novaRepertoar.setDatumOd(noviDatumOd);
        novaRepertoar.setIdRepertoara(1);
        faktDao.azurirajBazu(new DTORepertoar(novaRepertoar),c);

        ispisi = (ArrayList<DTORepertoar>)faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getRepertoar()));
        System.out.println("almost Everything done");

        DTORepertoar trenutniRepertoar = faktDao.dohvatiTrenutniRepertoar(c);
        System.out.println(trenutniRepertoar.getRepertoar());


        c.close();

    }
}
