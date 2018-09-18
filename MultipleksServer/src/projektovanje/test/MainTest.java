package projektovanje.test;

import projektovanje.bin.film.Film;
import projektovanje.bin.film.Ponuda;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args)throws Exception{
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");
        Zaposleni z = new Zaposleni(1);
        DBDAOFilm dao = new DBDAOFilm();
        DBDAOArtikal artDao = new DBDAOArtikal();
        DBDAOOprema opremaDao = new DBDAOOprema();
       /* Film f = new Film(1,z,"Film1",45,"mlogo dobar film","www.link.ba","3D");*/
        System.out.println("pokusavama procitati sve:");
       /* ArrayList<DTOFilm> stavkaIzBaze = (ArrayList<DTOFilm>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getFilm()));*/
       List<DTOOprema> dtoOprema = (List<DTOOprema>)opremaDao.ispisi(c);
        dtoOprema.forEach(x-> System.out.println(x.getOprema()));
        System.out.println("Pokusavam dodati u bazu");
        Oprema o = new Oprema(1,2,"Testna oprema",true,z);
        opremaDao.upisiUBazu(new DTOOprema(o),c);
        /*
        dao.upisiUBazu(new DTOFilm(f),c);*/
        System.out.println("Dodao");
        System.out.println("pokusavam opet procitati sve:");

        dtoOprema = (List<DTOOprema>)opremaDao.ispisi(c);
        dtoOprema.forEach(x-> System.out.println(x.getOprema()));
        /*stavkaIzBaze = (ArrayList<DTOFilm>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getFilm()));*/


        System.out.println("Pokusavam update");
        o.setNaziv("BLABLABLA");
        opremaDao.azurirajBazu(new DTOOprema(o),c);

        System.out.println("Update gotov opet citam");

        /*stavkaIzBaze = (ArrayList<DTOFilm>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getFilm()));*/
        dtoOprema = (List<DTOOprema>)opremaDao.ispisi(c);
        dtoOprema.forEach(x-> System.out.println(x.getOprema()));

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
        System.out.println("all done");
    }
}
