package projektovanje.bin.transakcije;

import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UlaznaFaktura implements Serializable {
    public static final long serialVersionUID=9016l;
    private Integer idFakute;
    private Zaposleni zaposleni;
    private Integer kolicina;
    private String jedinicaMjere;
    private Integer identifikator;
    private String nazivRobe;
    private Double prodajnaVrijednost;
    private Double prodajnaCijena;
    private IOprema roba;
    private Date datumFakture;
    private Integer brojFakture;
    private String dobavljac;
    private String barKod;


    public UlaznaFaktura(Integer idFakute, Zaposleni zaposleni, String jedinicaMjere,Integer kolicina,
                         Integer identifikator, String naziv, Double prodajnaVrijednost,
                         Double prodajnaCijena, IOprema roba,
                         Integer brojFakture, Date datumFakture, String dobavljac, String barKod) {
        this.idFakute = idFakute;
        this.zaposleni = zaposleni;
        this.kolicina = kolicina;
        this.jedinicaMjere = jedinicaMjere;
        this.identifikator = identifikator;
        this.nazivRobe = naziv;
        this.prodajnaVrijednost = prodajnaVrijednost;
        this.prodajnaCijena = prodajnaCijena;
        this.roba = roba;
        this.brojFakture = brojFakture;
        this.datumFakture = datumFakture;
        this.dobavljac = dobavljac;
        this.barKod = barKod;
    }

    public IOprema getRoba() {
        return roba;
    }

    public Date getDatumFakture() {
        return datumFakture;
    }

    public void setDatumFakture(Date datumFakture) {
        this.datumFakture = datumFakture;
    }

    public Integer getBrojFakture() {
        return brojFakture;
    }

    public void setBrojFakture(Integer brojFakture) {
        this.brojFakture = brojFakture;
    }

    public String getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(String dobavljac) {
        this.dobavljac = dobavljac;
    }

    public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barKod) {
        this.barKod = barKod;
    }

    public void setRoba(IOprema roba) {
        this.roba = roba;
    }

    public UlaznaFaktura() {
    }

    public Integer getIdentifikator() {
        return identifikator;
    }

    public void setIdentifikator(Integer identifikator) {
        this.identifikator = identifikator;
    }

    public String getNazivRobe() {
        return nazivRobe;
    }

    public void setNazivRobe(String nazivRobe) {
        this.nazivRobe = nazivRobe;
    }

    public Double getProdajnaVrijednost() {
        return prodajnaVrijednost;
    }

    public void setProdajnaVrijednost(Double prodajnaVrijednost) {
        this.prodajnaVrijednost = prodajnaVrijednost;
    }

    public Double getProdajnaCijena() {
        return prodajnaCijena;
    }

    public void setProdajnaCijena(Double prodajnaCijena) {
        this.prodajnaCijena = prodajnaCijena;
    }

    public Integer getIdFakute() {
        return idFakute;
    }

    public void setIdFakute(Integer idFakute) {
        this.idFakute = idFakute;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getJedinicaMjere() {
        return jedinicaMjere;
    }

    public void setJedinicaMjere(String jedinicaMjere) {
        this.jedinicaMjere = jedinicaMjere;
    }

    @Override
    public String toString() {
        return "UlaznaFaktura{" +
                "idFakute=" + idFakute +
                ", zaposleni=" + zaposleni.toString() +
                ", kolicina=" + kolicina +
                ", jedinicaMjere='" + jedinicaMjere + '\'' +
                '}';
    }
}
