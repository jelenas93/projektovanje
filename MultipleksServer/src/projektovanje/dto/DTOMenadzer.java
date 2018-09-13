package projektovanje.dto;

import projektovanje.bin.zaposleni.Menadzer;

import java.io.Serializable;

public class DTOMenadzer implements Serializable {
    Menadzer menadzer;

    public DTOMenadzer() {
    }

    public DTOMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }
}
