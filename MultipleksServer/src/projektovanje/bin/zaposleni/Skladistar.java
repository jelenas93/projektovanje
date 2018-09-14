package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Skladistar extends Zaposleni {
    private Integer idSkladistara;

    public Skladistar(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }

    public Skladistar(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idSkladistara) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idSkladistara = idSkladistara;
    }

    public Skladistar() {
    }

    public Skladistar(Integer idSkladistara) {
        this.idSkladistara = idSkladistara;
    }
}
