package projektovanje.dto;

import projektovanje.bin.transakcije.Racun;

import java.io.Serializable;

public class DTORacun implements Serializable {
    Racun racun;

    public DTORacun() {
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public DTORacun(Racun racun) {
        this.racun = racun;
    }
}
