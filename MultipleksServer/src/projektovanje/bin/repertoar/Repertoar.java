package projektovanje.bin.repertoar;

import projektovanje.bin.film.Film;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;

public class Repertoar implements Serializable {
    private Integer idRepertoara;
    private Projekcija projekcija;
    private Film film;
    private Zaposleni zaposleni;
    private Date datumOd;
    private Date datumDo;

    public Repertoar() {
    }

    public Repertoar(Integer idRepertoara, Projekcija projekcija, Film film, Zaposleni zaposleni, Date datumOd, Date datumDo) {
        this.idRepertoara = idRepertoara;
        this.projekcija = projekcija;
        this.film = film;
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

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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
