package projektovanje.test;

import projektovanje.bin.film.Zanr;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
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

        DBDAOUlaznaFaktura faktDao = new DBDAOUlaznaFaktura();
        UlaznaFaktura novaFaktura = new UlaznaFaktura(1,new Zaposleni(2),"abc","kes","kol",5.0,3.0,"Cijeli",new Date(),null);
        List<DTOUlaznaFaktura> ispisi =(ArrayList<DTOUlaznaFaktura>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getUlaznaFaktura()));

        System.out.println("Dodavanje...");
        faktDao.upisiUBazu(new DTOUlaznaFaktura(novaFaktura),c);
        System.out.println("Dodavanje uspjesno");


        ispisi =(ArrayList<DTOUlaznaFaktura>) faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getUlaznaFaktura()));
        System.out.println("Pokusaj updatea");
        novaFaktura.setJedinicaMjere("BLAZANR");
        novaFaktura.setIdFakute(2);
        faktDao.azurirajBazu(new DTOUlaznaFaktura(novaFaktura),c);

        ispisi = (ArrayList<DTOUlaznaFaktura>)faktDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getUlaznaFaktura()));
        System.out.println("Everything done");
        c.close();
    }
}
