package projektovanje.test;

import projektovanje.bin.film.Zanr;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.racun.Stavka;
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
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");

        DBDAOSjediste faktDao = new DBDAOSjediste();
        Sjediste novaSjediste = new Sjediste(1,new Sala(3),1,2);
        List<DTOSjediste> ispisi =(ArrayList<DTOSjediste>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getSjediste()));

        System.out.println("Dodavanje...");
        faktDao.upisiUBazu(new DTOSjediste(novaSjediste),c);
        System.out.println("Dodavanje uspjesno");


        ispisi =(ArrayList<DTOSjediste>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getSjediste()));
        System.out.println("Pokusaj updatea");
        novaSjediste.setVrsta(6);
        novaSjediste.setIdSjedista(4);
        faktDao.azurirajBazu(new DTOSjediste(novaSjediste),c);

        ispisi = (ArrayList<DTOSjediste>)faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getSjediste()));
        System.out.println("almost Everything done");

        ispisi = (ArrayList<DTOSjediste>)faktDao.pretraziSjedistaZaSalu(2,c);
        ispisi.stream().forEach(x-> System.out.println(x.getSjediste()));
        System.out.println("Everything done");

        c.close();
    }
}
