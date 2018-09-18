package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Menadzer extends Zaposleni {
    public static final long serialVersionUID=9019l;

    public Integer getIdMenadzera() {
        return this.getIdZaposlenog();
    }

    public void setIdMenadzera(Integer idMenadzera) {
         this.setIdZaposlenog(idMenadzera);
    }

    public Menadzer(Integer idMenadzera) {
        this.setIdZaposlenog(idMenadzera);
    }


    public Menadzer(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(), zaposleni.getAktivan(), zaposleni.getNalog());
    }


    public Menadzer() {
    }

    public Menadzer(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Boolean aktivan, Integer idMenadzera) {
        super(idZaposlenog, plata, ime, prezime, JMBG, aktivan, nalog);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
