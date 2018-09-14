package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Kinooperater extends Zaposleni {
    private Integer idKinooperatera;

    public Kinooperater(Integer idKinooperatera) {
        this.idKinooperatera = idKinooperatera;
    }

    public Kinooperater() {
    }

    public Kinooperater(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idKinooperatera) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idKinooperatera = idKinooperatera;
    }

    public Kinooperater(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }
}
