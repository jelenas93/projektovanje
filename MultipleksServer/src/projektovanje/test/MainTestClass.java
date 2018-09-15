package projektovanje.test;

import projektovanje.bin.film.Zanr;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.racun.Stavka;
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
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");

        DBDAOStavka faktDao = new DBDAOStavka();
        Stavka novaStavka = new Stavka(1,5,32.5,new Artikal(1));
        List<DTOStavka> ispisi =(ArrayList<DTOStavka>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getStavka()));

        System.out.println("Dodavanje...");
        faktDao.upisiUBazu(new DTOStavka(novaStavka),c);
        System.out.println("Dodavanje uspjesno");


        ispisi =(ArrayList<DTOStavka>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getStavka()));
        System.out.println("Pokusaj updatea");
        novaStavka.setKolicina(6);
        novaStavka.setIdStavke(1);
        faktDao.azurirajBazu(new DTOStavka(novaStavka),c);

        ispisi = (ArrayList<DTOStavka>)faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getStavka()));
        System.out.println("Everything done");
        c.close();
    }
}
