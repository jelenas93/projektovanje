package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.enumPackage.Korisnici;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import static projektovanje.enumPackage.Korisnici.*;

public class ServisZaPrijavu {
    private static Boolean prijavaNaServer(String message, Connection konekcijaNaBazu,  Nalog nalogTrenutnogKorisnika){
        Boolean uspjesno = false;
        String[] listaArgumenata = message.split("#");
        if(3 != listaArgumenata.length){
            uspjesno = false;
            return uspjesno;
        }
        try {
            DTONalog nalogIzBaze = (DTONalog)new DBDAONalog().pretraziBazu(konekcijaNaBazu, listaArgumenata[1]);
            if((null != nalogIzBaze) && (nalogIzBaze.getNalog().getKorisnickiNalog().equals(listaArgumenata[1])) && ((nalogIzBaze.getNalog().getLozinkaHash().equals(listaArgumenata[2])))){
               nalogTrenutnogKorisnika.setKorisnickiNalog(nalogIzBaze.getNalog().getKorisnickiNalog());
               nalogTrenutnogKorisnika.setLozinkaHash(nalogIzBaze.getNalog().getLozinkaHash());
                uspjesno = true;
            }else{
                uspjesno = false;
            }
        } catch (SQLException e) {
            uspjesno = false;
        } finally {
            return uspjesno;
        }
    }

    public static void outPrijava(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika, Boolean[] prijavljen, ObjectInputStream in) throws IOException, SQLException, ClassNotFoundException {
        ServisZaPrijavu servisZaPrijavu = new ServisZaPrijavu();
        if(servisZaPrijavu.prijavaNaServer(msg,konekcijaNaBazu, nalogTrenutnogKorisnika)){
            DTOKlijent dtoKlijent = (DTOKlijent) new DBDAOKlijent().pretraziKlijentaPoNalogu(konekcijaNaBazu,msg.split("#")[1]);
            if(null == dtoKlijent){
                DTOZaposleni dtoZaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu, msg.split("#")[1]);
                if(null == dtoZaposleni){
                    out.writeObject(new String("NOK KorisnickoIme ili Sifra nisu tacni."));
                    out.writeObject(null);
                }else{
                    Integer pomInt = dtoZaposleni.getZaposleni().getIdZaposlenog();
                    DTOAdministrator dtoAdministrator;
                    DTOKinooperater dtoKinooperater;
                    DTOMenadzer dtoMenadzer;
                    DTOProdavacHraneIPica dtoProdavacHraneIPica;
                    DTOProdavacKarata dtoProdavacKarata;
                    DTORacunovodja dtoRacunovodja;
                    DTOSkladistar dtoSkladistar;
                    if(null != (dtoAdministrator = (DTOAdministrator) new DBDAOAdministrator().pretraziBazu(konekcijaNaBazu, pomInt.toString()))){
                        out.writeObject(new String("OK#ADMINISTRATOR"));
                        prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoAdministrator);
                    }else if(null != (dtoKinooperater = (DTOKinooperater) new DBDAOKinooperater().pretraziBazu(konekcijaNaBazu, pomInt.toString()))){
                        out.writeObject(new String("OK#KINOOPERATER"));
                        prijavljen[Korisnici.KINOOPERATER.getKinoopreater()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoKinooperater);
                    }else if(null != (dtoMenadzer = (DTOMenadzer) new DBDAOMenadzer().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#MENADZER"));
                        prijavljen[Korisnici.MENADZER.getMenadzer()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoMenadzer);
                    }else if(null != (dtoProdavacHraneIPica = (DTOProdavacHraneIPica) new DBDAOProdavacHraneIPica().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#PRODAVACHARANEIPICA"));
                        prijavljen[Korisnici.PRODAVACHARANEIPICA.getProdavacHraneIPica()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoProdavacHraneIPica);
                    }else if(null != (dtoProdavacKarata = (DTOProdavacKarata) new DBDAOProdavacKarata().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#PRODAVACKARATA"));
                        prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoProdavacKarata);
                    }else if(null != (dtoRacunovodja = (DTORacunovodja) new DBDAORacunovodja().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#RACUNOVODJA"));
                        prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoRacunovodja);
                    }else if(null != (dtoSkladistar = (DTOSkladistar) new DBDAOSkladistar().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#SKLADISTAR"));
                        prijavljen[Korisnici.SKLADISTAR.getSkladistar()] = true;
                        String provjera = (String)in.readObject();
                        out.writeObject(dtoSkladistar);
                    }else{
                        out.writeObject(new String("NOK Zaposleni nije prijavljen ni na jedno radno mjesto."));
                        out.writeObject(null);
                    }
                }
            }else{
                out.writeObject(new String("OK#KLIJENT"));
                prijavljen[Korisnici.KLIJENT.getKlijent()] = true;
                out.writeObject(dtoKlijent);
            }
        }else{
            out.writeObject(new String("NOK KorisnickoIme ili Sifra nisu tacni."));
            out.writeObject(null);
        }
    }
}
