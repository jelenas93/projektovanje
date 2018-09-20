package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.ostalo.Logovanje;
import sun.util.logging.PlatformLogger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ServisZaRacunovodju {
    private static Logovanje logServisaZaRacunovodju;
    static
    {
        logServisaZaRacunovodju = new Logovanje(new ServisZaRacunovodju());
    }
    public static void prikazListeZaposlenih(Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        List<DTOZaposleni> listaAktivnihZaposlenih = (List<DTOZaposleni>) new DBDAOZaposleni().procitajSveAktivneZaposlene(konekcijaNaBazu);
        listaAktivnihZaposlenih.stream().parallel().forEach(x-> x.getZaposleni().setNalog(null));
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja trazio i dobio listu zaposlenih sa platama.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(listaAktivnihZaposlenih);
        out.flush();
    }

    public static void azuriranjeZaposlenog(Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException, ClassNotFoundException {
        //out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        new DBDAOPlata().azurirajBazu(new DTOPlata(dtoZaposleni.getZaposleni().getPlata()), konekcijaNaBazu);
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja azurirao platu zaposlenom.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesna izmjena."));
    }


    public static void dodajPlatuZaposlenom(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        //out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        DBDAOPlata dbdaoPlata = new DBDAOPlata();
        dbdaoPlata.upisiUBazu(new DTOPlata(dtoZaposleni.getZaposleni().getPlata()), konekcijaNaBazu);
        int poslednjiPlataID = dbdaoPlata.zadnjiUmetnutiId(konekcijaNaBazu);
        new DBDAOZaposleni().promjeniPlatu(dtoZaposleni.getZaposleni().getIdZaposlenog(),poslednjiPlataID,konekcijaNaBazu);
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja dodao platu zaposlenom.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodana plata."));
    }
}
