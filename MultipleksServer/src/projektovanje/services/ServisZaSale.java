package projektovanje.services;

import jdk.dynalink.beans.StaticClass;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dbDAO.DBDAOSala;
import projektovanje.dbDAO.DBDAOSjediste;
import projektovanje.dto.DTOSala;
import projektovanje.dto.DTOSjediste;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ServisZaSale {
    private static Logovanje logServisaZaSale;
    static
    {
        logServisaZaSale = new Logovanje(new ServisZaSale());
    }


    public static void dodajSalu(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOSala dtoSala = (DTOSala)in.readObject();
        DBDAOSala dbdaoSala = new DBDAOSala();
        dbdaoSala.upisiUBazu(dtoSala,konekcijaNaBazu);
        int lastId = dbdaoSala.zadnjiUmetnutiId(konekcijaNaBazu);
        DBDAOSjediste dbdaoSjediste = new DBDAOSjediste();
        for(Sjediste sjediste : dtoSala.getSala().getSjedista()){
            sjediste.getSala().setIdSale(lastId);
            dbdaoSjediste.upisiUBazu(new DTOSjediste(sjediste), konekcijaNaBazu);
        }
        logServisaZaSale.logujDogadjaj(Level.FINE, new ServisZaSale(), "" +
                "Uspjesno dodana nova sala u multipleks od strane menadzera sa korisnickim imenom:" +
                "\nKorisnicko ime: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno kreirana sala."));
    }

    public static void prikaziSale(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws SQLException, IOException {
        List<DTOSala> dtoSale = (List<DTOSala>)new DBDAOSala().ispisi(konekcijaNaBazu);
        logServisaZaSale.logujDogadjaj(Level.FINE, new ServisZaSale(), "" +
                "Uspjesno izlistane sale od strane menadzera sa korisnickim imenom:" +
                "\nKorisnicko ime: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(dtoSale);
    }
}
