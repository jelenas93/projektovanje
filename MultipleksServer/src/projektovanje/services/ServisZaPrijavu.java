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
        String[] listaArgumenata = message.trim().split("#");
        if(3 != listaArgumenata.length){
            System.out.println("Nije dobra lista argumenata");
            uspjesno = false;
            return uspjesno;
        }
        try {
            System.out.println("Usao u nabavljanje naloga iz baze. "+ listaArgumenata[0] + " " + listaArgumenata[1] + " " + listaArgumenata[2]);
            DTONalog nalogIzBaze = (DTONalog)new DBDAONalog().pretraziBazu(konekcijaNaBazu, listaArgumenata[1]);
            System.out.println("Da li je razlicito od null " + (null!= nalogIzBaze));
            System.out.println("Da li je username jedanak " +(nalogIzBaze.getNalog().getKorisnickiNalog().equals(listaArgumenata[1])));
            System.out.println("Da li je sifra jedanaka " + (nalogIzBaze.getNalog().getLozinkaHash().equals(listaArgumenata[2])));
            if((null != nalogIzBaze) ) {
                System.out.println("Proslo da nije null");
                if (nalogIzBaze.getNalog().getKorisnickiNalog().equals(listaArgumenata[1])) {
                    System.out.println("Proslo da je username jednak ");
                    if ((nalogIzBaze.getNalog().getLozinkaHash().equals(listaArgumenata[2]))) {
                        System.out.println("Proslo da je jednaka sifra ");
                        nalogTrenutnogKorisnika.setKorisnickiNalog(listaArgumenata[1]);
                        nalogTrenutnogKorisnika.setLozinkaHash(listaArgumenata[2]);
                        System.out.println("Uspjesna provjera.");
                        uspjesno = true;
                    }
                }

            }else{
                System.out.println("Ne poklapaju se sifra i username");
                uspjesno = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            uspjesno = false;
        } finally {
            return uspjesno;
        }
    }

    public static void outPrijava(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, Nalog nalogTrenutnogKorisnika, Boolean[] prijavljen, ObjectInputStream in) throws IOException, SQLException, ClassNotFoundException {
        ServisZaPrijavu servisZaPrijavu = new ServisZaPrijavu();
        System.out.println("Usao u prijavu");
        if(servisZaPrijavu.prijavaNaServer(msg,konekcijaNaBazu, nalogTrenutnogKorisnika)){
            System.out.println("Usao u prvi if uslov");
            DTOKlijent dtoKlijent = (DTOKlijent) new DBDAOKlijent().pretraziKlijentaPoNalogu(konekcijaNaBazu,msg.split("#")[1]);
            if(null == dtoKlijent){
                DTOZaposleni dtoZaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziZaposlenogPoNalogu(konekcijaNaBazu, msg.split("#")[1]);
                if(null == dtoZaposleni){
                    out.writeObject(new String("NOK#Novi#Username ili Sifra nisu tacni."));
                    return;
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
                        if(dtoAdministrator.getAdministrator().getAktivan()){
                            out.writeObject(new String("OK#ADMINISTRATOR#" + dtoAdministrator.getAdministrator().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()] = true;
                            return;
                        }else{
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                    }else if(null != (dtoKinooperater = (DTOKinooperater) new DBDAOKinooperater().pretraziBazu(konekcijaNaBazu, pomInt.toString()))){
                        if(dtoKinooperater.getKinooperater().getAktivan()) {
                            out.writeObject(new String("OK#KINOOPERATER#" + dtoKinooperater.getKinooperater().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.KINOOPERATER.getKinoopreater()] = true;
                            return;
                        }else{
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                     }else if(null != (dtoMenadzer = (DTOMenadzer) new DBDAOMenadzer().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoMenadzer.getMenadzer().getAktivan()) {
                            out.writeObject(new String("OK#MENADZER#" + dtoMenadzer.getMenadzer().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.MENADZER.getMenadzer()] = true;
                            return;
                        }else{
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                     }else if(null != (dtoProdavacHraneIPica = (DTOProdavacHraneIPica) new DBDAOProdavacHraneIPica().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoProdavacHraneIPica.getProdavacHraneIPica().getAktivan()) {
                            out.writeObject(new String("OK#PRODAVACHARANEIPICA#" + dtoProdavacHraneIPica.getProdavacHraneIPica().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.PRODAVACHARANEIPICA.getProdavacHraneIPica()] = true;
                            return;
                        }else{
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                   }else if(null != (dtoProdavacKarata = (DTOProdavacKarata) new DBDAOProdavacKarata().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoProdavacKarata.getProdavacKarata().getAktivan()) {
                            out.writeObject(new String("OK#PRODAVACKARATA#" + dtoProdavacKarata.getProdavacKarata().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()] = true;
                            return;
                        }else{
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                    }else if(null != (dtoRacunovodja = (DTORacunovodja) new DBDAORacunovodja().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoRacunovodja.getRacunovodja().getAktivan()) {
                            out.writeObject(new String("OK#RACUNOVODJA#" + dtoRacunovodja.getRacunovodja().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()] = true;
                            return;
                        }
                     }else if(null != (dtoSkladistar = (DTOSkladistar) new DBDAOSkladistar().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoSkladistar.getSkladistar().getAktivan()) {
                            out.writeObject(new String("OK#SKLADISTAR#" + dtoSkladistar.getSkladistar().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.SKLADISTAR.getSkladistar()] = true;
                            return;
                        }else{
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                    }else{
                        out.writeObject(new String("NOK#Zaposleni nije prijavljen ni na jedno radno mjesto."));
                        out.writeObject(null);
                        return;
                    }
                }
            }else{
                out.writeObject(new String("OK#KLIJENT#" + dtoKlijent.getKlijent().getIdKlijenta().toString()));
                prijavljen[Korisnici.KLIJENT.getKlijent()] = true;
                out.writeObject(dtoKlijent);
            }
        }else{
            out.writeObject(new String("NOK#KorisnickoIme ili Sifra nisu tacni."));
            out.writeObject(null);
        }
    }
}
