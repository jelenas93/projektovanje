package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.DBDAOProjekcija;
import projektovanje.dbDAO.DBDAOZaposleni;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOProjekcija;
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

public class ServisZaProjekcije {
    private static Logovanje logServisaZaProjekcije;
    static
    {
        logServisaZaProjekcije = new Logovanje(new ServisZaProjekcije());
    }


    public static void izlistajProjekcije(ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws SQLException, IOException {
        List<DTOProjekcija> listaProjekicija = new ArrayList();
        listaProjekicija = (List<DTOProjekcija>)new DBDAOProjekcija().ispisi(konekcijaNaBazu);
        logServisaZaProjekcije.logujDogadjaj(Level.FINEST, new ServisZaFilmove(), "Projekcije vracene na zahtjev menadzera sa korisnickim imenom :" + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(listaProjekicija);
    }

    public static void dodajProjekciju(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws SQLException, IOException, ClassNotFoundException {
        out.writeObject(new String("WHICHONE"));
        DTOProjekcija dtoProjekcija = (DTOProjekcija)in.readObject();
        DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu,nalogTrenutnogKorisnika.getKorisnickiNalog());
        dtoProjekcija.getProjekcija().getZaposleni().setIdZaposlenog(dtoZaposleni.getZaposleni().getIdZaposlenog());
        new DBDAOProjekcija().upisiUBazu(dtoProjekcija, konekcijaNaBazu);
        logServisaZaProjekcije.logujDogadjaj(Level.FINEST, new ServisZaFilmove(), "Uspjesno dodana projekcija.\nMenadzer: " +
                dtoZaposleni.getZaposleni().getNalog().getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodana projekcija."));
    }


    public static void azurirajProjekciju(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOProjekcija dtoProjekcija = (DTOProjekcija)in.readObject();
        DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu,nalogTrenutnogKorisnika.getKorisnickiNalog());
        dtoProjekcija.getProjekcija().setZaposleni(dtoZaposleni.getZaposleni());
        new DBDAOProjekcija().azurirajBazu(dtoProjekcija,konekcijaNaBazu);
        logServisaZaProjekcije.logujDogadjaj(Level.FINEST, new ServisZaFilmove(), "Uspjesno izmjenjena projekcija.\nMenadzer: " +
                dtoZaposleni.getZaposleni().getNalog().getKorisnickiNalog() + "idProjekcija: " + dtoProjekcija.getProjekcija().getIdProjekcije());
        out.writeObject(new String("OK#Uspjesno azurirana projekcija."));
    }
}
