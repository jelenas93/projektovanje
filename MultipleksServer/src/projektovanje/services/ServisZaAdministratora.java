package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.*;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServisZaAdministratora {

    public static void prikazListeZaposlenih(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws SQLException, IOException {
        List<DTOAdministrator> listaAktivnihAdministratora;
        List<DTOMenadzer> listaAktivnihMenadzera;
        List<DTORacunovodja> listaAktivnihRacunovodja;
        List<DTOSkladistar> listaAktivnihSkladistara;
        List<DTOProdavacKarata> listaAktivnihProdavacaKarata;
        List<DTOProdavacHraneIPica> listaAktivnihProdavacaHraneIPica;
        List<DTOKinooperater> listaAktivnihKinooperatera;
        List<List<? extends IDTO>> listaAktivnihZaposlenih = new ArrayList<>();

        listaAktivnihAdministratora = (List<DTOAdministrator>)new DBDAOAdministrator().ispisiSveAktivneAdministratore(konekcijaNaBazu);
        listaAktivnihAdministratora.stream().parallel().forEach(x->{
            x.getAdministrator().setPlata(null);
            x.getAdministrator().getNalog().setLozinkaHash(new String());
        });
        listaAktivnihMenadzera = (List<DTOMenadzer>)new DBDAOMenadzer().ispisiSveAktivneMenadzere(konekcijaNaBazu);
        listaAktivnihMenadzera.stream().parallel().forEach(x->{
            x.getMenadzer().setPlata(null);
            x.getMenadzer().getNalog().setLozinkaHash(new String());
        });
        listaAktivnihRacunovodja = (List<DTORacunovodja>)new DBDAORacunovodja().ispisiSveAktivneRacunovodje(konekcijaNaBazu);
        listaAktivnihRacunovodja.stream().parallel().forEach(x->{
            x.getRacunovodja().setPlata(null);
            x.getRacunovodja().getNalog().setLozinkaHash(new String());
        });
        listaAktivnihSkladistara = (List<DTOSkladistar>)new DBDAOSkladistar().ispisiSveAktivneSkladistare(konekcijaNaBazu);
        listaAktivnihSkladistara.stream().parallel().forEach(x->{
            x.getSkladistar().setPlata(null);
            x.getSkladistar().getNalog().setLozinkaHash(new String());
        });
        listaAktivnihProdavacaKarata = (List<DTOProdavacKarata>)new DBDAOProdavacKarata().ispisiSveAktivneProdavceKarata(konekcijaNaBazu);
        listaAktivnihProdavacaKarata.stream().parallel().forEach(x->{
            x.getProdavacKarata().setPlata(null);
            x.getProdavacKarata().getNalog().setLozinkaHash(new String());
        });
        listaAktivnihProdavacaHraneIPica = (List<DTOProdavacHraneIPica>)new DBDAOProdavacHraneIPica().ispisiSveAktivneProdavceHraneIPica(konekcijaNaBazu);
        listaAktivnihProdavacaHraneIPica.stream().parallel().forEach(x->{
            x.getProdavacHraneIPica().setPlata(null);
            x.getProdavacHraneIPica().getNalog().setLozinkaHash(new String());
        });
        listaAktivnihKinooperatera = (List<DTOKinooperater>)new DBDAOKinooperater().ispisiSveAktivneKinooperatere(konekcijaNaBazu);
        listaAktivnihKinooperatera.stream().parallel().forEach(x->{
            x.getKinooperater().setPlata(null);
            x.getKinooperater().getNalog().setLozinkaHash(new String());
        });

        listaAktivnihZaposlenih.add(listaAktivnihAdministratora);
        listaAktivnihZaposlenih.add(listaAktivnihMenadzera);
        listaAktivnihZaposlenih.add(listaAktivnihRacunovodja);
        listaAktivnihZaposlenih.add(listaAktivnihSkladistara);
        listaAktivnihZaposlenih.add(listaAktivnihProdavacaKarata);
        listaAktivnihZaposlenih.add(listaAktivnihProdavacaHraneIPica);
        listaAktivnihZaposlenih.add(listaAktivnihKinooperatera);

        out.writeObject(listaAktivnihZaposlenih);
    }



    public static void dodavanjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws IOException, SQLException {
        String[] hlpNizStringova = msg.split("#");
        if(7 != hlpNizStringova.length){
            out.writeObject(new String("NOK Pogresan broj argumenata u protokolu. Provjeri dokumentaciju protokola."));
            return;
        }
        if((13 != hlpNizStringova[3].length()) && (hlpNizStringova[3].matches("[a-zA-Z]+") == true)){
            out.writeObject(new String("NOK Neispravan JMBG."));
            return;
        }
        Nalog nalog = new Nalog(hlpNizStringova[4], hlpNizStringova[5]);
        Zaposleni noviZaposleni = new Zaposleni(1, Plata.nulaPlata,hlpNizStringova[1], hlpNizStringova[2], hlpNizStringova[3], true, nalog);
        if(provjeriNalog(nalog,konekcijaNaBazu)){
            out.writeObject(new String("NOK Nalog vec postoji."));
            return;
        }
        if(!provjeriPoziciju(hlpNizStringova[6])){
            out.writeObject(new String("NOK Pozicija ne postoji."));
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

    public static void azuriranjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        Integer pomocniInteger = dtoZaposleni.getZaposleni().getIdZaposlenog();
        DTOZaposleni provjeraPostojanjaDatogZaposlenog = (DTOZaposleni)new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,pomocniInteger.toString());
        if(null == provjeraPostojanjaDatogZaposlenog.getZaposleni().getIdZaposlenog()){
            out.writeObject(new String("NOK Zaposleni ne postoju u bazi."));
            return;
        }
        new DBDAOZaposleni().izmjeniInformacijeOZaposlenom(dtoZaposleni, konekcijaNaBazu);
        out.writeObject(new String("OK"));
    }

    public static void brisanjeZaposlenog(){

    }
}
