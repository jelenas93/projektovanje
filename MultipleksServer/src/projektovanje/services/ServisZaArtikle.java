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

    public static void dodajArtikal(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOUlaznaFaktura dtoUlaznaFaktura = (DTOUlaznaFaktura)in.readObject();
        new DBDAOUlaznaFaktura().upisiUBazu(dtoUlaznaFaktura,konekcijaNaBazu);
        int poslednjiIDUlazneFakture = new DBDAOUlaznaFaktura().zadnjiUmetnutiId(konekcijaNaBazu);
        DBDAOFakturaOprema faktOprDao = new DBDAOFakturaOprema();
        List<Artikal> listaRobe = (List<Artikal>)dtoUlaznaFaktura.getUlaznaFaktura().getKupljenaRoba();
        Iterator<Artikal> iterator = listaRobe.iterator();
        DBDAOArtikal dbdaoArtikal = new DBDAOArtikal();
        while(iterator.hasNext()){
            Artikal stavka = iterator.next();
            dbdaoArtikal.upisiUBazu(new DTOArtikal(stavka),konekcijaNaBazu);
            int poslednjiIDArtikla = dbdaoArtikal.zadnjiUmetnutiId(konekcijaNaBazu);
            faktOprDao.upisiUBazu(poslednjiIDUlazneFakture,poslednjiIDArtikla,konekcijaNaBazu);
        }
        out.writeObject(new String("OK#Uspjesno dodan artikal."));
    }

    public static void azurirajArtikal(Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOArtikal dtoArtikal = (DTOArtikal)in.readObject();
        new DBDAOArtikal().azurirajBazu(dtoArtikal,konekcijaNaBazu);
        out.writeObject(new String("OK#Uspjesno azuriran artikal"));
    }
}
