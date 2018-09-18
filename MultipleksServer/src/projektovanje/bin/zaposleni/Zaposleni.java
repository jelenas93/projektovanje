package projektovanje.bin.zaposleni;

import projektovanje.bin.plata.Plata;
import projektovanje.bin.nalog.Nalog;

import java.io.Serializable;


public class Zaposleni implements Serializable {
    public static final long serialVersionUID=9024l;

    private String ime;
    private String prezime;
    private String JMBG;
    private Plata plata;
    private Integer idZaposlenog;
    private Nalog nalog;
    private Boolean aktivan;

    public Zaposleni(){}

    public Zaposleni(Integer idZaposlenog,Plata plata,String ime, String prezime, String JMBG , Boolean aktivan, Nalog nalog){
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.plata = plata;
        this.idZaposlenog = idZaposlenog;
        this.nalog = nalog;
        this.aktivan = aktivan;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Zaposleni(Integer id){
        idZaposlenog = id;
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

    @Override
    public String toString() {
        return "Zaposleni{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", JMBG='" + JMBG + '\'' +
                ", plata=" + plata +
                ", idZaposlenog=" + idZaposlenog +
                ", nalog=" + nalog +
                '}';
    }
}
