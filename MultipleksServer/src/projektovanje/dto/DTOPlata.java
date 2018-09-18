package projektovanje.dto;

import projektovanje.bin.plata.Plata;

import java.io.Serializable;

public class DTOPlata implements Serializable, IDTO {
    public static final long serialVersionUID=10010l;

    Plata plata;

    public Plata getPlata() {
        return plata;
    }

    public void setPlata(Plata plata) {
        this.plata = plata;
    }

    public DTOPlata() {
    }

    public DTOPlata(Plata plata) {
        this.plata = plata;
    }
}
