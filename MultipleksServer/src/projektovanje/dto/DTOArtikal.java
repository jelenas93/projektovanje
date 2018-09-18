package projektovanje.dto;

import projektovanje.bin.oprema.Artikal;

import java.io.Serializable;

public class DTOArtikal implements IDTO {
    public static final long serialVersionUID=10001l;
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
