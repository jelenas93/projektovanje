package projektovanje.net;

import projektovanje.bin.nalog.Nalog;
import projektovanje.db.ConnectionPool;
import projektovanje.enumPackage.Korisnici;
import projektovanje.enumPackage.Protokoli;
import projektovanje.services.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerThread extends Thread{

    Socket klijentSoket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    Connection konekcijaNaBazu= null;
    Nalog nalogTrenutnogKorisnika;
    Boolean prijavljen[] = new Boolean[8];

    public ServerThread(Socket klijent) throws IOException, SQLException {
        for(Boolean x : prijavljen){
            x = false;
        }
        konekcijaNaBazu = ConnectionPool.getInstance().checkOut();
        klijentSoket = klijent;
        in = new ObjectInputStream(klijent.getInputStream());
        out = new ObjectOutputStream(klijent.getOutputStream());
    }


    @Override
    public void run(){
        while(true){
            try{
                String msg = new String();
                msg = (String)in.readObject();
                switch (Protokoli.valueOf(msg.split("#")[0])) {
                    case LOGIN:
                        ServisZaPrijavu.outPrijava(msg, konekcijaNaBazu, out, nalogTrenutnogKorisnika, prijavljen, in);
                        if(prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            //ServisZaRacunovodju.obradiNedodjeljenePlate(out, in, konekcijaNaBazu); mozda odradi, mozda ne
                        }
                        break;
                    case REGISTER:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]) {
                            ServisZaRegistracijuKlijenta.obaviRegistraciju(msg, konekcijaNaBazu, out);
                        }else{
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
                            out.writeObject(new String("NOK#Prijavljeni korisnike nije Adminstrator."));
                        }
                        break;
                    case LIST_EMPLOYEES:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]){
                            ServisZaAdministratora.prikazListeZaposlenih(msg, konekcijaNaBazu, out);
                        }else if(prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            ServisZaRacunovodju.prikazListeZaposlenih(msg, konekcijaNaBazu, out);
                        }else{
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo pregleda zaposlenih."));
                        }
                        break;
                    case UPDATE_EMPLOYEE:
                        if(prijavljen[Korisnici.ADMINISTRATOR.getAdministrator()]){
                            ServisZaAdministratora.azuriranjeZaposlenog(msg, konekcijaNaBazu, out, in);
                        }else if(prijavljen[Korisnici.RACUNOVODJA.getRacunovodja()]){
                            ServisZaRacunovodju.azuriranjeZaposlenog(msg, konekcijaNaBazu, out, in);
                        }else{
                            out.writeObject(new String("NOK#Prijavljeni korisnik nema pravo promjene podataka zaposlenih."));
                        }
                        break;
                    case DELETE_EMPLOYEE:
                        break;
                    case NEW_PROJECTION:
                        break;
                    case UPDATE_PROJECTION:
                        break;
                    case LIST_PROJECTIONS:
                        break;
                    case ADD_MOVIE_HALL:
                        break;
                    case ADD_MOVIE:
                        break;
                    case UPDATE_MOVIE:
                        break;
                    case GIVE_ME_MOVIE:
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
                    case ADD_TRANSACTION:
                        break;
                    case LIST_TRANSACTIONS:
                        break;
                    case LIST_TRANSACTIONS_TYPE:
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
                        break;
                    case CANCEL_RESERVATION:
                        break;
                    default:
                        out.writeObject(new String("NOK ProtokolError"));

                }
            }catch(SocketException e){

                break;
            }catch (IOException e){

                break;
            }catch (ClassNotFoundException e){

                break;
            }catch (java.sql.SQLException e){

                break;
            }

            System.out.println("I did nothing");
        }
        ConnectionPool.getInstance().checkIn(konekcijaNaBazu);
    }
}
