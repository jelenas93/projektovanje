package projektovanje.services;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.Oprema;
import projektovanje.dbDAO.DBDAOArtikal;
import projektovanje.dbDAO.DBDAOFakturaOprema;
import projektovanje.dbDAO.DBDAOOprema;
import projektovanje.dbDAO.DBDAOUlaznaFaktura;
import projektovanje.dto.DTOArtikal;
import projektovanje.dto.DTOOprema;
import projektovanje.dto.DTOUlaznaFaktura;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class ServisZaArtikle {



    public static void pregledSvihArtikala(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws SQLException, IOException {
        List<DTOArtikal> artikli = new DBDAOArtikal().ispisi(konekcijaNaBazu);
        out.writeObject(artikli);
    }



    public static void azurirajArtikal(Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOArtikal dtoArtikal = (DTOArtikal)in.readObject();
        new DBDAOArtikal().azurirajBazu(dtoArtikal,konekcijaNaBazu);
        out.writeObject(new String("OK#Uspjesno azuriran artikal"));
    }
}
