package projektovanje.dto;

import projektovanje.bin.racun.Stavka;

import java.io.Serializable;

public class DTOStavka implements Serializable {
    Stavka stavka;

    public Stavka getStavka() {
        return stavka;
    }

    public void setStavka(Stavka stavka) {
        this.stavka = stavka;
    }

    public DTOStavka(Stavka stavka) {
        this.stavka = stavka;
    }

    public DTOStavka() {
    }
}
