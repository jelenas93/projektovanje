package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dbDAO.*;
import projektovanje.dto.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class ServisZaPrijavu {
    private Boolean prijavaNaServer(String message, Connection konekcijaNaBazu,  Nalog nalogTrenutnogKorisnika){
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

    public void outPrijava(String msg, Connection konekcijaNaBazu, ObjectOutputStream out,  Nalog nalogTrenutnogKorisnika) throws IOException, SQLException {
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
                        out.writeObject(dtoAdministrator);
                    }else if(null != (dtoKinooperater = (DTOKinooperater) new DBDAOKinooperater().pretraziBazu(konekcijaNaBazu, pomInt.toString()))){
                        out.writeObject(new String("OK#KINOOPERATER"));
                        out.writeObject(dtoKinooperater);
                    }else if(null != (dtoMenadzer = (DTOMenadzer) new DBDAOMenadzer().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#MENADZER"));
                        out.writeObject(dtoMenadzer);
                    }else if(null != (dtoProdavacHraneIPica = (DTOProdavacHraneIPica) new DBDAOProdavacHraneIPica().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#PRODAVACHARANEIPICA"));
                        out.writeObject(dtoProdavacHraneIPica);
                    }else if(null != (dtoProdavacKarata = (DTOProdavacKarata) new DBDAOProdavacKarata().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#PRODAVACKARATA"));
                        out.writeObject(dtoProdavacKarata);
                    }else if(null != (dtoRacunovodja = (DTORacunovodja) new DBDAORacunovodja().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#RACUNOVODJA"));
                        out.writeObject(dtoRacunovodja);
                    }else if(null != (dtoSkladistar = (DTOSkladistar) new DBDAOSkladistar().pretraziBazu(konekcijaNaBazu,pomInt.toString()))){
                        out.writeObject(new String("OK#SKLADISTAR"));
                        out.writeObject(dtoSkladistar);
                    }else{
                        out.writeObject(new String("NOK Zaposleni nije prijavljen ni na jedno radno mjesto."));
                        out.writeObject(null);
                    }
                }
            }else{
                out.writeObject(new String("OK#KLIJENT"));
                out.writeObject(dtoKlijent);
            }
        }else{
            out.writeObject(new String("NOK KorisnickoIme ili Sifra nisu tacni."));
            out.writeObject(null);
        }
    }
}
