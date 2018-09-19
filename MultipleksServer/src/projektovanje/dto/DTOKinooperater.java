package projektovanje.dto;

import projektovanje.bin.zaposleni.Kinooperater;

import java.io.Serializable;

public class DTOKinooperater implements IDTO {
    public static final long serialVersionUID=10005l;
    Kinooperater kinooperater;

    public DTOKinooperater() {
    }

    public DTOKinooperater(Kinooperater kinooperater) {
        this.kinooperater = kinooperater;
    }

    public Kinooperater getKinooperater() {
        return kinooperater;
    }

    public void setKinooperater(Kinooperater kinooperater) {
        this.kinooperater = kinooperater;
    }
}
