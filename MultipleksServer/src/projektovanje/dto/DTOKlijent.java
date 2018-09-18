package projektovanje.dto;

import projektovanje.bin.klijent.Klijent;

import java.io.Serializable;

public class DTOKlijent implements IDTO {
    public static final long serialVersionUID=10006l;
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
