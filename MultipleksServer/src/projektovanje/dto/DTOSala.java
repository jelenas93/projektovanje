package projektovanje.dto;

import projektovanje.bin.sala.Sala;

import java.io.Serializable;

public class DTOSala implements IDTO {
    public static final long serialVersionUID=10018l;
    Sala sala;

    public DTOSala() {
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public DTOSala(Sala sala) {
        this.sala = sala;
    }
}
