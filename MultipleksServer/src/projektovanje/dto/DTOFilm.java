package projektovanje.dto;

import projektovanje.bin.film.Film;

import java.io.Serializable;

public class DTOFilm implements Serializable, IDTO {
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
