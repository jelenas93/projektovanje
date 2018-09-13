package projektovanje.dto;

import projektovanje.bin.klijent.Klijent;

import java.io.Serializable;

public class DTOKlijent implements Serializable {
    Klijent klijent;

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public DTOKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public DTOKlijent() {
    }
}
