package projektovanje.dto;

import projektovanje.bin.zaposleni.Racunovodja;

import java.io.Serializable;

public class DTORacunovodja implements IDTO {
    public static final long serialVersionUID=10016l;
    Racunovodja racunovodja;

    public DTORacunovodja() {
    }

    public DTORacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }

    public Racunovodja getRacunovodja() {
        return racunovodja;
    }

    public void setRacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }
}
