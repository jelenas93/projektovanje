package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServisZaRacunovodju {
    public static void prikazListeZaposlenih(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws IOException, SQLException {
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
            x.getAdministrator().setNalog(null);
        });
        listaAktivnihMenadzera = (List<DTOMenadzer>)new DBDAOMenadzer().ispisiSveAktivneMenadzere(konekcijaNaBazu);
        listaAktivnihMenadzera.stream().parallel().forEach(x->{
            x.getMenadzer().setNalog(null);
        });
        listaAktivnihRacunovodja = (List<DTORacunovodja>)new DBDAORacunovodja().ispisiSveAktivneRacunovodje(konekcijaNaBazu);
        listaAktivnihRacunovodja.stream().parallel().forEach(x->{
            x.getRacunovodja().setNalog(null);
        });
        listaAktivnihSkladistara = (List<DTOSkladistar>)new DBDAOSkladistar().ispisiSveAktivneSkladistare(konekcijaNaBazu);
        listaAktivnihSkladistara.stream().parallel().forEach(x->{
            x.getSkladistar().setNalog(null);
        });
        listaAktivnihProdavacaKarata = (List<DTOProdavacKarata>)new DBDAOProdavacKarata().ispisiSveAktivneProdavceKarata(konekcijaNaBazu);
        listaAktivnihProdavacaKarata.stream().parallel().forEach(x->{
            x.getProdavacKarata().setNalog(null);
        });
        listaAktivnihProdavacaHraneIPica = (List<DTOProdavacHraneIPica>)new DBDAOProdavacHraneIPica().ispisiSveAktivneProdavceHraneIPica(konekcijaNaBazu);
        listaAktivnihProdavacaHraneIPica.stream().parallel().forEach(x->{
            x.getProdavacHraneIPica().setNalog(null);
        });
        listaAktivnihKinooperatera = (List<DTOKinooperater>)new DBDAOKinooperater().ispisiSveAktivneKinooperatere(konekcijaNaBazu);
        listaAktivnihKinooperatera.stream().parallel().forEach(x->{
            x.getKinooperater().setNalog(null);
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

    public static void azuriranjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, SQLException, ClassNotFoundException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        Integer pomocniInteger = dtoZaposleni.getZaposleni().getIdZaposlenog();
        DTOPlata dtoPlata = new DTOPlata(dtoZaposleni.getZaposleni().getPlata());
        if(null == dtoPlata){
            out.writeObject(new String("NOK Plata nije ispravna."));
        }
        DTOZaposleni provjeraPostojanjaDatogZaposlenog = (DTOZaposleni)new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,pomocniInteger.toString());
        if(null == provjeraPostojanjaDatogZaposlenog.getZaposleni().getIdZaposlenog()){
            out.writeObject(new String("NOK Zaposleni ne postoju u bazi."));
            return;
        }
        new DBDAOPlata().upisiUBazu(dtoPlata, konekcijaNaBazu);
        new DBDAOZaposleni().izmjeniInformacijeOZaposlenom(dtoZaposleni, konekcijaNaBazu);
        out.writeObject(new String("OK"));
    }
}
