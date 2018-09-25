package projektovanje.services;

import projektovanje.bin.projekcija.Projekcija;
import projektovanje.dbDAO.DBDAOProjekcija;
import projektovanje.dbDAO.DBDAORepertoar;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;
import projektovanje.net.ServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ServisZaRepertoar {

    public static void dodavanjeRepertoara(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("WHICHONE");
        out.writeObject(odgovor);
        DTORepertoar repertoar = (DTORepertoar) in.readObject();
        DBDAOProjekcija projekcijaDao = new DBDAOProjekcija();
        new DBDAORepertoar().upisiUBazu(repertoar,konekcijaNaBazu);
        int idRepertoara = projekcijaDao.zadnjiUmetnutiId(konekcijaNaBazu);
        List<Projekcija> projekcije = repertoar.getRepertoar().getProjekcija();
        for (Projekcija projekcija : projekcije) {
            projekcija.setIdRepertoara(idRepertoara);
            projekcijaDao.upisiUBazu(new DTOProjekcija(projekcija),konekcijaNaBazu);
        }
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }

    public static void dodavanjeFilmaNaRepetoar(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("GIVE_ME_DATA");
        out.writeObject(odgovor);
        DTOProjekcija projekcija = (DTOProjekcija)in.readObject();
        odgovor = new String("GIVE_ME_REPERTOIRE");
        out.writeObject(odgovor);
        DTORepertoar repertoar = (DTORepertoar) in.readObject();
        new DBDAORepertoar().azurirajBazu(repertoar,konekcijaNaBazu);
        new DBDAOProjekcija().upisiUBazu(projekcija,konekcijaNaBazu);
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }

    public static void pregledSvihRepertoara(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws SQLException, IOException {
        List<DTORepertoar> repertoari = new DBDAORepertoar().ispisi(konekcijaNaBazu);
        out.writeObject(repertoari);
    }

    public static void pregledTrenutnogRepertoara(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws SQLException, IOException {
        DTORepertoar repertoar = new DBDAORepertoar().dohvatiTrenutniRepertoar(konekcijaNaBazu);
        out.writeObject(repertoar);
    }
}
