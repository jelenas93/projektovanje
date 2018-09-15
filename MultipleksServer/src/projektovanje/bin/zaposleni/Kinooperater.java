package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Kinooperater extends Zaposleni {

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
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(), zaposleni.getNalog());
    }


    public Kinooperater() {
    }

    public Kinooperater(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idKinooperatera) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
    }
}
