package projektovanje.dto;

import projektovanje.bin.projekcija.Projekcija;

import java.io.Serializable;

public class DTOProjekcija implements Serializable, IDTO {
    Projekcija projekcija;

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public DTOProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public DTOProjekcija() {
    }
}
