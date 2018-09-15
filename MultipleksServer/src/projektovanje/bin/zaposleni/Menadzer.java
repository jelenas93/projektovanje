package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Menadzer extends Zaposleni {

    public Integer getIdMenadzera() {
        return this.getIdZaposlenog();
    }

    public void setIdMenadzera(Integer idMenadzera) {
         this.setIdZaposlenog(idMenadzera);
    }

    public Menadzer(Integer idMenadzera) {
        this.setIdZaposlenog(idMenadzera);
    }

    public Menadzer() {
    }

    public Menadzer(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idMenadzera) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
    }
}
