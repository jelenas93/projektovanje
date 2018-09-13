package projektovanje.dto;

import projektovanje.bin.sala.Sjediste;

import java.io.Serializable;

public class DTOSjediste implements Serializable {
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
