package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Kinooperater extends Zaposleni {
    public static final long serialVersionUID=9018l;

    public Integer getIdKinooperatera() {
        return this.getIdZaposlenog();
    }

    public void setIdKinooperatera(Integer idKinooperatera) {
        this.setIdZaposlenog(idKinooperatera);
    }

    public Kinooperater(Integer idKinooperatera) {
        this.setIdZaposlenog(idKinooperatera);
    }


    public Kinooperater(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(),zaposleni.getAktivan(), zaposleni.getNalog());
    }


    public Kinooperater() {
    }

    public Kinooperater(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Boolean aktivan, Nalog nalog) {
        super(idZaposlenog, plata, ime, prezime, JMBG, aktivan, nalog);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
