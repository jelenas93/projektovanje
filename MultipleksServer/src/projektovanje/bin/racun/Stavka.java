package projektovanje.bin.racun;

import projektovanje.bin.oprema.*;

import java.io.Serializable;

public class Stavka implements Serializable {
    public static final long serialVersionUID=9011l;
    private Integer idStavke;
    private Integer kolicina;
    private Double ukupaCijena;
    private Artikal artikal;
    private Integer idRacuna;

    public Stavka() {
    }

    @Override
    public String toString() {
        return "Stavka{" +
                "idStavke=" + idStavke +
                ", kolicina=" + kolicina +
                ", ukupaCijena=" + ukupaCijena +
                ", artikal=" + artikal.toString() +
                '}';
    }

    public Stavka(Integer idStavke, Integer kolicina, Double ukupaCijena, Artikal artikal,Integer idRacuna) {
        this.idStavke = idStavke;
        this.kolicina = kolicina;
        this.ukupaCijena = ukupaCijena;
        this.artikal = artikal;
        this.idRacuna=idRacuna;
    }

    public Integer getIdStavke() {
        return idStavke;
    }

    public void setIdStavke(Integer idStavke) {
        this.idStavke = idStavke;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Double getUkupaCijena() {
        return ukupaCijena;
    }

    public void setUkupaCijena(Double ukupaCijena) {
        this.ukupaCijena = ukupaCijena;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Integer getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(Integer idRacuna) {
        this.idRacuna = idRacuna;
    }
}
