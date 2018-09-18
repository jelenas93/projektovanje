package projektovanje.test;

import projektovanje.bin.film.Film;
import projektovanje.bin.film.Ponuda;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.transakcije.UlaznaFaktura;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest {

    public static void main(String[] args)throws Exception{
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");
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

        /*Film f = new Film(1,z,"TestFilm2",54,"kulj film","www.trailers.com","2d",zanrovi);
        ArrayList<Film> filmovi = new ArrayList<>();
        filmDao.upisiUBazu(new DTOFilm(f),c);
        filmovi.add(f);*/
        System.out.println("pokusavama procitati sve:");
       /* ArrayList<DTOFilm> stavkaIzBaze = (ArrayList<DTOFilm>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getFilm()));*
       List<DTOOprema> dtoOprema = (List<DTOOprema>)opremaDao.ispisi(c);
        dtoOprema.forEach(x-> System.out.println(x.getOprema()));*/
       List<DTOPonuda> ponude = (List<DTOPonuda>) ponudaDao.ispisi(c);
       ponude.forEach(x-> System.out.println(x.getPonuda()));
        System.out.println("Pokusavam dodati u bazu");
        /*Oprema o = new Oprema(1,2,"Testna oprema",true,z);
        opremaDao.upisiUBazu(new DTOOprema(o),c);
        //Ponuda p = new Ponuda(1,filmovi,new Date(),z);
        //ponudaDao.upisiUBazu(new DTOPonuda(p),c);*/
        System.out.println("Dodao");
        System.out.println("pokusavam opet procitati sve:");

        ponude = (List<DTOPonuda>) ponudaDao.ispisi(c);
        ponude.forEach(x-> System.out.println(x.getPonuda()));

        /*dtoOprema = (List<DTOOprema>)opremaDao.ispisi(c);
        dtoOprema.forEach(x-> System.out.println(x.getOprema()));
        stavkaIzBaze = (ArrayList<DTOFilm>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getFilm()));*/


        System.out.println("Pokusavam update");
       /* p.setZaposleni(new Zaposleni(2));
        ponudaDao.azurirajBazu(new DTOPonuda(p),c);
        /*opremaDao.azurirajBazu(new DTOOprema(o),c);*/

        System.out.println("Update gotov opet citam");

        /*stavkaIzBaze = (ArrayList<DTOFilm>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getFilm()));
        dtoOprema = (List<DTOOprema>)opremaDao.ispisi(c);
        dtoOprema.forEach(x-> System.out.println(x.getOprema()));*/
        ponude = (List<DTOPonuda>) ponudaDao.ispisi(c);
        ponude.forEach(x-> System.out.println(x.getPonuda()));

        /*System.out.println("pokusavam citati artikle");
        List<DTOArtikal> artikliIzBaze = artDao.citajIzBaze(c);
        artikliIzBaze.forEach(x->System.out.println(x.getArtikal()));

        DTOArtikal art = (DTOArtikal) artDao.pretraziBazu(c,"3");
        System.out.println("Procitani artikal");
        System.out.println(art.getArtikal());*/

      /* System.out.println("Citam sve filmove");
       DBDAOFilm filmDAO = new DBDAOFilm();
        List<DTOFilm> dtoFilms = filmDAO.citajIzBaze(c);
        dtoFilms.forEach(x-> System.out.println(x.getFilm()));*/

      System.out.println("Citam sva izdavanja");
       /* DBDAOIzdavanje izdDAO = new DBDAOIzdavanje();
        List<DTOIzdavanje> dtoFilms = izdDAO.citajIzBaze(c);
        dtoFilms.forEach(x->System.out.println(x.getIzdavanje()));*/
       /* System.out.println("all done");

        List<DTORepertoar> dtoRepertoars = repDao.citajIzBaze(c);
        dtoRepertoars.forEach(x->System.out.println(x.getRepertoar()));
        System.out.println("It's now done");

        List<DTOStavka> dtoStavke = (List<DTOStavka>) new DBDAOStavka().ispisi(c);
        dtoStavke.forEach(x->System.out.println(x.getStavka()));
       DBDAOUlaznaFaktura uf = new DBDAOUlaznaFaktura();
       List<DTOUlaznaFaktura> l = (List<DTOUlaznaFaktura>) uf.citajIzBaze(c);
       l.forEach(x->System.out.println(x.getUlaznaFaktura()));*/

       Artikal art = new Artikal(1,"test art 1",15,44.5,"t1","001",z);
       Oprema o = new Oprema(2,1,"T oprema",true,z);
       List<IOprema> lst = new ArrayList<>();
       lst.add(art);
       lst.add(o);
       System.out.println("Dodavanje nove fakture");
       UlaznaFaktura uf = new UlaznaFaktura(2,z,"4","kes","kg",5.0,6.0,"Test kupac",new Date(),lst);
       DBDAOUlaznaFaktura ufDao = new DBDAOUlaznaFaktura();
       ufDao.upisiUBazu(new DTOUlaznaFaktura(uf),c);
       System.out.println("ispisao");

    }
}
