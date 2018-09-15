package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Racunovodja extends Zaposleni {

    public Integer getIdRacunovodje() {
        return this.getIdZaposlenog();
    }

    public void setIdRacunovodje(Integer idRacunovodje) {
        this.setIdZaposlenog(idRacunovodje);
    }

    public Racunovodja(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idRacunovodje) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
    }

    public Racunovodja() {
    }



    public Racunovodja(Integer idRacunovodje) {
        this.setIdZaposlenog(idRacunovodje);
    }
}
