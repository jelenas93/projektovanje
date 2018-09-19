package projektovanje.services;

import projektovanje.bin.karta.Karta;
import projektovanje.dbDAO.DBDAOKarta;
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

    public static void rezervacijaKarte(){

    }

    public static void pregledSvihProjekcija(){

    }

    public static void otkazi(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("WHICHONE");
        out.writeObject(odgovor);
        Karta karta = (Karta)in.readObject();
        karta.setRezervisana(false);
        DTOKarta dtoKarta = new DTOKarta(karta);
        new DBDAOKarta().azurirajBazu(dtoKarta,konekcijaNaBazu);
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }
}
