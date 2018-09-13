package projektovanje.dto;

import projektovanje.bin.izdavanje.Izdavanje;

import java.io.Serializable;

public class DTOIzdavanje implements Serializable, IDTO {
    Izdavanje izdavanje;

    public DTOIzdavanje() {
    }

    public DTOIzdavanje(Izdavanje izdavanje) {
        this.izdavanje = izdavanje;
    }

    public Izdavanje getIzdavanje() {
        return izdavanje;
    }

    public void setIzdavanje(Izdavanje izdavanje) {
        this.izdavanje = izdavanje;
    }
}
