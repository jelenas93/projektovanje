package projektovanje.dto;

import projektovanje.bin.zaposleni.Racunovodja;

import java.io.Serializable;

public class DTORacuovodja implements Serializable, IDTO {
    Racunovodja racunovodja;

    public DTORacuovodja() {
    }

    public DTORacuovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }

    public Racunovodja getRacunovodja() {
        return racunovodja;
    }

    public void setRacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }
}
