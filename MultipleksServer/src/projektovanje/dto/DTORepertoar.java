package projektovanje.dto;

import projektovanje.bin.repertoar.Repertoar;

import java.io.Serializable;

public class DTORepertoar implements Serializable {
    Repertoar repertoar;

    public Repertoar getRepertoar() {
        return repertoar;
    }

    public void setRepertoar(Repertoar repertoar) {
        this.repertoar = repertoar;
    }

    public DTORepertoar(Repertoar repertoar) {
        this.repertoar = repertoar;
    }

    public DTORepertoar() {
    }
}
