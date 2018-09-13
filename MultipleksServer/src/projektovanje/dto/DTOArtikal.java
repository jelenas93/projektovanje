package projektovanje.dto;

import projektovanje.bin.oprema.Artikal;

import java.io.Serializable;

public class DTOArtikal implements Serializable {
    Artikal artikal;

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public DTOArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public DTOArtikal() {
    }
}
