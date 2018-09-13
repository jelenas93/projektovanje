package projektovanje.dto;

import projektovanje.bin.sala.Sala;

import java.io.Serializable;

public class DTOSala implements Serializable, IDTO {
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
