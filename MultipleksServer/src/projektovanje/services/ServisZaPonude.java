package projektovanje.services;

import projektovanje.bin.film.Film;
import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.DBDAOFilm;
import projektovanje.dbDAO.DBDAOFilmPonuda;
import projektovanje.dbDAO.DBDAOPonuda;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOPonuda;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ServisZaPonude {
    private static Logovanje logServisaZaPonude;
    static
    {
        logServisaZaPonude = new Logovanje(new ServisZaPonude());
    }
    public static void dodajPonudeNaFilmove(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICONE"));
        DTOPonuda dtoPonuda = (DTOPonuda)in.readObject();
        DBDAOFilm dbdaoFilm = new DBDAOFilm();
        DBDAOPonuda dbdaoPonuda = new DBDAOPonuda();
        DBDAOFilmPonuda dbdaoFilmPonuda = new DBDAOFilmPonuda();
        dbdaoPonuda.upisiUBazu(dtoPonuda,konekcijaNaBazu);
        int poslednjaPonudaId = dbdaoPonuda.zadnjiUmetnutiId(konekcijaNaBazu);
        for(Film filmIzPonude : dtoPonuda.getPonuda().getFilm()){
            dbdaoFilm.upisiUBazu(new DTOFilm(filmIzPonude),konekcijaNaBazu);
            int posledjiFilmId = dbdaoFilm.zadnjiUmetnutiId(konekcijaNaBazu);
            dbdaoFilmPonuda.upisiUBazu(posledjiFilmId,poslednjaPonudaId,konekcijaNaBazu);
        }
        logServisaZaPonude.logujDogadjaj(Level.FINEST, new ServisZaPonude(), "Menadzer uspjesno dodao nove ponude na filmove. " +
                "\nMenadzer: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodavanje ponude."));
    }


    public static void izlistajPonude(ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        List<DTOPonuda> listaPonuda = new ArrayList<>();
        DBDAOPonuda dbdaoPonuda = new DBDAOPonuda();
        DBDAOFilmPonuda dbdaoFilmPonuda = new DBDAOFilmPonuda();
        listaPonuda = dbdaoPonuda.ispisi(konekcijaNaBazu);
        for(DTOPonuda dtoPonuda : listaPonuda){
            List<DTOFilm> listaFilmovaNaPonudi = dbdaoFilmPonuda.pretraziSveFilmoveZaPonuda(dtoPonuda.getPonuda().getIdPonude(),konekcijaNaBazu);
            List<Film> listaOtpakovanihFilmovaNaPonudi = new ArrayList<>();
            listaFilmovaNaPonudi.stream().parallel().forEach(x->listaOtpakovanihFilmovaNaPonudi.add(x.getFilm()));
            dtoPonuda.getPonuda().setFilm(listaOtpakovanihFilmovaNaPonudi);
        }
        logServisaZaPonude.logujDogadjaj(Level.FINEST, new ServisZaPonude(), "Menadzer uspjesno izlistao nove ponude na filmove. " +
                "\nMenadzer: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(listaPonuda);
    }
}
