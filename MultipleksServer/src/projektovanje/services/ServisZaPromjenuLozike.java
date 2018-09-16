package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.DBDAONalog;
import projektovanje.dto.DTONalog;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class ServisZaPromjenuLozike {
    public void promjeniLozinku(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        String[] hlpNizStringova = msg.split("#");
        if(3 != hlpNizStringova.length){
            out.writeObject(new String("NOK Pogresan broj argumenata u protokolu. Provjeri dokumentaciju protokola."));
            return;
        }
        if(!nalogTrenutnogKorisnika.getLozinkaHash().equals(hlpNizStringova[1])){
            out.writeObject(new String("NOK Pogresna lozinka."));
            return;
        }
        nalogTrenutnogKorisnika.setLozinkaHash(hlpNizStringova[2]);
        new DBDAONalog().azurirajBazu(new DTONalog(nalogTrenutnogKorisnika),konekcijaNaBazu);
        out.writeObject(new String("OK Uspjesna promjena lozinke."));
    }
}
