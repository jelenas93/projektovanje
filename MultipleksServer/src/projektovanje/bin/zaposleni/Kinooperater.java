package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Kinooperater extends Zaposleni {
    private Integer idKinooperatera;

    public Integer getIdKinooperatera() {
        return idKinooperatera;
    }

    public void setIdKinooperatera(Integer idKinooperatera) {
        this.idKinooperatera = idKinooperatera;
    }

    public Kinooperater(Integer idKinooperatera) {
        this.idKinooperatera = idKinooperatera;
    }

    public Kinooperater() {
    }

    public Kinooperater(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idKinooperatera) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idKinooperatera = idKinooperatera;
    }
}
