package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.DBDAONalog;
import projektovanje.dto.DTONalog;
import projektovanje.ostalo.Logovanje;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class ServisZaPromjenuLozike {
    private static Logovanje logServisaPromjene;
    static
    {
        logServisaPromjene = new Logovanje(new ServisZaPromjenuLozike());
    }
    public static void promjeniLozinku(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        String[] hlpNizStringova = msg.split("#");
        if(3 != hlpNizStringova.length){
            logServisaPromjene.logujDogadjaj(Level.WARNING, new ServisZaPromjenuLozike(), "Greska u protokolu, primljena poruka = " + msg);
            out.writeObject(new String("NOK Pogresan broj argumenata u protokolu. Provjeri dokumentaciju protokola."));
            return;
        }
        if(!nalogTrenutnogKorisnika.getLozinkaHash().equals(hlpNizStringova[1])){
            logServisaPromjene.logujDogadjaj(Level.FINE, new ServisZaPromjenuLozike(), "Pogresna stara lozinka pri pokusaju promjene iste.");
            out.writeObject(new String("NOK Pogresna lozinka."));
            return;
        }
        nalogTrenutnogKorisnika.setLozinkaHash(hlpNizStringova[2]);
        new DBDAONalog().azurirajBazu(new DTONalog(nalogTrenutnogKorisnika),konekcijaNaBazu);
        logServisaPromjene.logujDogadjaj(Level.FINE, new ServisZaPromjenuLozike(), "Korisnik promjenio lozinku.");
        out.writeObject(new String("OK Uspjesna promjena lozinke."));
    }
}
