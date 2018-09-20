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
import projektovanje.bin.repertoar.Repertoar;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.transakcije.UlaznaFaktura;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.services.ServisZaProdavcaKarata;
import projektovanje.services.ServisZaRepertoar;

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
        Nalog nalog = new Nalog("Cijeli","Cijeli2");

        DTOZaposleni zaposleniDto = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(c,"1");

        List<DTOFilm> filmovi = (List<DTOFilm>) new DBDAOFilm().citajIzBaze(c);
        List<Projekcija> projekcije = new ArrayList<>();
        filmovi.forEach(x->projekcije.add(new Projekcija(1,x.getFilm(),new Date(),zaposleniDto.getZaposleni(),5)));
        Repertoar repertoar = new Repertoar(5,projekcije,zaposleniDto.getZaposleni(),new Date(),new Date(System.currentTimeMillis()+10000));
        Projekcija p = new Projekcija(1,filmovi.get(2).getFilm(),new Date(),zaposleniDto.getZaposleni(),5);
       // oos.writeObject(new DTOProjekcija(p));
        //oos.writeObject(new DTORepertoar(repertoar));
       // ServisZaRepertoar.dodavanjeRepertoara("msg",c,oos,ois);
      //  ServisZaRepertoar.dodavanjeFilmaNaRepetoar("msg",c,oos,ois);
        //String msg = (String)ois.readObject();
        //System.out.println(msg);
        ServisZaRepertoar.pregledSvihRepertoara("msg",c,oos,ois);
        List<DTORepertoar> repertoari = (List<DTORepertoar>) ois.readObject();
        repertoari.forEach(x-> System.out.println(x.getRepertoar()));
        System.out.println("Repertoar bi trebalo da je dodan");
    }
}
