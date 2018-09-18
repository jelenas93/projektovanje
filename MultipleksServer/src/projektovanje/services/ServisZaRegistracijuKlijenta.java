package projektovanje.services;

import projektovanje.bin.klijent.Klijent;
import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.DBDAOKlijent;
import projektovanje.dbDAO.DBDAONalog;
import projektovanje.dto.DTOKlijent;
import projektovanje.dto.DTONalog;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class ServisZaRegistracijuKlijenta {
    private static Logovanje logServisaZaRegistraciju;
    static
    {
        logServisaZaRegistraciju = new Logovanje(new ServisZaRegistracijuKlijenta());
    }
    public static void obaviRegistraciju(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws SQLException, IOException {
        if(!provjeriNalog(msg,konekcijaNaBazu,out)){
            return;
        }
        registruj(msg,konekcijaNaBazu,out);
        logServisaZaRegistraciju.logujDogadjaj(Level.FINEST, new ServisZaRegistracijuKlijenta(), "Novi korisnik uspjesno registrovan.");
        out.writeObject(new String("OK#Uspjesna registracija klijenta."));
    }

    private static Boolean provjeriNalog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws SQLException, IOException {
        String[] hlpNizStringova = msg.split("#");
        if(6!=hlpNizStringova.length){
            logServisaZaRegistraciju.logujDogadjaj(Level.WARNING, new ServisZaRegistracijuKlijenta(), "Greska u protokolu, primljena poruka = " + msg);
            out.writeObject(new String("NOK#Pogresan broj argumenata u protokolu. Provjeri dokumentaciju protokola."));
            return false;
        }
        DTONalog dtoNalog = (DTONalog)new DBDAONalog().pretraziBazu(konekcijaNaBazu,hlpNizStringova[3]);
        if(null == dtoNalog){
            return true;
        }
        logServisaZaRegistraciju.logujDogadjaj(Level.FINE, new ServisZaRegistracijuKlijenta(), "Pokusaj dupliranja korisnickog naloga.");
        out.writeObject(new String("NOK#Korisnicko ime vec postoji."));
        return false;
    }

    private static void registruj(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws SQLException, IOException {
        String[] hlpNizStringova = msg.split("#");
        Nalog nalog = new Nalog(hlpNizStringova[3],hlpNizStringova[4]);
        new DBDAONalog().upisiUBazu(new DTONalog(nalog),konekcijaNaBazu);
        new DBDAOKlijent().upisiUBazu(new DTOKlijent(new Klijent(1, nalog, hlpNizStringova[1], hlpNizStringova[2],hlpNizStringova[5])), konekcijaNaBazu);
    }
}
