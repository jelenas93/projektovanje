package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.oprema.Oprema;
import projektovanje.dbDAO.DBDAOFakturaArtikal;
import projektovanje.dbDAO.DBDAOFakturaOprema;
import projektovanje.dbDAO.DBDAOOprema;
import projektovanje.dbDAO.DBDAOUlaznaFaktura;
import projektovanje.dto.DTOOprema;
import projektovanje.dto.DTOUlaznaFaktura;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class ServisZaKinooperatere {
    private static Logovanje logServisaZaKinooperatere;
    static
    {
        logServisaZaKinooperatere = new Logovanje(new ServisZaKinooperatere());
    }
    public static void dodajOpremu(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOUlaznaFaktura dtoUlaznaFaktura = (DTOUlaznaFaktura)in.readObject();
        new DBDAOUlaznaFaktura().upisiUBazu(dtoUlaznaFaktura,konekcijaNaBazu);
        int poslednjiIDUlazneFakture = new DBDAOUlaznaFaktura().zadnjiUmetnutiId(konekcijaNaBazu);
        DBDAOFakturaOprema faktOprDao = new DBDAOFakturaOprema();
        List<Oprema> listaRobe = (List<Oprema>)dtoUlaznaFaktura.getUlaznaFaktura().getKupljenaRoba();
        Iterator<Oprema> iterator = listaRobe.iterator();
        DBDAOOprema dbdaoOprema = new DBDAOOprema();
        while(iterator.hasNext()){
            Oprema stavka = iterator.next();
            dbdaoOprema.upisiUBazu(new DTOOprema(stavka),konekcijaNaBazu);
            int poslednjiIDOpreme = dbdaoOprema.zadnjiUmetnutiId(konekcijaNaBazu);
            faktOprDao.upisiUBazu(poslednjiIDUlazneFakture,poslednjiIDOpreme,konekcijaNaBazu);
        }
        logServisaZaKinooperatere.logujDogadjaj(Level.FINEST,new ServisZaKinooperatere(),"Dodana oprema od strane zaposelnog: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodana oprema."));
    }

    public static void izmjeniOpremu(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOOprema dtoOprema = (DTOOprema)in.readObject();
        new DBDAOOprema().azurirajBazu(dtoOprema,konekcijaNaBazu);
        logServisaZaKinooperatere.logujDogadjaj(Level.FINEST,new ServisZaKinooperatere(),"Izmjenjena oprema od strane zaposelnog: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno Izmjenjena oprema."));
    }

    public static void izlistajOpremu(ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws SQLException, IOException {
        List<DTOOprema> listaOpreme = new ArrayList<>();
        listaOpreme = (List<DTOOprema>)new DBDAOOprema().ispisi(konekcijaNaBazu);
        out.writeObject(listaOpreme);
        logServisaZaKinooperatere.logujDogadjaj(Level.FINEST,new ServisZaKinooperatere(),"Izlistana oprema od strane zaposelnog: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
    }

    public static void izlistajOpremuPoImenu(String msg, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        String[] listaArgumenata = msg.trim().split("#");
        List<DTOOprema> listaOpreme = new ArrayList<>();
        listaOpreme = (List<DTOOprema>)new DBDAOOprema().pretraziOpremuPoNazivu(konekcijaNaBazu,listaArgumenata[1]);
        out.writeObject(listaOpreme);
        logServisaZaKinooperatere.logujDogadjaj(Level.FINEST,new ServisZaKinooperatere(),"Izlistana oprema od strane zaposelnog: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
    }

}
