package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Racunovodja extends Zaposleni {
    private Integer idRacunovodje;

    public Integer getIdRacunovodje() {
        return idRacunovodje;
    }

    public void setIdRacunovodje(Integer idRacunovodje) {
        this.idRacunovodje = idRacunovodje;
    }

    public Racunovodja(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idRacunovodje) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idRacunovodje = idRacunovodje;
    }

    public Racunovodja() {
    }



    public Racunovodja(Integer idRacunovodje) {
        this.idRacunovodje = idRacunovodje;
    }
}
