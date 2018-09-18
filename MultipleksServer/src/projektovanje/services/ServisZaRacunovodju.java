package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ServisZaRacunovodju {
    private static Logovanje logServisaZaRacunovodju;
    static
    {
        logServisaZaRacunovodju = new Logovanje(new ServisZaRacunovodju());
    }
    public static void prikazListeZaposlenih(String msg, Connection konekcijaNaBazu, ObjectOutputStream out) throws IOException, SQLException {
        /*List<DTOAdministrator> listaAktivnihAdministratora;
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

        out.writeObject(listaAktivnihZaposlenih);*/

        try {
            List<DTOZaposleni> listaAktivnihZaposlenih = (List<DTOZaposleni>) new DBDAOZaposleni().procitajSveAktivneZaposlene(konekcijaNaBazu);
            out.writeObject(listaAktivnihZaposlenih);
            out.flush();
        }catch(Exception e){
            logServisaZaRacunovodju.logujDogadjaj(Level.WARNING, new ServisZaRacunovodju(), e.getStackTrace().toString());
        }
    }

    public static void azuriranjeZaposlenog(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, SQLException, ClassNotFoundException {
        out.writeObject(new String("WHICHONE"));
        DTOZaposleni dtoZaposleni = (DTOZaposleni)in.readObject();
        new DBDAOPlata().azurirajBazu(new DTOPlata(dtoZaposleni.getZaposleni().getPlata()), konekcijaNaBazu);
        out.writeObject(new String("OK#Uspjesna izmjena."));
    }
}
