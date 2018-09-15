package projektovanje.bin.karta;

import projektovanje.bin.klijent.Klijent;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.projekcija.Projekcija;

import java.io.Serializable;
import java.util.Date;

public class Karta implements Serializable {
    private Integer idKarte;
    private Date datumIzdavanja;
    private Double cijena;
    private Boolean rezervisana;
    private Nalog nalog;
    private Projekcija projekcija;

    {
        nalog = null;
    }

    public Karta(){
    }

    public Karta(Integer idKarte, Date datumIzdavanja, Double cijena, Projekcija projekcija) {
        this.idKarte = idKarte;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.projekcija = projekcija;
    }

    public Karta(Integer idKarte, Date datumIzdavanja, Double cijena, Boolean rezervisana, Nalog nalog, Projekcija projekcija) {
        this.idKarte = idKarte;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.rezervisana = rezervisana;
        this.nalog = nalog;
        this.projekcija = projekcija;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public Integer getIdKarte() {
        return idKarte;
    }

    public void setIdKarte(Integer idKarte) {
        this.idKarte = idKarte;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public Boolean getRezervisana() {
        return rezervisana;
    }

    public void setRezervisana(Boolean rezervisana) {
        this.rezervisana = rezervisana;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public Karta(Integer idKarte) {
        this.idKarte = idKarte;
    }

    @Override
    public String toString() {
        return "Karta{" +
                "idKarte=" + idKarte +
                ", datumIzdavanja=" + datumIzdavanja +
                ", cijena=" + cijena +
                ", rezervisana=" + rezervisana +
                ", nalog=" + nalog +
                '}';
    }
}
