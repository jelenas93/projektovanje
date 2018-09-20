package projektovanje.services;

import projektovanje.bin.oprema.Artikal;
import projektovanje.dbDAO.DBDAOArtikal;
import projektovanje.dto.DTOArtikal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServisZaArtikle {


    public static void azurirajArtikal(Artikal artikal){

    }

    public static void pregledSvihArtikala(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws SQLException, IOException {
        List<DTOArtikal> artikli = new DBDAOArtikal().ispisi(konekcijaNaBazu);
        out.writeObject(artikli);
    }

    public static void dodajArtikal(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("WHICHONE");
        out.writeObject(odgovor);
        DTOArtikal artikal = (DTOArtikal)in.readObject();
        new DBDAOArtikal().upisiUBazu(artikal,konekcijaNaBazu);
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }
}
