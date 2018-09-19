package projektovanje.services;

import projektovanje.bin.film.Zanr;
import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.DBDAOFillmZanr;
import projektovanje.dbDAO.DBDAOFilm;
import projektovanje.dbDAO.DBDAOZanr;
import projektovanje.dbDAO.DBDAOZaposleni;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOZanr;
import projektovanje.dto.DTOZaposleni;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ServisZaFilmove {
    static private Logovanje logServisaZaFilmove;
    static
    {
        logServisaZaFilmove = new Logovanje(new ServisZaFilmove());
    }
    public static void dodajFilm(ObjectOutputStream out, ObjectInputStream in, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOFilm dtoFilm = (DTOFilm)in.readObject();
        DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu,nalogTrenutnogKorisnika.getKorisnickiNalog());
        dtoFilm.getFilm().getZaposleni().setIdZaposlenog(dtoZaposleni.getZaposleni().getIdZaposlenog());
        new DBDAOFilm().upisiUBazu(dtoFilm, konekcijaNaBazu);
        int posljedniID = new DBDAOFilm().zadnjiUmetnutiId(konekcijaNaBazu);
        for(Zanr zanr : dtoFilm.getFilm().getZanrovi()){
            DTOZanr dtoZanrIzBaze = (DTOZanr) new DBDAOZanr().pretraziZanrPoImenu(konekcijaNaBazu, zanr.getNazivZanra());
            new DBDAOFillmZanr().upisiUBazu(posljedniID,dtoZanrIzBaze.getZanr().getIdZanra(),konekcijaNaBazu);
        }
        logServisaZaFilmove.logujDogadjaj(Level.FINEST, new ServisZaFilmove(), "Uspjesno dodan film." + dtoFilm.getFilm().getNaziv() + "\nMenadzer: " +
                dtoZaposleni.getZaposleni().getNalog().getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodat film."));
    }

    public static void azurirajFilm(ObjectOutputStream out, ObjectInputStream in, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOFilm dtoFilm = (DTOFilm) in.readObject();
        DTOZaposleni dtoZaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu, nalogTrenutnogKorisnika.getKorisnickiNalog());
        dtoFilm.getFilm().getZaposleni().setIdZaposlenog(dtoZaposleni.getZaposleni().getIdZaposlenog());
        new DBDAOFilm().azurirajBazu(dtoFilm, konekcijaNaBazu);
        new DBDAOFillmZanr().ukloniSveZandroveVezaneZaFilm(konekcijaNaBazu, dtoFilm.getFilm().getIdFilma());
        for (Zanr zanr : dtoFilm.getFilm().getZanrovi()) {
            DTOZanr dtoZanrIzBaze = (DTOZanr) new DBDAOZanr().pretraziZanrPoImenu(konekcijaNaBazu, zanr.getNazivZanra());
            new DBDAOFillmZanr().upisiUBazu(dtoFilm.getFilm().getIdFilma(), dtoZanrIzBaze.getZanr().getIdZanra(), konekcijaNaBazu);
        }
        out.writeObject(new String("OK#Uspjesno izmjenjen film."));
        logServisaZaFilmove.logujDogadjaj(Level.FINEST, new ServisZaFilmove(), "Uspjesno izmjenjen film." + dtoFilm.getFilm().getNaziv() + "\nMenadzer: " +
                dtoZaposleni.getZaposleni().getNalog().getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno izmjenjen film."));

    }

    public static void ispisiFilmove(ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws SQLException, IOException {
        List<DTOFilm> listaFilmova = new ArrayList();
        listaFilmova = (List<DTOFilm>)new DBDAOFilm().ispisi(konekcijaNaBazu);
        logServisaZaFilmove.logujDogadjaj(Level.FINEST, new ServisZaFilmove(), "Filmovi vraceni na zahtjev korisnika sa korisnickim imenom :" + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(listaFilmova);
    }
}
