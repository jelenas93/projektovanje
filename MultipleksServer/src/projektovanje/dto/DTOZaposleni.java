package projektovanje.dto;

import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;

public class DTOZaposleni implements Serializable, IDTO {
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
