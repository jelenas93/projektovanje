package projektovanje.bin.izdavanje;

import projektovanje.bin.film.Film;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;

public class Izdavanje implements Serializable {
    public static final long serialVersionUID=9004l;
    private Karta karta;
    private Sjediste sjediste;
    private Sala sala;
    private Projekcija projekcija;
    private Zaposleni zaposleni;

    public Izdavanje() {
    }

    public Izdavanje(Karta karta, Sjediste sjediste, Sala sala, Projekcija projekcija, Zaposleni zaposleni) {
        this.karta = karta;
        this.sjediste = sjediste;
        this.sala = sala;
        this.projekcija = projekcija;
        this.zaposleni = zaposleni;
    }

    @Override
    public String toString() {
        return "Izdavanje{" +
                "karta=" + karta +
                ", sjediste=" + sjediste +
                ", sala=" + sala +
                ", projekcija=" + projekcija +
                ", zaposleni=" + zaposleni +
                '}';
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

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
