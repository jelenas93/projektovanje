package projektovanje.bin.karta;

import projektovanje.bin.klijent.Klijent;
import projektovanje.bin.projekcija.Projekcija;

import java.io.Serializable;
import java.util.Date;

public class Karta implements Serializable {
    private Integer idKarte;
    private Date datumIzdavanja;
    private Double cijena;
    private Boolean rezervisana;
    private Klijent klijent;
    private Projekcija projekcija;

    {
        klijent = null;
    }

    public Karta(){
    }

    public Karta(Integer idKarte, Date datumIzdavanja, Double cijena, Projekcija projekcija) {
        this.idKarte = idKarte;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.projekcija = projekcija;
    }

    public Karta(Integer idKarte, Date datumIzdavanja, Double cijena, Boolean rezervisana, Klijent klijent, Projekcija projekcija) {
        this.idKarte = idKarte;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.rezervisana = rezervisana;
        this.klijent = klijent;
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

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }
}
