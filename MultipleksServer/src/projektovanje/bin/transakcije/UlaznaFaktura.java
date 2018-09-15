package projektovanje.bin.transakcije;

import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UlaznaFaktura implements Serializable {
    private Integer idFakute;
    private Zaposleni zaposleni;
    private String brojRacina;
    private String vrstaTransakcije;
    private Double kolicina;
    private String jedinicaMjere;
    private Double cijena;
    private String kupac;
    private Date datum;
    private List<? extends IOprema> kupljenaRoba;

    public UlaznaFaktura(Integer idFakute, Zaposleni zaposleni, String brojRacina, String vrstaTransakcije, String jedinicaMjere,Double kolicina, Double cijena, String kupac, Date datum, List<? extends IOprema> kupljenaRoba) {
        this.idFakute = idFakute;
        this.zaposleni = zaposleni;
        this.brojRacina = brojRacina;
        this.vrstaTransakcije = vrstaTransakcije;
        this.kolicina = kolicina;
        this.jedinicaMjere = jedinicaMjere;
        this.cijena = cijena;
        this.kupac = kupac;
        this.datum = datum;
        this.kupljenaRoba = kupljenaRoba;
    }

    public UlaznaFaktura() {
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

    public String getBrojRacina() {
        return brojRacina;
    }

    public void setBrojRacina(String brojRacina) {
        this.brojRacina = brojRacina;
    }

    public String getVrstaTransakcije() {
        return vrstaTransakcije;
    }

    public void setVrstaTransakcije(String vrstaTransakcije) {
        this.vrstaTransakcije = vrstaTransakcije;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    public String getJedinicaMjere() {
        return jedinicaMjere;
    }

    public void setJedinicaMjere(String jedinicaMjere) {
        this.jedinicaMjere = jedinicaMjere;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<? extends IOprema> getKupljenaRoba() {
        return kupljenaRoba;
    }

    public void setKupljenaRoba(List<? extends IOprema> kupljenaRoba) {
        this.kupljenaRoba = kupljenaRoba;
    }

    @Override
    public String toString() {
        return "UlaznaFaktura{" +
                "idFakute=" + idFakute +
                ", zaposleni=" + zaposleni.toString() +
                ", brojRacina='" + brojRacina + '\'' +
                ", vrstaTransakcije='" + vrstaTransakcije + '\'' +
                ", kolicina=" + kolicina +
                ", jedinicaMjere='" + jedinicaMjere + '\'' +
                ", cijena=" + cijena +
                ", kupac='" + kupac + '\'' +
                ", datum=" + datum +
                '}';
    }
}
