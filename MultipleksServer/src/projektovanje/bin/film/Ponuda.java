package projektovanje.bin.film;

import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;

public class Ponuda implements Serializable {
    private Integer idPonude;
    private Film film;
    private Date datumPonude;
    private Zaposleni zaposleni;

    public Ponuda() {
    }

    public Ponuda(Integer idPonude, Film film, Date datumPonude, Zaposleni zaposleni) {
        this.idPonude = idPonude;
        this.film = film;
        this.datumPonude = datumPonude;
        this.zaposleni = zaposleni;
    }

    public Integer getIdPonude() {
        return idPonude;
    }

    public void setIdPonude(Integer idPonude) {
        this.idPonude = idPonude;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getDatumPonude() {
        return datumPonude;
    }

    public void setDatumPonude(Date datumPonude) {
        this.datumPonude = datumPonude;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
