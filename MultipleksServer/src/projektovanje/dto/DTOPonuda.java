package projektovanje.dto;

import projektovanje.bin.film.Ponuda;

import java.io.Serializable;

public class DTOPonuda implements Serializable, IDTO {
    public static final long serialVersionUID=10011l;
    Ponuda ponuda;

    public DTOPonuda(){}

    public DTOPonuda(Ponuda ponuda) {
        this.ponuda = ponuda;
    }

    public Ponuda getPonuda() {
        return ponuda;
    }

    public void setPonuda(Ponuda ponuda) {
        this.ponuda = ponuda;
    }
}
