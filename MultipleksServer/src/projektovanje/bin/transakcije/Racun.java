package projektovanje.bin.transakcije;

import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.bin.racun.Stavka;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Racun implements Serializable {
    private Integer idRacuna;
    private List<Stavka> stavka;
    private Date datumIzdavanja;
    private Double ukupndaCijena;
    private Zaposleni zaposleni;
    private Double kolicina;

    public Racun() {
    }

    public Racun(Integer idRacuna, List<Stavka> stavka, Date datumIzdavanja, Double ukupndaCijena, Zaposleni zaposleni, Double kolicina) {
        this.idRacuna = idRacuna;
        this.stavka = stavka;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupndaCijena = ukupndaCijena;
        this.zaposleni = zaposleni;
        this.kolicina = kolicina;
    }

    public Integer getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(Integer idRacuna) {
        this.idRacuna = idRacuna;
    }

    public List<Stavka> getStavka() {
        return stavka;
    }

    public void setStavka(List<Stavka> stavka) {
        this.stavka = stavka;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Double getUkupndaCijena() {
        return ukupndaCijena;
    }

    public void setUkupndaCijena(Double ukupndaCijena) {
        this.ukupndaCijena = ukupndaCijena;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public Integer getIdZaposlenog(){
        return this.zaposleni.getIdZaposlenog();
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }
}
