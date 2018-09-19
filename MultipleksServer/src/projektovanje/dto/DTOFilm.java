package projektovanje.dto;

import projektovanje.bin.film.Film;

import java.io.Serializable;

public class DTOFilm implements IDTO {
    public static final long serialVersionUID=10002l;
    Film film;

    public DTOFilm(){}

    public DTOFilm(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
