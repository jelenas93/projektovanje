package projektovanje.dto;

import projektovanje.bin.nalog.Nalog;

import java.io.Serializable;

public class DTONalog implements Serializable, IDTO {
    Nalog nalog;

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public DTONalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public DTONalog() {
    }
}
