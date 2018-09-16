package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.*;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class ServisZaAdministratora {

    public void prikazListeZaposlenih(){

    }

    public void dodavanjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws IOException, SQLException {
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

    private Boolean provjeriPoziciju(String pozicija) {
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

    private Boolean provjeriNalog(Nalog nalog, Connection konekcijaNaBazu) throws SQLException {
        Boolean postoji = false;
        DTONalog dtoNalog = (DTONalog)new DBDAONalog().pretraziBazu(konekcijaNaBazu,nalog.getKorisnickiNalog());
        if(null != dtoNalog){
            postoji = true;
        }
        return postoji;
    }

    public void azuriranjeZaposlenog(){

    }

    public void brisanjeZaposlenog(){

    }
}
