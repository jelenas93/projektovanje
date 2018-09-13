package projektovanje.bin.karta;

import projektovanje.bin.klijent.Klijent;

import java.io.Serializable;
import java.util.Date;

public class Karta implements Serializable {
    private Integer idKarte;
    private Date datumIzdavanja;
    private Double cijena;
    private Boolean rezervisana;
    private Klijent klijent;

    {
        klijent = null;
    }

    public Karta(){
    }

    public Karta(Integer idKarte, Date datumIzdavanja, Double cijena) {
        this.idKarte = idKarte;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
    }

    public Karta(Integer idKarte, Date datumIzdavanja, Double cijena, Boolean rezervisana, Klijent klijent) {
        this.idKarte = idKarte;
        this.datumIzdavanja = datumIzdavanja;
        this.cijena = cijena;
        this.rezervisana = rezervisana;
        this.klijent = klijent;
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
