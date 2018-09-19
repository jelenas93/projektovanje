package projektovanje.services;

import projektovanje.bin.izdavanje.Izdavanje;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dao.IDAO;
import projektovanje.dbDAO.DBDAOIzdavanje;
import projektovanje.dbDAO.DBDAOKarta;
import projektovanje.dbDAO.IDBDAO;
import projektovanje.dto.DTOIzdavanje;
import projektovanje.dto.DTOKarta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class ServisZaProdavcaKarata {

    public static void pregledRepertoara(){

    }

    public static void prodajaKarte(){

    }

    public static void rezervacijaKarte(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("WHICHONE");
        out.writeObject(odgovor);
        DTOIzdavanje izdavanjeDTO = (DTOIzdavanje)in.readObject();
        Izdavanje izdavanje = izdavanjeDTO.getIzdavanje();
        Karta k = izdavanje.getKarta();
        DBDAOKarta kartaDAO = new DBDAOKarta();
        kartaDAO.upisiUBazu(new DTOKarta(k),konekcijaNaBazu);
        int kartaId = kartaDAO.zadnjiUmetnutiId(konekcijaNaBazu);
        izdavanje.getKarta().setIdKarte(kartaId);
        new DBDAOIzdavanje().upisiUBazu(new DTOIzdavanje(izdavanje),konekcijaNaBazu);
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }

    public static void pregledSvihProjekcija(){

    }

    public static void otkazi(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("WHICHONE");
        out.writeObject(odgovor);
        DTOIzdavanje izdavanje = (DTOIzdavanje) in.readObject();
        Karta karta = izdavanje.getIzdavanje().getKarta();
        karta.setRezervisana(false);
        new DBDAOKarta().azurirajBazu(new DTOKarta(karta),konekcijaNaBazu);
        new DBDAOIzdavanje().ukloniIzdavanje(izdavanje,konekcijaNaBazu);
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }
}
