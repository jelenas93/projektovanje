package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;
import projektovanje.enumPackage.Korisnici;
import projektovanje.ostalo.Logovanje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import static projektovanje.enumPackage.Korisnici.*;

public class ServisZaPrijavu {
    private static Logovanje logerZaPrijavu = new Logovanje(new ServisZaPrijavu());

    private static Boolean prijavaNaServer(String message, Connection konekcijaNaBazu,  Nalog nalogTrenutnogKorisnika){
        Boolean uspjesno = false;
        String[] listaArgumenata = message.trim().split("#");
        if(3 != listaArgumenata.length){
            logerZaPrijavu.logujDogadjaj(Level.INFO, new ServisZaPrijavu(),"Nije dobra lista argumenata");
            uspjesno = false;
            return uspjesno;
        }
        try {
            DTONalog nalogIzBaze = (DTONalog)new DBDAONalog().pretraziBazu(konekcijaNaBazu, listaArgumenata[1]);
            if((null != nalogIzBaze) ) {
                if (nalogIzBaze.getNalog().getKorisnickiNalog().equals(listaArgumenata[1])) {
                    if ((nalogIzBaze.getNalog().getLozinkaHash().equals(listaArgumenata[2]))) {
                        nalogTrenutnogKorisnika.setKorisnickiNalog(listaArgumenata[1]);
                        nalogTrenutnogKorisnika.setLozinkaHash(listaArgumenata[2]);
                        logerZaPrijavu.logujDogadjaj(Level.FINEST, nalogTrenutnogKorisnika.getClass(),"Uspjesna provjera.");
                        uspjesno = true;
                    }
                }

            }else{
                logerZaPrijavu.logujDogadjaj(Level.FINEST, new Nalog(),"Pogresno uneseno korisnicko ime ili sifra prilikom pokusaja prijave na server.");
                uspjesno = false;
            }
        } catch (Exception e) {
            logerZaPrijavu.logujDogadjaj(Level.WARNING, new Exception(), e.getStackTrace().toString());
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
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOAdministrator(), "Administrator prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#ADMINISTRATOR#" + dtoAdministrator.getAdministrator().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOAdministrator(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                    }else if(null != (dtoKinooperater = (DTOKinooperater) new DBDAOKinooperater().pretraziBazu(konekcijaNaBazu, pomInt.toString()))){
                        if(dtoKinooperater.getKinooperater().getAktivan()) {
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOKinooperater(), "Kinooperater prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#KINOOPERATER#" + dtoKinooperater.getKinooperater().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.KINOOPERATER.getKinoopreater()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOKinooperater(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                     }else if(null != (dtoMenadzer = (DTOMenadzer) new DBDAOMenadzer().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoMenadzer.getMenadzer().getAktivan()) {
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOMenadzer(), "Menadzer prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#MENADZER#" + dtoMenadzer.getMenadzer().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.MENADZER.getMenadzer()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOMenadzer(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                     }else if(null != (dtoProdavacHraneIPica = (DTOProdavacHraneIPica) new DBDAOProdavacHraneIPica().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoProdavacHraneIPica.getProdavacHraneIPica().getAktivan()) {
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOProdavacHraneIPica(), "Prodavac hrane i pica prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#PRODAVACHARANEIPICA#" + dtoProdavacHraneIPica.getProdavacHraneIPica().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.PRODAVACHARANEIPICA.getProdavacHraneIPica()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOProdavacHraneIPica(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                   }else if(null != (dtoProdavacKarata = (DTOProdavacKarata) new DBDAOProdavacKarata().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoProdavacKarata.getProdavacKarata().getAktivan()) {
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOProdavacKarata(), "Prodavac karata prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#PRODAVACKARATA#" + dtoProdavacKarata.getProdavacKarata().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOProdavacKarata(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                    }else if(null != (dtoRacunovodja = (DTORacunovodja) new DBDAORacunovodja().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoRacunovodja.getRacunovodja().getAktivan()) {
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTORacunovodja(), "Racunovodja prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#RACUNOVODJA#" + dtoRacunovodja.getRacunovodja().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTORacunovodja(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                     }else if(null != (dtoSkladistar = (DTOSkladistar) new DBDAOSkladistar().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        if(dtoSkladistar.getSkladistar().getAktivan()) {
                            logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOSkladistar(), "Skladistar prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("OK#SKLADISTAR#" + dtoSkladistar.getSkladistar().getIdZaposlenog().toString()));
                            prijavljen[Korisnici.SKLADISTAR.getSkladistar()] = true;
                            return;
                        }else{
                            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOSkladistar(), "Pokusaj prijave neaktivnog naloga.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                            out.writeObject(new String("NOK#Nalog vise nije aktivan."));
                            return;
                        }
                    }else{
                        logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOZaposleni(), "Pokusaj prijeve na server od strane korisika koji vise nije zaposlen.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                        out.writeObject(new String("NOK#Zaposleni nije prijavljen ni na jedno radno mjesto."));
                        return;
                    }
                }
            }else{
                logerZaPrijavu.logujDogadjaj(Level.FINEST, new DTOKlijent(), "Klijent prijavljen na sistem.\nKorisnicko ime: "+ msg.trim().split("#")[1]);
                out.writeObject(new String("OK#KLIJENT#" + dtoKlijent.getKlijent().getIdKlijenta().toString()));
                prijavljen[Korisnici.KLIJENT.getKlijent()] = true;
                out.writeObject(dtoKlijent);
            }
        }else{
            logerZaPrijavu.logujDogadjaj(Level.WARNING, new DTOZaposleni(), "Neuspio pokusaj prijave na server. Pogresna sifra ili korisnicko ime.");
            out.writeObject(new String("NOK#KorisnickoIme ili Sifra nisu tacni."));
            out.writeObject(null);
        }
    }
}
