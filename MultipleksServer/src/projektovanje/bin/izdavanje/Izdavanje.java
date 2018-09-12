package projektovanje.bin.izdavanje;

import projektovanje.bin.film.Film;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;

public class Izdavanje {
    private Karta karta;
    private Sjediste sjediste;
    private Sala sala;
    private Projekcija projekcija;
    private Film film;
    private Zaposleni zaposleni;

    public Izdavanje() {
    }

    public Izdavanje(Karta karta, Sjediste sjediste, Sala sala, Projekcija projekcija, Film film, Zaposleni zaposleni) {
        this.karta = karta;
        this.sjediste = sjediste;
        this.sala = sala;
        this.projekcija = projekcija;
        this.film = film;
        this.zaposleni = zaposleni;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }

    public Sjediste getSjediste() {
        return sjediste;
    }

    public void setSjediste(Sjediste sjediste) {
        this.sjediste = sjediste;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
