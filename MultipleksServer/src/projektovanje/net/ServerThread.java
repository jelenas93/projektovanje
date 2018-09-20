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
                            ServisZaRacunovodju.prikazListeZaposlenih(konekcijaNaBazu, out, nalogTrenutnogKorisnika);
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
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaPonude.dodajPonudeNaFilmove(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo dodavati ponude na filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da dodaje ponude na filmove."));
                        }
                        break;
                    case LIST_OFFERS:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaPonude.izlistajPonude(out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo dodavati ponude na filmove.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izlistava ponude na  filmove."));
                        }
                        break;
                    case ADD_PAYMENT:
                        if (prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]) {
                            ServisZaRacunovodju.dodajPlatuZaposlenom(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo dodavati plate.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da dodaje novu platu."));
                        }
                        break;
                    case UPDATE_PAYMENT:
                        if (prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]) {
                            ServisZaRacunovodju.azuriranjeZaposlenog(konekcijaNaBazu,out,in,nalogTrenutnogKorisnika);
                        } else {
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo izmjeniti plate.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izmjeni platu."));
                        }
                        break;
                    case LIST_PAYMENTS:
                        if (prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]) {
                            ServisZaRacunovodju.prikazListeZaposlenih(konekcijaNaBazu,out,nalogTrenutnogKorisnika);
                        } else {
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo izlistati plate.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izlista plate."));
                        }
                        break;
                    case ADD_EQUIPMENT:
                        if(prijavljen[Korisnici.KINOOPERATER.getKinoopreater()]){
                            ServisZaKinooperatere.dodajOpremu(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo dodavati opremu.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da dodaje opremu."));
                        }
                        break;
                    case UPDATE_EQUIPMENT:
                        if(prijavljen[Korisnici.KINOOPERATER.getKinoopreater()]){
                            ServisZaKinooperatere.izmjeniOpremu(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo izmjenjivati podatke o opremi.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izmjenjuje podatke o opremi."));
                        }
                        break;
                    case LIST_EQUIPMENT:
                        if(prijavljen[Korisnici.KINOOPERATER.getKinoopreater()]){
                            ServisZaKinooperatere.izlistajOpremu(out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo izlistati podatke o opremi.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izlista podatke o opremi."));
                        }
                        break;
                    case GET_EQUIPMENT:
                        if(prijavljen[Korisnici.KINOOPERATER.getKinoopreater()]){
                            ServisZaKinooperatere.izlistajOpremuPoImenu(msg,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo izlistati podatke o opremi.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izlista podatke o opremi."));
                        }
                        break;
                    case ADD_INVOICE:
                        if(prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            ServisZaRacunovodju.dodajUlaznuFakturu(in,out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo dodavati fakture je to pokusao.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da dodaje fakture."));
                        }
                        break;
                    case LIST_INVOICES:
                        if(prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            ServisZaRacunovodju.ispisiUlazneFakture(out,konekcijaNaBazu,nalogTrenutnogKorisnika);
                        }else{
                            logServerThreada.logujDogadjaj(Level.WARNING, new DTONalog(), "Korisnik koji nema pravo izlistati fakture je to pokusao.\nKorisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da izlistati fakture."));
                        }
                        break;
                    case LIST_ALL_BILLS:
                        if(prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()] || prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            ServisZaRacune.slanjeSvihRacuna(msg,konekcijaNaBazu,out,in);
                        }
                        break;
                    case ADD_BILL:
                        if(prijavljen[Korisnici.PRODAVACHARANEIPICA.getProdavacHraneIPica()]){
                            ServisZaRacune.dodajRacun(msg,konekcijaNaBazu,in,out);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Uspjesno dodan racun");
                        } else{
                            out.writeObject(new String("NOK#Trenutni korisnik nema pravo dodavanja racuna"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo je pokusao dodati racun\n Korisnik:"+nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case UPDATE_PRODUCT:
                        if(prijavljen[Korisnici.SKLADISTAR.getSkladistar()]){
                            ServisZaArtikle.azurirajArtikal(konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Azuriran artikal");
                        } else{
                            out.writeObject(new String("NOK#Trenutni korisnik ne moze azurirati artikle"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo pokusao je azurirati artikal\n Korisnik: "+nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case ADD_PRODUCT:
                        break;
                    case LIST_PRODUCTS:
                        if (prijavljen[Korisnici.PRODAVACHARANEIPICA.getProdavacKarata()] || prijavljen[Korisnici.SKLADISTAR.getSkladistar()]){
                            ServisZaArtikle.pregledSvihArtikala(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Poslani svi artikli");
                        } else{
                            out.writeObject(new String("NOK#Trenutni korisnik ne moze pregledati trenutni repertoar"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo pokusao je pregledati sve artikle\n Korisnik: "+nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case GET_CURRENT_REPERTOIRE:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]|| prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()] || prijavljen[Korisnici.KLIJENT.getKlijent()]){
                            ServisZaRepertoar.pregledTrenutnogRepertoara(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Poslan trenutni repertoar");
                        } else{
                            out.writeObject(new String("NOK#Trenutni korisnik ne moze pregledati trenutni repertoar"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo pokusao je pregledati trenutni repertoar\n Korisnik: "+nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case LIST_REPERTOIRE:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]|| prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()] || prijavljen[Korisnici.KLIJENT.getKlijent()]){
                            ServisZaRepertoar.pregledSvihRepertoara(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE, this,"Poslani svi repertoari");
                        } else{
                            out.writeObject(new String("NOK#Trenutni korisnik ne moze pregledati repertoare"));
                            logServerThreada.logujDogadjaj(Level.FINE, new DTONalog(),"Korisnik koji nema pravo pokusao pregledati repertoare\n Korisnik: "+nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case ADD_MOVIE_TO_REPERTOIRE:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaRepertoar.dodavanjeFilmaNaRepetoar(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Uspjesno dodan novi film na repertoar");
                        } else{
                            out.writeObject(new String("NOK#Trenutni korisnik ne moze dodavati filmove na repertoar"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo pokusao dodati film na repertoar\n Korisnik:" + nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                        break;
                    case ADD_REPERTOIRE:
                        if(prijavljen[Korisnici.MENADZER.getMenadzer()]){
                            ServisZaRepertoar.dodavanjeRepertoara(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Uspjesno dodan repertoar");
                        } else{
                            out.writeObject(new String("NOK# Prijavljeni korisnik nema pravo da dodaje repertoar"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo pokusao dodati repertoar\n Korisnik: " + nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
                    case SELL_TICKET:
                        if(prijavljen[Korisnici.PRODAVACKARATA.getProdavacKarata()]){
                            ServisZaProdavcaKarata.prodajaKarte(msg,konekcijaNaBazu,out,in);
                            logServerThreada.logujDogadjaj(Level.FINE,this,"Uspjesno prodana karta");
                        } else {
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo da prodaje karte"));
                            logServerThreada.logujDogadjaj(Level.WARNING,new DTONalog(),"Korisnik koji nema pravo pokusao prodati kartu\n Korisnik: "+ nalogTrenutnogKorisnika.getKorisnickiNalog());
                        }
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
