package projektovanje.test;

import projektovanje.bin.film.Film;
import projektovanje.bin.film.Ponuda;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.izdavanje.Izdavanje;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.transakcije.UlaznaFaktura;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.services.ServisZaProdavcaKarata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest {

    public static void main(String[] args)throws Exception{
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
        Zaposleni z = new Zaposleni(1);
        DBDAOFilm dao = new DBDAOFilm();
        DBDAOArtikal artDao = new DBDAOArtikal();
        DBDAOOprema opremaDao = new DBDAOOprema();
        DBDAOPonuda ponudaDao = new DBDAOPonuda();
        DBDAOFilm filmDao = new DBDAOFilm();
        DBDAORepertoar repDao = new DBDAORepertoar();
        Zanr za = new Zanr(1,"Kulovi");
        List<Zanr> zanrovi = new ArrayList<>();
        zanrovi.add(za);

        List<DTOProjekcija> p = (List<DTOProjekcija>) new DBDAOProjekcija().citajIzBaze(c);
        List<DTOSjediste> sjedista = (List<DTOSjediste>) new DBDAOSjediste().ispisi(c);
        Nalog nalog = new Nalog("Cijeli","Cijeli2");
        Karta k = new Karta(4,new Date(),4.5,true,nalog,p.get(1).getProjekcija());
        Izdavanje i = new Izdavanje(k,sjedista.get(1).getSjediste(),sjedista.get(1).getSjediste().getSala(),p.get(1).getProjekcija(),z);
        oos.writeObject(new DTOIzdavanje(i));
        ServisZaProdavcaKarata.rezervacijaKarte("msg",c,oos,ois);
        System.out.println("Trebalo bi da je rezervisano mjesto");
        String odgovor = (String)ois.readObject();
        System.out.println(odgovor);


    }
}
