package projektovanje.bin.film;

import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Ponuda implements Serializable {
    public static final long serialVersionUID=9002l;

    private Integer idPonude;
    private List<Film> film;
    private Date datumPonude;
    private Zaposleni zaposleni;

    public Ponuda() {
    }

    @Override
    public String toString() {
        String filmovi = "";
        Iterator<Film> it = film.iterator();
        while(it.hasNext()){
            filmovi+=it.next().toString();
        }
        return "Ponuda{" +
                "idPonude=" + idPonude +
                ", film=" + filmovi +
                ", datumPonude=" + datumPonude +
                ", zaposleni=" + null +
                '}';
    }

    public Ponuda(Integer idPonude, List<Film> film, Date datumPonude, Zaposleni zaposleni) {
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

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
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
