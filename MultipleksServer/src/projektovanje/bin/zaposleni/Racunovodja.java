package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Racunovodja extends Zaposleni {
    private Integer idRacunovodje;

    public Racunovodja(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }

    public Racunovodja() {
    }

    public Racunovodja(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idRacunovodje) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idRacunovodje = idRacunovodje;
    }

    public Racunovodja(Integer idRacunovodje) {
        this.idRacunovodje = idRacunovodje;
    }
}
