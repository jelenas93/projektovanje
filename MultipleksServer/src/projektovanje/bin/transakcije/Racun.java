package projektovanje.bin.transakcije;

import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.bin.racun.Stavka;

import java.util.Date;

public class Racun {
    private Integer idRacuna;
    private Stavka stavka;
    private Date datumIzdavanja;
    private Double ukupndaCijena;
    private Zaposleni zaposleni;
    private Double kolicina;

    public Racun() {
    }

    public Racun(Integer idRacuna, Stavka stavka, Date datumIzdavanja, Double ukupndaCijena, Zaposleni zaposleni, Double kolicina) {
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

    public Stavka getStavka() {
        return stavka;
    }

    public void setStavka(Stavka stavka) {
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
