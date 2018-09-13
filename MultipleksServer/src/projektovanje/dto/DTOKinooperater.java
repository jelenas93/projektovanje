package projektovanje.dto;

import projektovanje.bin.zaposleni.Kinooperater;

import java.io.Serializable;

public class DTOKinooperater implements Serializable, IDTO {
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
