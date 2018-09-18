package projektovanje.dto;

import projektovanje.bin.karta.Karta;

import java.io.Serializable;

public class DTOKarta implements Serializable, IDTO {
    public static final long serialVersionUID=10004l;
    Karta karta;

    public DTOKarta() {
    }

    public DTOKarta(Karta karta) {
        this.karta = karta;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }
}
