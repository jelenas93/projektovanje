package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.*;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ServisZaAdministratora {
    private static Logovanje logServisaZaAdministratore;
    static
    {
        logServisaZaAdministratore = new Logovanje(new ServisZaAdministratora());
    }

    public static void prikazListeZaposlenih(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws SQLException, IOException {
       try {
           List<DTOZaposleni> listaAktivnihZaposlenih = (List<DTOZaposleni>) new DBDAOZaposleni().procitajSveAktivneZaposlene(konekcijaNaBazu);
           listaAktivnihZaposlenih.stream().parallel().forEach(x->{
               x.getZaposleni().setPlata(null);
               x.getZaposleni().getNalog().setLozinkaHash(null);
           });
           out.writeObject(listaAktivnihZaposlenih);
           out.flush();
       }catch(Exception e){
           logServisaZaAdministratore.logujDogadjaj(Level.WARNING, new Exception(), e.getStackTrace().toString());
       }
       /*DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu, "8");
       out.writeObject(zaposleni);
       out.flush();*/
    }



    public static void dodavanjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws IOException, SQLException {
        String[] hlpNizStringova = msg.split("#");
        if(7 != hlpNizStringova.length){
            logServisaZaAdministratore.logujDogadjaj(Level.FINE, new ServisZaAdministratora(), "Neispravan protokol. Poruka = " + msg);
            out.writeObject(new String("NOK#Pogresan broj argumenata u protokolu. Provjeri dokumentaciju protokola."));
            return;
        }
        if((13 != hlpNizStringova[3].length()) && (hlpNizStringova[3].matches("[a-zA-Z]+") == true)){
            logServisaZaAdministratore.logujDogadjaj(Level.FINE, new ServisZaAdministratora(), "Neispravan JMBG");
            out.writeObject(new String("NOK#Neispravan JMBG."));
            return;
        }
        Nalog nalog = new Nalog(hlpNizStringova[4], hlpNizStringova[5]);
        Zaposleni noviZaposleni = new Zaposleni(1, Plata.nulaPlata,hlpNizStringova[1], hlpNizStringova[2], hlpNizStringova[3], true, nalog);
        if(provjeriNalog(nalog,konekcijaNaBazu)){
            logServisaZaAdministratore.logujDogadjaj(Level.WARNING, new ServisZaAdministratora(), "Pokusaj dodavanja vec postojeceg naloga.");
            out.writeObject(new String("NOK#Nalog vec postoji."));
            return;
        }
        if(!provjeriPoziciju(hlpNizStringova[6])){
            logServisaZaAdministratore.logujDogadjaj(Level.WARNING, new ServisZaAdministratora(), "Pokusaj dodavanja ne postojece pozicije.");
            out.writeObject(new String("NOK#Pozicija ne postoji."));
            return;
        }
        new DBDAONalog().upisiUBazu(new DTONalog(nalog), konekcijaNaBazu);
        new DBDAOZaposleni().upisiUBazu(new DTOZaposleni(noviZaposleni),konekcijaNaBazu);
        DTOZaposleni dtoUpravoUpisanZaposleni = (DTOZaposleni)new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu,nalog.getKorisnickiNalog());
        if(hlpNizStringova[6].equalsIgnoreCase("administrator")){
            Administrator noviAdministrator = new Administrator(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAOAdministrator().upisiUBazu(new DTOAdministrator(noviAdministrator), konekcijaNaBazu);
        }else if(hlpNizStringova[6].equalsIgnoreCase("menadzer")){
            Menadzer noviMenadzer = new Menadzer(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAOMenadzer().upisiUBazu(new DTOMenadzer(noviMenadzer), konekcijaNaBazu);
        }else if(hlpNizStringova[6].equalsIgnoreCase("ProdavacKarata")){
            ProdavacKarata noviProdavacKarata = new ProdavacKarata(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAOProdavacKarata().upisiUBazu(new DTOProdavacKarata(noviProdavacKarata), konekcijaNaBazu);
        }else if(hlpNizStringova[6].equalsIgnoreCase("Kinooperater")){
            Kinooperater noviKinooperater = new Kinooperater(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAOKinooperater().upisiUBazu(new DTOKinooperater(noviKinooperater), konekcijaNaBazu);
        }else if(hlpNizStringova[6].equalsIgnoreCase("ProdavacHraneIPica")){
            ProdavacHraneIPica noviProdavacHraneIPica = new ProdavacHraneIPica(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAOProdavacHraneIPica().upisiUBazu(new DTOProdavacHraneIPica(noviProdavacHraneIPica), konekcijaNaBazu);
        }else if(hlpNizStringova[6].equalsIgnoreCase("Racunovodja")){
            Racunovodja noviRacunovodja = new Racunovodja(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAORacunovodja().upisiUBazu(new DTORacunovodja(noviRacunovodja), konekcijaNaBazu);
        }else if(hlpNizStringova[6].equalsIgnoreCase("Skladistar")){
            Skladistar noviSkladistar = new Skladistar(dtoUpravoUpisanZaposleni.getZaposleni());
            new DBDAOSkladistar().upisiUBazu(new DTOSkladistar(noviSkladistar), konekcijaNaBazu);
        }
        logServisaZaAdministratore.logujDogadjaj(Level.FINEST, new ServisZaAdministratora(), "Uspjesno dodan novi zaposleni.");
        out.writeObject(new String("OK"));
    }

    private static Boolean provjeriPoziciju(String pozicija) {
        Boolean postoji = false;
        if(pozicija.equalsIgnoreCase("administrator")){
            postoji = true;
        }else if(pozicija.equalsIgnoreCase("menadzer")){
            postoji = true;
        }else if(pozicija.equalsIgnoreCase("ProdavacKarata")){
            postoji = true;
        }else if(pozicija.equalsIgnoreCase("Kinooperater")){
            postoji = true;
        }else if(pozicija.equalsIgnoreCase("ProdavacHraneIPica")){
            postoji = true;
        }else if(pozicija.equalsIgnoreCase("Racunovodja")){
            postoji = true;
        }else if(pozicija.equalsIgnoreCase("Skladistar")){
            postoji = true;
        }
        return postoji;
    }

    private static Boolean provjeriNalog(Nalog nalog, Connection konekcijaNaBazu) throws SQLException {
        Boolean postoji = false;
        DTONalog dtoNalog = (DTONalog)new DBDAONalog().pretraziBazu(konekcijaNaBazu,nalog.getKorisnickiNalog());
        if(null != dtoNalog){
            postoji = true;
        }
        return postoji;
    }

    public static void azuriranjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in, Nalog nalogTrenutnogKorisnika) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        Integer pomocniInteger = dtoZaposleni.getZaposleni().getIdZaposlenog();
        DTOZaposleni provjeraPostojanjaDatogZaposlenog = (DTOZaposleni)new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,pomocniInteger.toString());
        if(null == provjeraPostojanjaDatogZaposlenog.getZaposleni().getIdZaposlenog()){
            logServisaZaAdministratore.logujDogadjaj(Level.WARNING, new ServisZaAdministratora(), "Pokusaj azuriranja ne postojeceg zposlenog.");
            out.writeObject(new String("NOK#Zaposleni ne postoju u bazi."));
            return;
        }
        new DBDAOZaposleni().izmjeniInformacijeOZaposlenom(dtoZaposleni, konekcijaNaBazu);
        logServisaZaAdministratore.logujDogadjaj(Level.FINEST, new ServisZaAdministratora(), "Uspjesno izmjenjene informacije o zaposlenom od strane administratora. " +
                "\nAdministrator: " + nalogTrenutnogKorisnika.getKorisnickiNalog() + "" +
                "\nIzmjenjeni Zaposleni");
        out.writeObject(new String("OK"));
    }

    public static void brisanjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
        if(2 != msg.trim().split("#").length){
            logServisaZaAdministratore.logujDogadjaj(Level.WARNING, new ServisZaAdministratora(), "Greska u protokolu." + msg);
            out.writeObject(new String("NOK#Neispravan protokol."));
        }
        new DBDAOZaposleni().dajOtkaz(msg.trim().split("#")[1], konekcijaNaBazu);
        logServisaZaAdministratore.logujDogadjaj(Level.FINEST, new ServisZaAdministratora(), "Zaposleni sa nalogom: " + msg.trim().split("#")[1] + " je dobio otkaz.\n" +"" +
                "Administrator sa nalogom: " +  nalogTrenutnogKorisnika.getKorisnickiNalog() + "mu je dao otkaz.");
        out.writeObject(new String("OK"));
    }
}
