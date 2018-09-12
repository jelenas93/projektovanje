package projektovanje.bin.projekcija;

import projektovanje.bin.film.Film;
import projektovanje.bin.zaposleni.Zaposleni;

import java.util.Date;

public class Projekcija {
    private Integer idProjekcije;
    private Film film;
    private Date vrijeme;
    private Zaposleni zaposleni;

    public Projekcija() {
    }

    public Projekcija(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni) {
        this.idProjekcije = idProjekcije;
        this.film = film;
        this.vrijeme = vrijeme;
        this.zaposleni = zaposleni;
    }

    public Integer getIdProjekcije() {
        return idProjekcije;
    }

    public void setIdProjekcije(Integer idProjekcije) {
        this.idProjekcije = idProjekcije;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Date vrijeme) {
        this.vrijeme = vrijeme;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
