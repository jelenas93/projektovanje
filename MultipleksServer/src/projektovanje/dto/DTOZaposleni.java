package projektovanje.dto;

import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;

public class DTOZaposleni implements IDTO {

    public static final long serialVersionUID=10024l;
    Zaposleni zaposleni;

    public DTOZaposleni() {
    }

    public DTOZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
