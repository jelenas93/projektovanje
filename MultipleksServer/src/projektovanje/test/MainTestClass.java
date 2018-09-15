package projektovanje.test;

import projektovanje.bin.film.Film;
import projektovanje.bin.karta.Karta;
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

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/multipleks", "root", "admin");

        DBDAOSala faktDao = new DBDAOSala();
        Sala novaSala = new Sala(1,5,6,null);
        List<DTOSala> ispisi =(ArrayList<DTOSala>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getSala()));


        System.out.println("Dodavanje...");
        faktDao.upisiUBazu(new DTOSala(novaSala),c);
        System.out.println("Dodavanje uspjesno");


        ispisi =(ArrayList<DTOSala>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getSala()));
        System.out.println("Pokusaj updatea");
        novaSala.setBrojKolona(12);;
        novaSala.setIdSale(1);
        faktDao.azurirajBazu(new DTOSala(novaSala),c);

        ispisi = (ArrayList<DTOSala>)faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getSala()));
        System.out.println("almost Everything done");

        c.close();

    }
}
