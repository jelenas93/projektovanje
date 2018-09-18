package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Racunovodja extends Zaposleni {
    public static final long serialVersionUID=9022l;

    public Integer getIdRacunovodje() {
        return this.getIdZaposlenog();
    }

    public void setIdRacunovodje(Integer idRacunovodje) {
        this.setIdZaposlenog(idRacunovodje);
    }

    public Racunovodja(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog,Boolean aktivan, Integer idRacunovodje) {
        super(idZaposlenog, plata, ime, prezime, JMBG, aktivan, nalog);
    }


    public Racunovodja(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(),zaposleni.getAktivan(), zaposleni.getNalog());
    }


    public Racunovodja() {
    }



    public Racunovodja(Integer idRacunovodje) {
        this.setIdZaposlenog(idRacunovodje);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
