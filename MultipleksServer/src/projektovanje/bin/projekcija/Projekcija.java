package projektovanje.bin.projekcija;

import projektovanje.bin.film.Film;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;

public class Projekcija implements Serializable {
    public static final long serialVersionUID=9010l;
    private Integer idProjekcije;
    private Film film;
    private Date vrijeme;
    private Zaposleni zaposleni;
    private Integer idRepertoara;

    public Projekcija() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Projekcija{" +
                "idProjekcije=" + idProjekcije +
                ", film=" + film +
                ", vrijeme=" + vrijeme +
                ", zaposleni=" + zaposleni +
                '}';
    }

    public Projekcija(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni,Integer idRepertoara) {
        this.idProjekcije = idProjekcije;
        this.film = film;
        this.vrijeme = vrijeme;
        this.zaposleni = zaposleni;
        this.idRepertoara = idRepertoara;
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

    public Integer getIdRepertoara() {
        return idRepertoara;
    }

    public void setIdRepertoara(Integer idRepertoara) {
        this.idRepertoara = idRepertoara;
    }
}
