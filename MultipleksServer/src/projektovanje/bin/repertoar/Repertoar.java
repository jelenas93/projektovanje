package projektovanje.bin.repertoar;

import projektovanje.bin.film.Film;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Repertoar implements Serializable {
    private Integer idRepertoara;
    private List<Projekcija> projekcija;
    private Zaposleni zaposleni;
    private Date datumOd;
    private Date datumDo;

    public Repertoar() {
    }

    @Override
    public String toString() {
        return "Repertoar{" +
                "idRepertoara=" + idRepertoara +
                ", projekcija=" + projekcija +
                ", zaposleni=" + zaposleni +
                ", datumOd=" + datumOd +
                ", datumDo=" + datumDo +
                '}';
    }

    public Repertoar(Integer idRepertoara, List<Projekcija> projekcija, Zaposleni zaposleni, Date datumOd, Date datumDo) {
        this.idRepertoara = idRepertoara;
        this.projekcija = projekcija;
        this.zaposleni = zaposleni;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Integer getIdRepertoara() {
        return idRepertoara;
    }

    public void setIdRepertoara(Integer idRepertoara) {
        this.idRepertoara = idRepertoara;
    }

    public List<Projekcija> getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(List<Projekcija> projekcija) {
        this.projekcija = projekcija;
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

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }
}
