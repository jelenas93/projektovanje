package projektovanje.dto;

import projektovanje.bin.film.Zanr;

import java.io.Serializable;

public class DTOZanr implements Serializable, IDTO {
    public static final long serialVersionUID=10023l;
    Zanr zanr;

    public DTOZanr() {
    }

    public DTOZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }
}
