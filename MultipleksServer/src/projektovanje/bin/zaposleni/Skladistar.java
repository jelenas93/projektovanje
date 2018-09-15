package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Skladistar extends Zaposleni {
    private Integer idSkladistara;

    public Integer getIdSkladistara() {
        return idSkladistara;
    }

    public void setIdSkladistara(Integer idSkladistara) {
        this.idSkladistara = idSkladistara;
    }

    public Skladistar(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idSkladistara) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idSkladistara = idSkladistara;
    }

    public Skladistar() {
    }

    public Skladistar(Integer idSkladistara) {
        this.idSkladistara = idSkladistara;
    }
}
