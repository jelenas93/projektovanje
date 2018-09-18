package projektovanje.dto;

import projektovanje.bin.racun.Stavka;

import java.io.Serializable;

public class DTOStavka implements Serializable, IDTO {
    public static final long serialVersionUID=10021l;
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
