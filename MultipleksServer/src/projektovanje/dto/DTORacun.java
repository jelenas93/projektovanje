package projektovanje.dto;

import projektovanje.bin.transakcije.Racun;

import java.io.Serializable;

public class DTORacun implements Serializable, IDTO {
    public static final long serialVersionUID=10015l;
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
