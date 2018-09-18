package projektovanje.dto;

import projektovanje.bin.zaposleni.Skladistar;

import java.io.Serializable;

public class DTOSkladistar implements Serializable, IDTO {
    public static final long serialVersionUID=10020l;
    Skladistar skladistar;

    public DTOSkladistar() {
    }

    public DTOSkladistar(Skladistar skladistar) {
        this.skladistar = skladistar;
    }

    public Skladistar getSkladistar() {
        return skladistar;
    }

    public void setSkladistar(Skladistar skladistar) {
        this.skladistar = skladistar;
    }
}
