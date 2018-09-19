package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ServisZaRacunovodju {
    private static Logovanje logServisaZaRacunovodju;
    static
    {
        logServisaZaRacunovodju = new Logovanje(new ServisZaRacunovodju());
    }
    public static void prikazListeZaposlenih(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws IOException, SQLException {
        List<DTOZaposleni> listaAktivnihZaposlenih = (List<DTOZaposleni>) new DBDAOZaposleni().procitajSveAktivneZaposlene(konekcijaNaBazu);
        listaAktivnihZaposlenih.stream().parallel().forEach(x-> x.getZaposleni().setNalog(null));
        out.writeObject(listaAktivnihZaposlenih);
        out.flush();
    }

    public static void azuriranjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, SQLException, ClassNotFoundException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        new DBDAOPlata().azurirajBazu(new DTOPlata(dtoZaposleni.getZaposleni().getPlata()), konekcijaNaBazu);
        out.writeObject(new String("OK#Uspjesna izmjena."));
    }
}
