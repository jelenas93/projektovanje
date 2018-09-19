package projektovanje.net;

import projektovanje.bin.nalog.Nalog;
import projektovanje.db.ConnectionPool;
import projektovanje.dto.DTONalog;
import projektovanje.enumPackage.Korisnici;
import projektovanje.enumPackage.Protokoli;
import projektovanje.ostalo.Logovanje;
import projektovanje.services.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class ServerThread extends Thread{

    Socket klijentSoket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    Connection konekcijaNaBazu= null;
    Nalog nalogTrenutnogKorisnika;
    Boolean prijavljen[] = new Boolean[8];
    Logovanje logServerThreada;

    public ServerThread(Socket klijent) throws IOException, SQLException {
        nalogTrenutnogKorisnika = new Nalog();
        for(Boolean x : prijavljen){
            x = false;
        }
        konekcijaNaBazu = ConnectionPool.getInstance().checkOut();
        klijentSoket = klijent;
        in = new ObjectInputStream(klijent.getInputStream());
        out = new ObjectOutputStream(klijent.getOutputStream());
        logServerThreada = new Logovanje(this);
    }


    @Override
    public void run(){
        while(true){
            try{
                String msg = new String();
                msg = (String)in.readObject();
                System.out.println(msg);
                switch (Protokoli.valueOf(msg.split("#")[0])) {
                    case LOGIN:
                        ServisZaPrijavu.outPrijava(msg, konekcijaNaBazu, out, nalogTrenutnogKorisnika, prijavljen, in);
                        break;
                    case REGISTER:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]) {
                            ServisZaRegistracijuKlijenta.obaviRegistraciju(msg, konekcijaNaBazu, out);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new ServisZaPrijavu(), "Korisnik koji nema nadleznost za registraciju ovih korisnika pokusava dodati novi nalog.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnike nije nadlezan za registraciju zaposlenih."));
                        }
                        break;
                    case CHANGE_PASSWORD:
                        ServisZaPromjenuLozike.promjeniLozinku(msg, konekcijaNaBazu, out, nalogTrenutnogKorisnika);
                        break;
                    case ADD_EMPLOYEE:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]) {
                            ServisZaAdministratora.dodavanjeZaposlenog(msg, konekcijaNaBazu, out);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new ServisZaAdministratora(), "Korisnik koji nije nadlezan za dodavanje novog zaposlenoj je pokusao to izvrsiti.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnike nije Adminstrator."));
                        }
                        break;
                    case LIST_EMPLOYEES:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]){
                            ServisZaAdministratora.prikazListeZaposlenih(msg, konekcijaNaBazu, out);
                            logServerThreada.logujDogadjaj(Level.FINEST, new ServisZaAdministratora(), "Uspjesno vracanje liste zaposlenih na zahtjev administratora.\n Administrator: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }else if(prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            ServisZaRacunovodju.prikazListeZaposlenih(msg, konekcijaNaBazu, out);
                            logServerThreada.logujDogadjaj(Level.FINEST, new ServisZaRacunovodju(), "Uspjesno vracanje liste zaposlenih na zahtjev racunovodje.\n Racunovodja: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nije nadlezan za pregled zaposlenih je poslao zahtjev.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo pregleda zaposlenih."));
                        }
                        break;
                    case UPDATE_EMPLOYEE:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]){
                            ServisZaAdministratora.azuriranjeZaposlenog(msg, konekcijaNaBazu, out, in, nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nije nadlezan za azuriranje zaposlenih je poslao zahtjev.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo promjene podataka zaposlenih."));
                        }
                        break;
                    case DELETE_EMPLOYEE:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]){
                            ServisZaAdministratora.brisanjeZaposlenog(msg, konekcijaNaBazu, out, nalogTrenutnogKorisnika);
                        }else{

                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nije nadlezan za otpustanje zaposlenih je poslao zahtjev.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da otpusti zaposlene."));
                        }
                        break;
                    case NEW_PROJECTION:
                        if (prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaProjekcije.dodajProjekciju(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                            logServerThreada.logujDogadjaj(Level.FINE, this, "Uspjesno dodana nova projekcije na zahtjev korisnika: +" + nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo pregledati filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da pregleda projekcije."));
                        }
                        break;
                    case UPDATE_PROJECTION:
                        if (prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaProjekcije.azurirajProjekciju(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                            logServerThreada.logujDogadjaj(Level.FINE, this, "Uspjesno izlistane projekcije na zahtjev korisnika: +" + nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo pregledati filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da pregleda projekcije."));
                        }
                        break;
                    case LIST_PROJECTIONS:
                        if (prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaProjekcije.izlistajProjekcije(out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                            logServerThreada.logujDogadjaj(Level.FINE, this, "Uspjesno izlistane projekcije na zahtjev korisnika: +" + nalogTrenutnogKorisnika.getKorisnickiNalog());

                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo pregledati filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da pregleda projekcije."));
                        }
                        break;
                    case ADD_MOVIE_HALL:
                        if (prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaSale.dodajSalu(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                            logServerThreada.logujDogadjaj(Level.FINE, this, "Uspjesno izlistane projekcije na zahtjev korisnika: +" + nalogTrenutnogKorisnika.getKorisnickiNalog());

                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo pregledati filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da pregleda projekcije."));
                        }
                        break;
                    case ADD_MOVIE:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]) {
                            ServisZaFilmove.dodajFilm(out, in, konekcijaNaBazu, nalogTrenutnogKorisnika);
                            logServerThreada.logujDogadjaj(Level.FINE, this, "Uspjesno dodat film.");
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nije nadlezan za dodavanje filmova je poslao zahtjev.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da doda film."));
                        }
                        break;
                    case UPDATE_MOVIE:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]) {
                            ServisZaFilmove.azurirajFilm(out, in, konekcijaNaBazu, nalogTrenutnogKorisnika);
                            logServerThreada.logujDogadjaj(Level.FINE, this, "Uspjesno azuriran film.");
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nije nadlezan za dodavanje filmova je poslao zahtjev.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da azurira filmove."));
                        }
                        break;
                    case GIVE_ME_MOVIE:
                        if (prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaFilmove.ispisiFilmove(out,konekcijaNaBazu, nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo pregledati filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da pregleda filmove."));
                        }
                        break;
                    case ADD_OFFER:
                        break;
                    case LIST_OFFERS:
                        break;
                    case ADD_PAYMENT:
                        break;
                    case UPDATE_PAYMENT:
                        break;
                    case LIST_PAYMENTS:
                        break;
                    case ADD_EQUIPMENT:
                        break;
                    case UPDATE_EQUIPMENT:
                        break;
                    case LIST_EQUIPMENT:
                        break;
                    case GET_EQUIPMENT:
                        break;
                    case ADD_INVOICE:
                        break;
                    case UPDATE_INVOICE:
                        break;
                    case LIST_INVOICES:
                        break;
                    case LIST_ALL_BILLS:
                        break;
                    case ADD_BILL:
                        break;
                    case UPDATE_PRODUCT:
                        break;
                    case ADD_PRODUCT:
                        break;
                    case LIST_PRODUCTS:
                        break;
                    case LIST_REPERTOIRE:
                        break;
                    case ADD_MOVIE_TO_REPERTOIRE:
                        break;
                    case ADD_REPERTOIRE:
                        break;
                    case SELL_TICKET:
                        break;
                    case RESERVE_TICKET:
                        if(prijavljen[Korisnici.KLIJENT.getKlijent()] || prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()]){
                            ServisZaProdavcaKarata.rezervacijaKarte(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Uspjesno rezervisana karta");
                        } else {
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da rezervise kartu"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo da rezervise kartu\n Korisnik: "+ nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case CANCEL_RESERVATION:
                        if(prijavljen[Korisnici.KLIJENT.getKlijent()]|| prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()]){
                            ServisZaProdavcaKarata.otkazi(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Uspjesno otkazana rezervacija");
                        } else {
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da otkaze rezervaciju"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Kornisnik koji nema pravo da otkaze rezervaciju\n Korisnik: "+ nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }

                        break;
                    default:
                        logServerThreada.logujDogadjaj(Level.SEVERE, this, "Neispravan protokol. Poruka = " + msg);
                        out.writeObject(new String("NOK ProtokolError"));
                }
                logServerThreada.logujDogadjaj(Level.FINEST, this, "Obradio zahtjev i ceka na sledeci.");
            }catch(SocketException e){
                logServerThreada.logujDogadjaj(Level.SEVERE, new SocketException(), e.getStackTrace().toString());
                break;
            }catch (IOException e){
                logServerThreada.logujDogadjaj(Level.SEVERE, new IOException(), e.getStackTrace().toString());
            }catch (ClassNotFoundException e){
                logServerThreada.logujDogadjaj(Level.SEVERE, new ClassNotFoundException(), e.getStackTrace().toString());
            }catch (java.sql.SQLException e){
                logServerThreada.logujDogadjaj(Level.SEVERE, new SQLException(), e.getStackTrace().toString());
            }catch (Exception e){
                logServerThreada.logujDogadjaj(Level.SEVERE, new Exception(), e.getStackTrace().toString());
            }
        }
        if(null != nalogTrenutnogKorisnika.getKorisnickiNalog()){
            logServerThreada.logujDogadjaj(Level.FINE, this, "Korisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog()+" se odjavio sa Servera.");
        }else{
            logServerThreada.logujDogadjaj(Level.FINE, this, "Korisnik je izgubio konekciju sa Serverom.");
        }
        ConnectionPool.getInstance().checkIn(konekcijaNaBazu);
    }
}
