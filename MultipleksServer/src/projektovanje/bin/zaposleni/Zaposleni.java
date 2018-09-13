package projektovanje.bin.zaposleni;

import projektovanje.bin.plata.Plata;
import projektovanje.bin.nalog.Nalog;

import java.io.Serializable;


public abstract class Zaposleni implements Serializable {
    private String ime;
    private String prezime;
    private String JMBG;
    private Plata plata;
    private Integer idZaposlenog;
    private Nalog nalog;

    public Zaposleni(){}

    public Zaposleni(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog){
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.plata = plata;
        this.idZaposlenog = idZaposlenog;
        this.nalog = nalog;
    }

    public Integer getIdZaposlenog() {
        return idZaposlenog;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public Plata getPlata() {
        return plata;
    }

    public String getIme() {
        return ime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIdZaposlenog(Integer idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public void setPlata(Plata plata) {
        this.plata = plata;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
