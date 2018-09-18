package projektovanje.dto;

import projektovanje.bin.sala.Sjediste;

import java.io.Serializable;

public class DTOSjediste implements Serializable, IDTO {
    public static final long serialVersionUID=10019l;
    Sjediste sjediste;

    public DTOSjediste() {
    }

    public DTOSjediste(Sjediste sjediste) {
        this.sjediste = sjediste;
    }

    public Sjediste getSjediste() {
        return sjediste;
    }

    public void setSjediste(Sjediste sjediste) {
        this.sjediste = sjediste;
    }
}
