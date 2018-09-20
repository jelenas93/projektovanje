package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.oprema.Oprema;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.ostalo.Logovanje;
import sun.util.logging.PlatformLogger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class ServisZaRacunovodju {
    private static Logovanje logServisaZaRacunovodju;
    static
    {
        logServisaZaRacunovodju = new Logovanje(new ServisZaRacunovodju());
    }
    public static void prikazListeZaposlenih(Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        List<DTOZaposleni> listaAktivnihZaposlenih = (List<DTOZaposleni>) new DBDAOZaposleni().procitajSveAktivneZaposlene(konekcijaNaBazu);
        listaAktivnihZaposlenih.stream().parallel().forEach(x-> x.getZaposleni().setNalog(null));
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja trazio i dobio listu zaposlenih sa platama.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(listaAktivnihZaposlenih);
        out.flush();
    }

    public static void azuriranjeZaposlenog(Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException, ClassNotFoundException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        new DBDAOPlata().azurirajBazu(new DTOPlata(dtoZaposleni.getZaposleni().getPlata()), konekcijaNaBazu);
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja azurirao platu zaposlenom.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesna izmjena."));
    }


    public static void dodajPlatuZaposlenom(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        DBDAOPlata dbdaoPlata = new DBDAOPlata();
        dbdaoPlata.upisiUBazu(new DTOPlata(dtoZaposleni.getZaposleni().getPlata()), konekcijaNaBazu);
        int poslednjiPlataID = dbdaoPlata.zadnjiUmetnutiId(konekcijaNaBazu);
        new DBDAOZaposleni().promjeniPlatu(dtoZaposleni.getZaposleni().getIdZaposlenog(),poslednjiPlataID,konekcijaNaBazu);
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja dodao platu zaposlenom.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodana plata."));
    }


    public static void dodajUlaznuFakturu(ObjectInputStream in, ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOUlaznaFaktura lokalnaUlaznaFaktura = (DTOUlaznaFaktura)in.readObject();
        new DBDAOUlaznaFaktura().upisiUBazu(lokalnaUlaznaFaktura,konekcijaNaBazu);
        int poslednjiIDUlazneFakture = new DBDAOUlaznaFaktura().zadnjiUmetnutiId(konekcijaNaBazu);
        DBDAOFakturaArtikal faktArtDao = new DBDAOFakturaArtikal();
        DBDAOFakturaOprema faktOprDao = new DBDAOFakturaOprema();
        DBDAOOprema dbdaoOprema = new DBDAOOprema();
        DBDAOArtikal dbdaoArtikal = new DBDAOArtikal();
        int poslednjiUneseniID;
        Iterator<? extends IOprema> it = lokalnaUlaznaFaktura.getUlaznaFaktura().getKupljenaRoba().iterator();
        while(it.hasNext()){
            IOprema stavka = it.next();
            if(stavka instanceof Artikal){
                dbdaoArtikal.upisiUBazu(new DTOArtikal((Artikal)stavka),konekcijaNaBazu);
                poslednjiUneseniID = dbdaoArtikal.zadnjiUmetnutiId(konekcijaNaBazu);
                faktArtDao.upisiUBazu(poslednjiIDUlazneFakture,poslednjiUneseniID,konekcijaNaBazu);
            } else{
                dbdaoOprema.upisiUBazu(new DTOOprema((Oprema) stavka),konekcijaNaBazu);
                poslednjiUneseniID = dbdaoOprema.zadnjiUmetnutiId(konekcijaNaBazu);
                faktOprDao.upisiUBazu(poslednjiIDUlazneFakture,poslednjiUneseniID,konekcijaNaBazu);
            }
        }
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja dodao ulaznu fakturu.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        out.writeObject(new String("OK#Uspjesno dodana daktura."));
    }

    public static void ispisiUlazneFakture(ObjectOutputStream out, Connection konekcijaNaBazu, Nalog nalogTrenutnogKorisnika) throws SQLException, IOException {
        List<DTOUlaznaFaktura> listaUlaznihFaktura = (List<DTOUlaznaFaktura>)new DBDAOUlaznaFaktura().ispisi(konekcijaNaBazu);
        out.writeObject(listaUlaznihFaktura);
        logServisaZaRacunovodju.logujDogadjaj(Level.FINEST,new ServisZaRacunovodju(),"Racunovodja izlistao ulazne fakturu.\n" +
                "Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
        }
}
