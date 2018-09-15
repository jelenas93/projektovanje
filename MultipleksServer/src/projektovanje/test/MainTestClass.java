package projektovanje.test;

import projektovanje.bin.film.Zanr;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Administrator;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.DBDAOAdministrator;
import projektovanje.dbDAO.DBDAOArtikal;
import projektovanje.dbDAO.DBDAOZanr;
import projektovanje.dbDAO.DBDAOZaposleni;
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

        DBDAOZanr zanrDao = new DBDAOZanr();
        Zanr noviZanr = new Zanr(0,"Tler");
        List<DTOZanr> ispisi =(ArrayList<DTOZanr>) zanrDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getZanr()));

        System.out.println("Dodavanje...");
        zanrDao.upisiUBazu(new DTOZanr(noviZanr),c);
        System.out.println("Dodavanje uspjesno");


        ispisi =(ArrayList<DTOZanr>) zanrDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getZanr()));
        System.out.println("Pokusaj updatea");
        noviZanr.setNazivZanra("BLAZANR");
        noviZanr.setIdZanra(1);
        zanrDao.azurirajBazu(new DTOZanr(noviZanr),c);

        ispisi = (ArrayList<DTOZanr>)zanrDao.ispisi(c);
        ispisi.stream().forEach(x-> System.out.println(x.getZanr()));
        System.out.println("Everything done");
        c.close();
    }
}
